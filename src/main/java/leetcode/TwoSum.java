package leetcode;
import lombok.NonNull;

import java.util.*;
/**
 * https://leetcode.com/problems/two-sum
 * @see TwoSumII
 * @see ThreeSum
 */
public class TwoSum
{
	private static List<Integer> findPairs(@NonNull List<Integer> nums, final int target)
	{
		// We are guaranteed inputs with *exactly* one solution.
		final Map<Integer, Integer> mySet = new HashMap<>();
		for(int i = 0; i < nums.size(); i++)
		{
			final int diff = target - nums.get(i);
			if(mySet.containsKey(diff))
			{
				return Arrays.asList(mySet.get(diff), i);
			}
			else
			{
				mySet.put(nums.get(i), i);
			}
		}
		throw new RuntimeException("We were guaranteed exactly one solution!");
	}

	public static void main(String[] args)
	{
		System.out.println(findPairs(Arrays.asList(3, 3), 6));
		System.out.println(findPairs(Arrays.asList(3, 2, 4), 6));
		System.out.println(findPairs(Arrays.asList(2, 7, 11, 15), 9));
	}
}
