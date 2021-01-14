package hackerrank;
import java.util.ArrayDeque;
/**
 * Given a binary tree, return the number of nodes with exactly one child. The tree contains between 1 and 10^6 nodes.
 *
 * Constraints:
 *      <ul>
 *          <li>(*) The tree has from 1 to 10^6 nodes.</li>
 *          <li>(*) The values in the nodes do not matter for this problem.</li>
 *      </ul>
 */
public class NumSingleChildNodes
{
	private static class TreeNode
	{
		Integer val;        // Not really relevant to this problem
		TreeNode left;
		TreeNode right;
	}

	public static int solution(TreeNode root)
	{
		//write your solution here

        /* Doing this recursively would be a straightforward and easy to understand solution, but with up to a million nodes in the
        tree, only if it is balanced will I have guarantees of log_2(1000000) =~= 20 stack frames of a recursion. I might
        end up with a fully imbalanced tree that could yield all the way up to 1,000,000 stack frames.

          So I will instead opt for a BFS with a custom queue of mine. I could have also done a DFS with a custom stack. The size
        of both (so, our space complexity) will be linear in the number of nodes in the tree, but for trees that are not heavily
        imbalanced, the queue will grow more "tractably".

        TODO: Make a solution with a Python dequeue, since people in Pathrise tend to use that a lot. */

		final ArrayDeque<TreeNode> myQueue = new ArrayDeque<>();
		int numSingleChildNodes = 0;
		myQueue.add(root);
		while(!myQueue.isEmpty())
		{
			final TreeNode currentNode = myQueue.poll();
			if(hasOnlyOneChild(currentNode))
			{
				numSingleChildNodes++;
			}
			addChildrenToDeque(currentNode, myQueue);
		}
		return numSingleChildNodes;
	}

	private static boolean hasOnlyOneChild(final TreeNode node)
	{
		return (node.left != null && node.right == null)
		       ||
		       (node.right != null && node.left == null);
	}

	private static void addChildrenToDeque(final TreeNode currentNode, final ArrayDeque<TreeNode> myQueue)
	{
		if(currentNode.left != null)
		{
			myQueue.add(currentNode.left);
		}
		if(currentNode.right != null)
		{
			myQueue.add(currentNode.right);
		}
	}
}
