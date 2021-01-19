package hackerrank;
import java.util.Stack;
/**
 * Given a Binary Search Tree, return whether the tree contains a sequence of 10 consecutive values (i.e it contains the values x, x + 1, ..., x + 9 for some x).
 *
 * Definition: A Binary Search Tree is a binary tree where, for each node, the values on the left subtree are strictly smaller and the values on the right
 * subtree are strictly larger.
 *
 * Constraints:
 *  <ul>
 *      <li>The tree contains between 1 and 10^6 nodes.</li>
 *      <li>The tree contains distinct values.</li>
 *      <li>The values are either positive or negative.</li>
 *  </ul>
 */
public class ConsecutiveValuesInBst
{
	private static final int RANGE = 10;                        // How many consecutive numbers do we want to scan for?

	private static class TreeNode
	{
		Integer val;
		TreeNode left;
		TreeNode right;
	}
	public static boolean solution(TreeNode root)
	{
		//write your solution here

        /* Jason's notes

            - Note that if I find one sequence of, say, 9 consecutive numbers but then I fail, it does not mean that I shouldn't try
            again somewhere else in the tree. I might succeed there. 9 could be replaced with any number between 2 and 8 inclusive.

            - So I need to start from the smallest element of the BST, which is its leftmost leaf, and explore the tree in that
            manner.

            - I will continue by performing a DFS over the tree, but I will do it using my own stack, because with up to a million
            nodes I cannot be safe from StackOverflowException instances thrown at me.

            - Once again, the definition of BST given by the question does *not* allow for duplicates.

            - Side note: if I were allowed to touch the node, I could have made this tree threaded and made the inorder traversal
            stackless.

            - I was *not* able to finish this question within 45 minutes. :(
        */
		final Stack<TreeNode> visitationStack = new Stack<>();
		descendToLeftMostLeaf(root, visitationStack);             // Call for side-effect of pushing nodes in leftmost path on stack
		return scanForConsecutiveNums(visitationStack);
	}

	private static void descendToLeftMostLeaf(TreeNode root, final Stack<TreeNode> visitationStack)
	{
		while(root != null)
		{
			visitationStack.push(root);
			root = root.left;
		}
	}

	private static boolean scanForConsecutiveNums(final Stack<TreeNode> visitationStack)
	{
		int currentSequenceLength = 1;                                          // At least "one" consecutive characters.
		TreeNode current;
		int currentSequenceNumber = Integer.MAX_VALUE;                          // Starting value
		while(!visitationStack.isEmpty())
		{
			current = visitationStack.pop();
			if(current.val == (currentSequenceNumber + 1))
			{
				if(++currentSequenceLength == RANGE)
				{
					return true;
				}
			}
			else                                                                // Reset counter, go right if possible
			{
				currentSequenceLength = 1;
				if(current.right != null)
				{
					descendToLeftMostLeaf(current.right, visitationStack);      // Right, then leftmost
				}
			}
			currentSequenceNumber = current.val;
		}
		return false;
	}
}
