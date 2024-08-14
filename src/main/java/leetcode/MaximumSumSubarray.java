package leetcode;

/**
 * Given an integer array nums, find the
 * subarray with the largest sum, and return its sum.
 *
 * Example 1:
 *
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: The subarray [4,-1,2,1] has the largest sum 6.
 * Example 2:
 *
 * Input: nums = [1]
 * Output: 1
 * Explanation: The subarray [1] has the largest sum 1.
 * Example 3:
 *
 * Input: nums = [5,4,-1,7,8]
 * Output: 23
 * Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.
 *
 * Leetcode #53 (https://leetcode.com/problems/maximum-subarray/description/)
 */
public class MaximumSumSubarray {
    class Solution {

        private static final int MINUS_INFINITY = -1_000_000_000; // -10^5 * 10^4

        public int maxSubArray(int[] nums) {
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
    }
}
