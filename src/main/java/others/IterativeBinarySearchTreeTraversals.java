package others;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
public class IterativeBinarySearchTreeTraversals
{

	private static class TreeNode<T extends  Comparable<T>>
	{
		T data;
		TreeNode<T> left, right;
		TreeNode(final T data, final TreeNode<T> left, final TreeNode<T> right)
		{
			this.data = data;
			this.left = left;
			this.right = right;
		}
		TreeNode(final T data)
		{
			this(data, null, null);
		}
		void insert(final T element)
		{
			TreeNode<T> parent = null, current = this;
			while (current != null)
			{
				parent = current;
				if (element.compareTo(current.data) >= 0)
				{
					current = current.right;
				}
				else
				{
					current = current.left;
				}
			}
			current = new TreeNode<>(element, null, null);
			if (element.compareTo(parent.data) >= 0)
			{
				parent.right = current;
			}
			else
			{
				parent.left = current;
			}
		}
		// Iterative search
		boolean search(final T element)
		{
			TreeNode<T> current = this;
			while (current != null)
			{
				if (element.compareTo(current.data) == 0)
				{
					return true;
				}
				else if (element.compareTo(current.data) > 0)
				{
					current = current.right;
				}
				else
				{
					current = current.left;
				}
			}
			return false;
		}

		/* Traversal algorithms with custom stack */
		void preOrder()
		{
			final Stack<TreeNode<T>> visitationStack = new Stack<>();
			visitationStack.push(this);
			while (!visitationStack.isEmpty())
			{
				final TreeNode<T> currentNode = visitationStack.pop();
				System.out.println(currentNode.data);
				// We push right child first and left second so that we visit left first.
				// If we want reverse preorder, switch order of pushes.
				if (currentNode.right != null)
				{
					visitationStack.push(currentNode.right);
				}
				if (currentNode.left != null)
				{
					visitationStack.push(currentNode.left);
				}
			}
		}
		void inOrder()
		{
			final Stack<TreeNode<T>> visitationStack = new Stack<>();
			TreeNode<T> current = this;
			while (!visitationStack.isEmpty() || current != null)
			{
				// Go left as much as you can at any point.
				while (current != null)
				{
					visitationStack.push(current);
					current = current.left;
				}
				// Pop father and process
				current = visitationStack.pop();
				System.out.println(current.data);
				// Go right
				current = current.right;
			}
		}
		void postOrder()
		{
			// post-order needs two stacks.
			final Stack<TreeNode<T>> visitationStack = new Stack<>();
			final Stack<T> postOrderStack = new Stack<>();
			TreeNode<T> currentNode = this;
			visitationStack.push(currentNode);  // Root will be processed last.
			while (!visitationStack.isEmpty())
			{
				currentNode = visitationStack.pop();
				postOrderStack.push(currentNode.data);
				// Then, push right first, left second, for classic post-order (lrR).
				// Reverse for rlR.
				if (currentNode.left != null)
				{
					visitationStack.push(currentNode.left);
				}

				if (currentNode.right != null)
				{
					visitationStack.push(currentNode.right);
				}

			}
			while(!postOrderStack.isEmpty())
			{
				System.out.println(postOrderStack.pop());
			}
		}
	}

	private static final Integer START_INT = 0, END_INT = 1000;

	public static void main(String[] args)
	{

		/*
		 *   Tree 1:
		 *
		 *         20
		 *
		 *     10      30
		 */

		TreeNode<Integer> treeOne = new TreeNode<>(20);
		treeOne.insert(10);
		treeOne.insert(30);
		System.out.println("Preorder traversal of Tree 1:");
		treeOne.preOrder();
		System.out.println("Inorder traversal of Tree 1");
		treeOne.inOrder();
		System.out.println("Postorder traversal of Tree 1:");
		treeOne.postOrder();

		/*
		*   Tree 2:
		*
		*                 5
		*
		*            -5
		*
		*       -10     2
		*
		*/

		TreeNode<Integer> treeTwo = new TreeNode<>(5);
		treeTwo.insert(-5);
		treeTwo.insert(-10);
		treeTwo.insert(2);
		System.out.println("Preorder traversal of Tree 2:");
		treeTwo.preOrder();
		System.out.println("Inorder traversal of Tree 2");
		treeTwo.inOrder();
		System.out.println("Postorder traversal of Tree 2:");
		treeTwo.postOrder();

		/* Tree 3:
		*                       15
		*
		*                   -5       20
		*
		*               -10     5   16
		*
		*                     2
		*/

		final TreeNode<Integer> treeThree = new TreeNode<>(15);
		Arrays.asList(-5, 20, -10, 5, 16, 2).forEach(treeThree::insert);
		System.out.println("Preorder traversal of Tree 3:");
		treeThree.preOrder();
		System.out.println("Inorder traversal of Tree 3:");
		treeThree.inOrder();
		System.out.println("Postorder traversal of Tree 3:");
		treeThree.postOrder();
		assert Stream.of(-5, 20, -10, 5, 16, 2).allMatch(treeThree::search) : "Some of the integers inserted were not found in the tree! What calamity :(";

		/*
		 *	Finally, a randomy generated tree for stress tests.
		*/
		final TreeNode<Integer> stressedTree = new TreeNode<>(START_INT + (END_INT - START_INT) / 2);
		shuffle(IntStream.range(START_INT, END_INT).boxed().collect(Collectors.toList())).forEach(stressedTree::insert);
		System.out.println("Preorder traversal for stress test tree:");
		stressedTree.preOrder();
		System.out.println("Inorder traversal for stress test tree:");
		stressedTree.inOrder();
		System.out.println("Postorder traversal for stress test tree:");
		stressedTree.postOrder();
	}

	// Just a shuffling with returning pointer to list.
	private static <T> List<T> shuffle(final List<T> list)
	{
		Collections.shuffle(list);
		return list;
	}

}
