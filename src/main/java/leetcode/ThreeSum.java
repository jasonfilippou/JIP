package leetcode;
import lombok.NonNull;

import java.util.*;
/**
 * https://leetcode.com/problems/3sum/
 * @see TwoSum
 * @see TwoSumII
 */
public class ThreeSum
{
	private static List<List<Integer>> findAllTriplets(@NonNull final List<Integer> nums)
	{
		assert nums.size() >= 3 : "To find unique triplets, we need a list of size 3 and above, instead given: "
		                          + nums + " with length " + nums.size();
		Collections.sort(nums);      // This will allow us to find duplicates faster.
		final List<List<Integer>> retVal = new LinkedList<>();  // At most it might hold 1,000 elements. Make linked to ease cache.
		for (int i = 0; i < nums.size(); i++)
		{
			final int currNum = nums.get(i);
			if(currNum > 0)     // Considering list is sorted, the
			{
				assert listOk(retVal) : "We encountered the positive number nums[" + i + "]=" + currNum + ", with the list containing: " + nums.toString();
				return retVal;
			}
			else if(i == 0 || !nums.get(i - 1).equals(nums.get(i))) // If the previous number is the same, we can produce duplicate triplets. Avoid this.
			{
				storeTriplets(nums, i, retVal);
			}
		}
		return  retVal;
	}

	private static boolean listOk(@NonNull final List<List<Integer>> myList)
	{
		return myList.size() >= 1 && myList.get(0).size() >= 3;
	}

	// Slightly modified solution for TwoSum. Better to get reference to nums Collection and a starting index to it
	// instead of running sublist() from the caller. Sublist() can have linear complexity.
	private static void storeTriplets(@NonNull final List<Integer> nums, final int startIdx,  @NonNull List<List<Integer>> tripletStore)
	{
		// This time, we don't need a map, but only a set.
		final Set<Integer> mySet = new HashSet<>();
		for(int i = startIdx + 1; i < nums.size(); i++)
		{
			final int diff = - nums.get(startIdx) - nums.get(i);
			if(mySet.contains(diff))
			{
				tripletStore.add(Arrays.asList(nums.get(startIdx), diff, nums.get(i))); // TODO: ensure order of items in list appropriate.
				i = advanceIndexIfNecessary(nums, i);
			}
			mySet.add(nums.get(i));
		}
	}

	// Skips any duplicates in sorted array so that we don't have same sols.
	private static int advanceIndexIfNecessary(final List<Integer> nums, int i)
	{
		while(i + 1 < nums.size() && nums.get(i).equals(nums.get(i + 1)))
		{
			i++;
		}
		return i;
	}

	public static void main(String[] args)
	{
		System.out.println(findAllTriplets(Arrays.asList(2, -1,-1,0,1,-4))); // Should print: [ [-1, -1, 2], [-1, 0, 1]]
		System.out.println(findAllTriplets(Arrays.asList(-1,0,-3,2,1,3))); // Should give: [ [-3, 0, 3], [-3, 1, 2], [-1, 0, 1]]
	}
}
