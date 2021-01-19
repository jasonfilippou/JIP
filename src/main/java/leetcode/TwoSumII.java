package leetcode;
import lombok.NonNull;

import java.util.Arrays;
import java.util.List;
/**
 * https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
 * @see TwoSum
 * @see ThreeSum
 */
public class TwoSumII
{
	private static List<Integer> findPairs(@NonNull List<Integer> nums, final int target)
	{
		// We are guaranteed input sorted in ascending order, with *exactly* one solution.
		// Two-index solution possible now that input is sorted.
		assert nums.size() >= 2 : "If you want me to return discrete indices, better give me a list larger than 1 element.";
		int leftIdx = 0, rightIdx = nums.size() - 1;
		while(leftIdx < rightIdx)   // Strict inequality since I always want to return indices such that left < right.
		{
			final int currentSum = nums.get(leftIdx) + nums.get(rightIdx);
			if(currentSum < target)
			{
				leftIdx++;
			}
			else if(currentSum == target)
			{
				return Arrays.asList(leftIdx, rightIdx);
			}
			else
			{
				rightIdx--;
			}
		}
		throw new RuntimeException("We were guaranteed exactly one solution!");
	}

	public static void main(String[] args)
	{
		System.out.println(findPairs(Arrays.asList(1, 2, 4), 6));
		System.out.println(findPairs(Arrays.asList(0, 1, 2, 3, 3), 6));
		System.out.println(findPairs(Arrays.asList(2, 7, 11, 15), 9));
		System.out.println(findPairs(Arrays.asList(0, 0), 0));
		System.out.println(findPairs(Arrays.asList(0, 1), 1));
		System.out.println(findPairs(Arrays.asList(0, 1, 3), 3));
		System.out.println(findPairs(Arrays.asList(0, 1, 1), 2));
	}
}
