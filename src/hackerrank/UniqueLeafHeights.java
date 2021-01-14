package hackerrank;
import java.util.ArrayDeque;
import java.util.Queue;
/**
 * Given a binary tree, return whether all the leaves are at different heights.
 *
 * Definitions:
 *      <ul>
 *          <li>A leaf is a node where <b>both</b> of the children are {@literal null}.</li>
 *          <li>The height of the root is {@code 1}.</li>
 *          <li>The height of any non-root node is the height of its parent + 1.</li>
 *      </ul>
 *
 *  Constraints:
 *      <ul>
 *          <li>The tree contains between 1 and 10^6 nodes.</li>
 *          <li>All nodes contain the value 1, but the value is not important for this problem.</li>
 *      </ul>
 *
 */
public class UniqueLeafHeights
{
	private static class TreeNode
	{
		Integer val;
		TreeNode left;
		TreeNode right;
	}

	public static boolean solution(TreeNode root)
	{
		//write your solution here

        /* Jason's notes:

            - I hate the fact that a stub tree has a height of 1. In every source I've read, and on my slides, it's 0.
            Check yourself, HackerRank.

            - Similarly to the other binary tree problem, I will try my best to avoid recursion and also push for a linear
            or at most a linearithmic solution.

            - We are effectively being asked whether a tree is completely crooked, like, the "opposite" of perfect, in a way

            - I will explore the tree by breadth. When I add a new node's children to the queue, I will check if both are
            leaves. If so, I know for sure that there are two leaves with the same height, so I return false.

            - I will visit every node thrice: to check its children (which also yields a visit for the children, so 2 so
                far), and 1 for polling from the queue. The time of execution is linear in the number of nodes, as is the
                required space for the queue.

            - Unfortunately I had to submit with 2 test cases not passing because I had a workshop to attend.
        */

		final Queue<TreeNode> myQueue = new ArrayDeque<>();
		myQueue.add(root);
		while(!myQueue.isEmpty())
		{
			final TreeNode topNode = myQueue.poll();
			if(enqueueAndCheckChildren(topNode, myQueue) == false)
			{
				return false;
			}
		}
		return true;
	}

	private static boolean enqueueAndCheckChildren(final TreeNode treeNode, final Queue<TreeNode> myQueue)
	{
		if(treeNode.left == null && treeNode.right == null)
		{
			return myQueue.isEmpty();                   // Return false if we just examined root node (special case).
		}
		else if(treeNode.left == null)
		{
			myQueue.add(treeNode.right);
		}
		else if(treeNode.right == null)
		{
			myQueue.add(treeNode.left);
		}
		else
		{
			myQueue.add(treeNode.left);
			myQueue.add(treeNode.right);
		}
		return true;
	}
}
