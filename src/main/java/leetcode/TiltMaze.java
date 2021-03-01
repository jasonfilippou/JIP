package leetcode;
/**
 * <a href="https://leetcode.com/problems/the-maze/">LeetCode source</a>
 */
public class  TiltMaze
{
	// Do a DFS where every time you encounter a '1', you increase the number of current nodes. In fact,
	// at every level of the recursion, you need to consider different positions. You want the min.
//	public static int minMoves(final int[] startCoords, final int[] endCoords, final int[][] matrix)
//	{
//		final int[][] directions = buildDirections();
//		if(Arrays.equals(startCoords, endCoords))
//		{
//			return 0;
//		}
//		else
//		{
//			return minMovesTowardsGoal(startCoords, endCoords, matrix, buildDirections, 0);
//		}
//	}
//
//	private static int minMovesTowardsGoal(final int[] startCoords, final int[] endCoords, final int[][] matrix,
//	                                       final int[][] directions, final int currentNumberOfMoves)
//	{
//		if(badCoords(startCoords, matrix))  // wall or beyond boundaries
//		{
//			return currentNumberOfMoves;
//		}
//		else
//		{
//			for(int[] direction : directions)
//			{
//
//			}
//		}
//	}
}
