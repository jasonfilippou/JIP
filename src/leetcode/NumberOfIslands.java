package leetcode;
import java.util.HashSet;
import java.util.Set;
/**
 * <a href="https://leetcode.com/problems/number-of-islands/">Problem 200</a>
 */
public class NumberOfIslands
{
	private class Edge
	{
		final RootedTreeNode source, dest;   // Keep package - access for easier access. It's a private class anyway.

		Edge(final int sourceNodeID, final int destNodeID)
		{
			this.source = new RootedTreeNode(sourceNodeID);     // Creates a self-looped RootedTreeNode instance.
			this.dest = new RootedTreeNode(destNodeID);         // Same.
		}
	}

	private Set<Edge> edges = new HashSet<>();
	private Set<RootedTreeNode> rootedForest = new HashSet<>();

	public int numIslands(char[][] grid)
	{
        /* Algorithm for finding #connected components irrespective of disjoint set implementation (lists, rooted trees, etc)

        --------------------------------
        DISJOINT_SET_SUBROUTINE(Graph G)
        --------------------------------
        (1) For every node n in G:
        (2)     MAKE-SET(n)
        (3) For every edge (i, j) in G
        (4)     if FIND_SET(i) != FIND_SET(j)
        (5)         UNION(i, j)

            When representing the disjoint sets as rooted trees, we will implement MAKE-SET, FIND_SET and UNION such that we introduce
        union-by-rank and path-compression heuristics. This will lead us to time almost linear in the number of operations required
        to run the procedure. Here is pseudocode (more details in chapter 21 of CLRS):


        ----------------
        MAKE_SET(Node n)
        ----------------
        (1) n.p = n             // Self-loop
        (2) n.rank = 0

        ----------------
        FIND_SET(Node n)        // Implements path compression from top to bottom.
        ----------------
        (1) if n.p != n
        (2)     n.p = FIND_SET(n.p)
        (3) return n.p

        ---------------------
        UNION(Node i, Node j)
        ---------------------
        (1) LINK(FIND_SET(i), FIND_SET(j))      // Can be optimized if i and j are guaranteed to be set representatives.

        --------------------
        LINK(Node i, Node j)        // Implements union-by-rank heuristic.
        --------------------
        (1) if i.rank > j.rank
        (2)     j.p = i
        (3) else if i.rank < j.rank
        (4)     i.p = j
        (5) else if i.rank == j.rank    // Trying to link trees of the same rank (height)
        (6)     i.p = j                 // Arbitrary choice
        (7)     i.rank = i.rank + 1     // This is the only situation where rank can actually change! */

		assert grid != null && grid.length > 0 && grid[0]!= null && grid[0].length > 0 : " Bad grid provided.";
		final int n = grid.length;
		final int m = grid[0].length;

		// Build a graph from the grid information. O(m *n) time, O(1) space.
		for(int i = 0; i < n; i++)
		{
			for(int j = 0; j < m; j++)
			{
				if(grid[i][j] == '1')
				{
					rootedForest.add(new RootedTreeNode(i * m + j));        // MAKE-SET operation. Makes a self-looping node.
					storeNewEdges(i, j, grid);
				}
			}
		}
		// Find the connected components of the graph. Time / space complexity dependent on underlying
		// implementation of disjoint components / sets.
		findConnectedComponents();
		return rootedForest.size();
	}

	private void storeNewEdges(final int i, final int j, final char[][] grid)
	{
		final int numCol = grid[0].length;
		final int nodeId = i * numCol + j;                  // Unique node ID
		if((j - 1) > -1 && grid[i][j-1] == '1')
		{
			edges.add(new Edge(nodeId - 1, nodeId));        // Enumeration of nodes is row-major
		}
		if((i - 1) > -1 && grid[i-1][j] == '1')
		{
			edges.add(new Edge(nodeId - numCol, nodeId));   // Node one row up has an ID that is "numCols" smaller than the current
		}
	}

	private void findConnectedComponents()
	{
		for(Edge edge : edges)
		{
			final RootedTreeNode sourceSet = edge.source.findRepr(), destSet = edge.dest.findRepr();
			if(!sourceSet.equals(destSet))
			{
				union(sourceSet, destSet);          // Takes care of node linking and removal from rootedForest.
			}
		}
	}

	private void union(final RootedTreeNode n1, final RootedTreeNode n2)
	{
		// In our implementation, union is effectively link, because the arguments are always
		// the set representatives. This leads to a more efficient implementation than the one
		// implied in CLRS. We will therefore ensure invariant: n1 and n2 should be set representatives.
		assert n1.parent == n1 && n2.parent == n2 : "Trying to perform union between non-representative nodes.";
		n1.link(n2);                                  // TODO: ensure operation is symmetric
	}

	private class RootedTreeNode
	{
		// Keep things package private
		RootedTreeNode parent;
		final int id;
		int rank;


		RootedTreeNode(final int id, int rank)
		{
			this.id = id;
			this.rank = rank;
			this.parent = this;
		}

		RootedTreeNode(final int id)
		{
			this(id, 0);
		}

		// findRepr() implements path compression heuristic.
		RootedTreeNode findRepr()
		{
			if(parent != this)
			{
				parent = parent.findRepr();
			}
			return parent;
		}

		void link(final RootedTreeNode otherTree)
		{
			if(rank > otherTree.rank)
			{
				otherTree.parent = this;
				rootedForest.remove(otherTree);

			}
			else if(rank < otherTree.rank)
			{
				parent = otherTree;
				rootedForest.remove(this);
			}
			else                        // Same rank. Visitor node arbitrarily decided as new tie-breaker.
			{
				parent = otherTree;
				rootedForest.remove(this);
				otherTree.rank++;       // Length of longest simple path from leaves to root increased by 1.
			}
		}
	}


}
