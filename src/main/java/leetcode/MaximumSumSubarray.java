package leetcode;

import java.util.Arrays;
import java.util.Map;

import static util.TestingFramework.checkEquality;

/**
 * Given an integer array nums, find the
 * subarray with the largest sum, and return its sum.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: The subarray [4,-1,2,1] has the largest sum 6.
 * Example 2:
 * <p>
 * Input: nums = [1]
 * Output: 1
 * Explanation: The subarray [1] has the largest sum 1.
 * Example 3:
 * <p>
 * Input: nums = [5,4,-1,7,8]
 * Output: 23
 * Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.
 * <p>
 * <a href="https://leetcode.com/problems/maximum-subarray/description/">Leetcode #53</a>
 */
public class MaximumSumSubarray {

    private static final int MINUS_INFINITY = -1_000_000_000; // -10^5 * 10^4

    private static int maxSubArraySum(int[] nums) {
        if(nums.length == 0){
            return MINUS_INFINITY;
        }
        int maxSum = nums[0];
        int currentSum = nums[0];
        for(int i = 1; i < nums.length; i++){
            if(currentSum < 0){
                currentSum = 0;
            }
            currentSum += nums[i];
            if(currentSum > maxSum){
                maxSum = currentSum;
            }
        }
        return maxSum;
    }

    public static void main(String[] args) {
        final Map<int[], Integer> leetcodeTestCases= Map.of(
            new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}, 6,
            new int[]{1}, 1,
            new int[]{5, 4, -1, 7, 8}, 23
        );
        leetcodeTestCases.forEach((array, maxSubarraySum) -> checkEquality(maxSubArraySum(array), maxSubarraySum,
                "Array " + Arrays.toString(array) + " has maximum subarray sum equal to " + maxSubarraySum + ". Returned: " + maxSubArraySum(array)));
    }
}
