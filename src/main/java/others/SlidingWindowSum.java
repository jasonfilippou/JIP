package others;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Given an array nums and an integer k, print the sums of every k - large subarray of nums to sdout.
 *
 * @author jason
 */
public class SlidingWindowSum {

    /* We will implement a sliding window method to solve the problem in O(n-k). */
    private static void printSums(int[] nums, int k){
        assert 1 <= nums.length && k >= 1 && k <= nums.length : "Parameter error: n=" + nums.length + ", k=" + k;
        // Calculate sum of first k as normal:
        int sum = 0;
        int n = nums.length;
        for(int i = 0; i < k; i++){
            sum += nums[i];
        }
        System.out.println("From index 0 to index " + (k - 1)+ ", sum = " + sum);
        for(int i = 1; i <= n - k; i++){
            sum -= nums[i - 1];
            sum += nums[i + k - 1];
            System.out.println("From index " + i + " to index " + (i + k - 1) + ", sum = " + sum);
        }
    }

    public static void main(String[] args) {
        Map<int[], Integer> arraysAndOffsets = Map.of(
                new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 3,
                new int[]{2, 1, 5, 8, 9, 4, 6}, 3,
                new int[]{2, 1, 5, 8, 9, 4, 6}, 2
        );
        arraysAndOffsets.forEach((array, offset) -> {
            System.out.println("Printing sums of " + Arrays.toString(array) + " for k = " + offset);
            printSums(array, offset);
        });
    }
}
