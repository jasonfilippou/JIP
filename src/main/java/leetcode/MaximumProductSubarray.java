package leetcode;

import java.util.Arrays;
import java.util.Map;

import static util.TestingFramework.checkEquality;

/**
 * Given an array of integers nums, return the maximum product that can be yielded by multiplying
 * the elements of a contiguous subarray of nums.
 * Constraints:
 *      1 <= |nums| <= 2*10^4
 *      -10 <= nums[i] <= 10
 *
 * The test cases are designed so that the answer fits in a 32-bit integer.
 *
 * @author Jason
 *
 * <a href="https://leetcode.com/problems/maximum-product-subarray/description/">Leetcode #152</a>
 */
public class MaximumProductSubarray {

    /* We will maintain both a maximum product scanned so far and a minimum one,
       because the possible presence of negative ints will make the maximum product
       equal to the minimum times the negative factor.
     */
    private static int maxProdSubarray(int[] nums){
        assert nums != null && nums.length >= 1 && nums.length <= 20_000 : "Problem constraints violated";
        int maxProd = nums[0];
        int minProd = nums[0];
        int result = nums[0];
        for(int i = 1; i < nums.length; i++){
            if(nums[i] >= 0){
                maxProd = Math.max(nums[i], nums[i] * maxProd);
                minProd = Math.min(nums[i], nums[i] * minProd);
            } else {
                int temp = maxProd;
                maxProd = Math.max(nums[i]* minProd, nums[i]);
                minProd = Math.min(nums[i] * temp, nums[i]);
            }
            result = Math.max(result, maxProd);
        }
        return result;
    }

    public static void main(String[] args) {
        Map<int[], Integer> testCases = Map.of(
                new int[]{2, 3, -2, 4}, 6,
                new int[]{-2, 0, -1}, 0,
                new int[]{0}, 0,
                new int[]{5, 10, 0, -1, -10, 0, 0, -2, 3}, 50
        );
        testCases.forEach((nums, prod) -> checkEquality(maxProdSubarray(nums), prod, "Maximum " +
                "subarray product for array: " + Arrays.toString(nums) + " is: " + prod + " (found: " + maxProdSubarray(nums)));
    }
}
