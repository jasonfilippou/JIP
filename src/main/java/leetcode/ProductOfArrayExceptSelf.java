package leetcode;

import util.TestingFramework;

import java.util.Arrays;
import java.util.Map;

import static util.TestingFramework.checkArraysEqual;
import static util.TestingFramework.checkEquality;

/**
 * Given an array of integers <b>nums</b>, return an array <b>answer</b>, where answer[i] is equal to the product of all elements of
 * nums EXCEPT nums[i].
 * <p>
 * You should aim for a linear - time solution and you cannot use the division operator.
 * <p>
 * The test cases are constrained so that the the product of any prefix or suffix of nums fits in a 32-bit integer.
 * <p>
 * Constraints:
 * <ul>
 *  <li> 2 <= |nums| <= 10^5
 *  </li> -30 <= nums[i] <= 30
 * </ul>>
 *
 * <a hre
 */
public class ProductOfArrayExceptSelf {

    /* We will first provide a linear extra space approach. */
    private static Integer[] linearExtraSpaceApproach(Integer[] nums){
        Integer[] left = new Integer[nums.length];
        Integer[] right = new Integer[nums.length];

        // Populate left array first
        populateLeftArray(left, nums);

        // Now populate right array
        populateRightArray(right, nums);

        // Final result is the memberwise product of these two arrays.
        return multiplyArrays(left, right);
    }

    private static void populateLeftArray(Integer[] leftArray, Integer[] nums){
        int currProd = 1;
        leftArray[0] = currProd;
        for(int i = 1; i < leftArray.length; i++){
            currProd *= nums[i - 1];
            leftArray[i] = currProd;
        }
    }

    private static void populateRightArray(Integer[] rightArray, Integer[] nums){
        int currProd = 1;
        rightArray[rightArray.length - 1] = currProd;
        for(int i = rightArray.length - 2; i > -1; i--){
            currProd *= nums[i + 1];
            rightArray[i] = currProd;
        }
    }

    private static Integer[] multiplyArrays(Integer[] arrayOne, Integer[] arrayTwo){
        assert arrayOne != null && arrayTwo != null && arrayOne.length == arrayTwo.length :
                "Can only multiply arrays of the same length.";
        Integer[] result = new Integer[arrayOne.length];
        for(int i = 0; i < result.length; i++){
            result[i] = arrayOne[i] * arrayTwo[i];
        }
        return result;
    }

    /* Now we will provide a constant extra space approach. */
    private static Integer[] constantExtraSpaceApproach(Integer[] nums){
        Integer[] output = new Integer[nums.length];
        output[0] = 1;
        for(int i = 1; i < output.length; i++){
            output[i] = output[i - 1] * nums[i - 1];
        }
        int right = 1; // The constant extra storage
        for(int i = output.length - 1; i >= 0; i--){
            output[i] *= right;
            right *= nums[i];
        }
        return output;
    }

    public static void main(String[] args) {
        Map<Integer[], Integer[]> testCases = Map.of(
                new Integer[]{1, 2, 3, 4}, new Integer[]{24, 12, 8, 6},
                new Integer[]{5, 2, 3, 4}, new Integer[]{24, 60, 40, 30},
                new Integer[]{-1, 1, 0, -3, 3}, new Integer[]{0, 0, 9, 0, 0}
        );
        // Choose between either approach
        testCases.forEach((input, output) -> checkArraysEqual(linearExtraSpaceApproach(input), output));
    }
}
