package leetcode;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
/**
 * <a href="https://leetcode.com/problems/the-k-weakest-rows-in-a-matrix/">Problem 1337</a>
 */
public class FindKWeakestRows
{
	static class Pair
	{
		int score;
		int rowIndex;

		Pair(int score, int rowIndex)
		{
			this.score = score;
			this.rowIndex = rowIndex;
		}
	}

	public static void main (String[] args) {
		// System.out.println("Hello Java");
	}

	public static int[] findKWeakest(final int[][] matrix, final int k)
	{

		assert matrix != null && matrix.length >= 1 && k > 1 : "Bad args.";
		final int m = matrix.length, n = matrix[0].length;
		final PriorityQueue<Pair> pqueue = new PriorityQueue<>(Comparator.comparingInt(x -> x.score));
		for(int r = 0; r < m; r++)
		{
			int rowScore = findFirstZero(matrix[r]);
			if(rowScore == -1)  rowScore = n;
			pqueue.add(new Pair(rowScore, r));
		}
		return Arrays.copyOfRange(Arrays.stream(pqueue.toArray(new Pair[]{})).mapToInt(x->x.score).toArray(), 0, k);
	}

	private static int findFirstZero(final int [] array)
	{
		int left = 0;
		int right = array.length -1;
		while(left <= right)
		{
			int mid = left + (right - left)/2;
			if(array[mid] == 0)
			{
				right = mid;
			}
			else
			{
				left = mid;
			}
		}
		if(array[left]== 0) return left;
		if(array[right]==0) return right;

		return -1;
	}

}
