package hackerrank;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/**
 * A binary matrix is a matrix with only '0's and '1's. We say that a binary matrix has a &quot;tunnel&quot; if it
 * has 0's everywhere except for a &quot;path&quot; of 1s that goes from the top-left cell to the top-right cell going
 * through adjacent cells. Diagonal cells do <b>not</b> count as adjacent. In addition, every &quot;1&quot; in the path,
 * except for the first and last ones, is adjacent to <b>exactly two other 1s</b>, one that is closer in the path to the
 * top-left corner, and one that is closer in the path to the top-right corner.
 *
 * Given a binary matrix with a tunnel, find how deep the tunnel reaches. The depth of the tunnel is the index of the
 * last row traversed by the tunnel, with rows being 0 - indexed.
 *
 * Constraints:
 *
 *  <ul>
 *      <li>The maximum number of rows / columns in the matrix is 1,000.</li>
 *  </ul>
 */
public class TunnelDepth
{
	private static final int OCCUPIED = 1, FREE = 0;
	public static int maxTunnelDepth(List<List<Integer>> grid)
	{

    /* Jason's notes:

        (*) Once again, I will make a hard stop at 45 minutes because of obscene time constraints. If I have code, good.

        (*) We are given that the binary matrix WILL contain a tunnel.

        (*) Additionally, we have that every tunnel square except first and last have EXACTLY TWO TUNNEL SQUARE NEIGHBORS.
        This means that, for example, we could not have a situation like an Example 1 where the cell (1, 1) is filled.

        (*) I clearly need to traverse the graph with either DFS or BFS. I will go DFS because the moment that I reach the last
        row is the moment I  can stop, and it seems to me as if in most cases, it will be faster to reach that last row (if I do)
        by going depth-first.

        (*) That said, my DFS should be iterative, so that I don't end up having StackOverflowExceptions thrown at me. The size of
        the input is too large for the system stack to safely work. But for a first approach, I will do recursion, and if I
        have time, I will optimize it. TODO: do a BFS instead, to avoid recursion.
    */
		// A grid that is not at least 3 x 3 does not have admissible solutions because there will be tunnel nodes
		// with more than 2 neighbors. Assert this.
		assert grid != null && grid.size() >= 3 && grid.get(0) != null && grid.get(0).size() >= 3 : "Bad grid provided.";
		final int largestDepthFound = -1;                           // Largest depth *could* be zero, for a 100% straight tunnel!
		return maxTunnelDepth(grid, 0, 0, largestDepthFound);       // Begin from top-left corner.
	}
	private static int maxTunnelDepth(final List<List<Integer>> grid, int i, int j, int largestDepthFound)
	{
		final int rows = grid.size(), columns = grid.get(0).size();
		if (i >= 0 && j >= 0)
		{
			if (grid.get(i).get(j) == OCCUPIED)
			{
				if (j > largestDepthFound)
				{
					largestDepthFound = j;
					if (largestDepthFound == rows - 1)
					{
						return largestDepthFound;     // Largest possible depth achieved.
					}
				}
				// The following modifies the argument grid in place to avoid space for either a deep copy of the grid
				// or a visitation set. The first one would be Theta(rows x column) space, the latter O(rows x colum) space.
				grid.get(i).set(j, FREE);
			}
		}
		else        // Out of bounds, cannot progress.
		{
			return largestDepthFound;
		}
		// Now, go to all four directions to find neighbors.
		return getMaxDepthFromNeighbors(grid, i, j, largestDepthFound);   // Hides 4 recursive calls!
	}
	private static int getMaxDepthFromNeighbors(final List<List<Integer>> grid, int i, int j, int largestDepthFound)
	{
		final int leftNeighborMaxDepth = maxTunnelDepth(grid, i, j - 1, largestDepthFound);
		final int topNeighborMaxDepth = maxTunnelDepth(grid, i - 1, j, largestDepthFound);
		final int rightNeighborMaxDepth = maxTunnelDepth(grid, i, j + 1, largestDepthFound);
		final int bottomNeighborDepth = maxTunnelDepth(grid, i + 1, j, largestDepthFound);
		return Collections.max(Arrays.asList(leftNeighborMaxDepth, topNeighborMaxDepth, rightNeighborMaxDepth,
		                                     bottomNeighborDepth, largestDepthFound));
	}
}
