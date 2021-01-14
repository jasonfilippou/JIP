package hackerrank;
/**
 * Given a Binary Search Tree, return whether it contains any value in the range [-3, 3] (both ends included).
 *
 * Definition: A binary search tree is a binary tree where, for each node, the values in the left subtree are strictly
 * smaller than the node's, and the values in the right subtree are strictly larger than the node's.
 *
 * Constraints:
 *  <ul>
 *      <li>Tree contains between 1 and 10^6 nodes.</li>
 *      <li> The tree contains distinct values.</li>
 *  </ul>
 */
public class BstSearchCloseToZero
{
	private static class TreeNode
	{
		Integer val;
		TreeNode left;
		TreeNode right;
	}
	private static final Integer LOWER_BOUND_INC = -3, UPPER_BOUND_INC = 3;
	public static boolean solution(TreeNode root)
	{
		//write your solution here

        /* Jason's notes:

            - Since input size can go all the way up to a million, I will aim for linearithmic or linear - time solution.
            - The definition I am given guarantees that there are no duplicates in the binary tree. Affects cmp operators.
            - Tree can be arbitrarily imbalanced, so we should try to avoid recursion.
        */
		TreeNode current = root;
		while(current != null)          // current=null means that we exhausted the tree with no success.
		{
			if(current.val > UPPER_BOUND_INC)
			{
				current = current.left;
			}
			else if(current.val < LOWER_BOUND_INC)
			{
				current = current.right;
			}
			else
			{
				return true;
			}
		}
		return false;
	}
}
