package hackerrank;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
/**
 * You are a ninja in a strange environment modeled as an undirected, unweighted, connected graph with n &#8805; 5 nodes. Nodes
 * are identified with an integer between 0 and n - 1. In this graph, there are 5 special nodes; s, the ninja's original
 * location, e, the location of an alien base, and t1, t2, t3 are the locations of 3 sacred temples. You are given {@code
 * n}, the number of nodes, the list of edges in the graph (each edge is a pair of different numbers between 0 and n-1),
 * and the 5 special nodes: {@code s, e, t1, t2} and {@code t3}. All those node IDs are distinct numbers between 0 and n-1.
 *
 * At time 0, we are at node {@code s}. At the same time, 3 alients leave from the alien base. Each alien goes towards one of the temples, each
 * following the shortest path to that temple. Both you and the aliens can move one edge per time step. Return {@literal true}
 * if you can reach <b>all</b> 3 sacred temples (in any order) before the enemies get to ANY of the temples. That is, you need to find
 * if the ninja can follow a path such that there is a time step where the ninja has allready been to t1, t2 and t3, but no alien
 * has reached any one of t1, t2, or t3. If the ninja reaches the last temple at the same time that the alients reach the temple,
 * we should return {@literal false}.
 *
 * See if <a href="https://www.hackerrank.com/test/g68isga7797/questions/eet794qk2sm">this URL</a> is live for some explanatory shapes.
 */
public class TempleRun
{
	private static final int INFINITY = Integer.MAX_VALUE;

	// A class that will help us determine which temple we reached via a shortest path search.
	private static class NodeShortestPathData
	{
		int nodeId;
		int distanceFromSource;
	}

	public static boolean canOutrunAliens(int n, List<List<Integer>> edges, int s, int e, int t1, int t2, int t3)
	{
		// Write your code here

    /*
        Jason's notes:

        (*) I will once again stop at 45 minutes, because I have severe time constraints. Whatever I have then, whether it compiles,
        runs, passes tests or not is what we get.

        (*) Question: In Example 1, it says the ninja needs 6 steps, but the way I see it, he is done at 5. Does he also need to
        reach the starting location for us to count? Edit: It seems that that is a typo, since the rest of the examples do not
        seem to count returning to 's'.

        (*) I am guaranteed that all 5 special nodes are distinct.

        (*) To calculate the length of the shortest path from s that covers all the three temples (so, the fastest we can hope
            to visit all three temples) will be the sum of:

            (-) Shortest path from s to any one of the three temples. Let's call the temple to which this path leads temple_sel_1.
            (-) The shortest path from temple_sel_1 to any one of the other two temples. Let's call the destination temple_sel_2.
            (-) The shortest path from temple_sel_2 to the last temple.

            For every one of those shortest path computations, I will run Dijkstra's algorithm, with weights being all of cost 1.
            I will use a minheap as my priority queue, which leads to complexity O(V + ElogV), for V vertices and E edges. If I
            were to use a Fibonacci heap as my queue, I would be able to drop that to O(V+VlogV), which is better since E = O(V^2).

        (*) I will also have to calculate the length of the path from the alien base to any one of the temples. Every time I update
        the distance of a node from the alien base, I will check to see if that node is a temple node. If it is, I return the length
        of the path that is computed.

        (*) Integer.MAX_VALUE is appropriate as an INFINITY constant, since I have at most 10K nodes, so at most 100,000,000 edges,
        all of which have a weight of 1. 100,000,000 is still under 2^{31} - 1, so I'm good.

        (*) Since we don't need to return any shortest paths, I will not maintain a table of parent pointers, saving some space.

        (*) Speaking of space, my spatial complexity is linear in the number of nodes, since I have a minheap that in the worst case
        can store up to n-1 neighbors.
    */
		final List<Integer> remainingTemples = Arrays.asList(t1, t2, t3);
		final Map<Integer, List<Integer>> edgesAsMap = turnListIntoMap(edges);
		final NodeShortestPathData firstTempleFound = shortestPathFromGivenNodeToAnyTemple(n, edgesAsMap, s, t1, t2, t3);
		remainingTemples.remove(new Integer(firstTempleFound.nodeId)); // Wrapping with Integer to force it to call the right remove()
		final NodeShortestPathData secondTempleFound = shortestPathFromTempleToTemple(firstTempleFound.nodeId, remainingTemples);
		remainingTemples.remove(new Integer(secondTempleFound.nodeId));
		final NodeShortestPathData thirdTempleFound = shortestPathFromTempleToTemple(secondTempleFound.nodeId, remainingTemples);
		final int totalNinjaCost = firstTempleFound.distanceFromSource + secondTempleFound.distanceFromSource +
		                           thirdTempleFound.distanceFromSource;
		return totalNinjaCost < shortestPathFromGivenNodeToAnyTemple(n, edgesAsMap, e, t1, t2, t3).distanceFromSource;    // Strictly smaller.
	}

	private static Map<Integer, List<Integer>> turnListIntoMap(final List<List<Integer>> edges)
	{
		// TODO: can I do this sorta fast using Map.Entry and a lambda?
		throw new RuntimeException("Implement me!");
	}

	private static NodeShortestPathData shortestPathFromGivenNodeToAnyTemple(final int n, final Map<Integer, List<Integer>> edges,
	                                                                         final int source, final int t1, final int t2, final int t3)
	{
		throw new RuntimeException("Implement me!");
	}

	private static NodeShortestPathData shortestPathFromTempleToTemple(final int source, List<Integer> remainingTemples)
	{
		throw new RuntimeException("Implement me!");
	}


}
