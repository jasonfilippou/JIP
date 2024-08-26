package leetcode;

import org.javatuples.Pair;
import org.javatuples.Tuple;

import java.util.Map;

import static util.TestingFramework.checkArraysEqual;

/**
 * <p>
 * Given an array nums and an integer k>=0, rotate the array to the right by k positions.
 * <p>
 * Examples and full description on <a href=https://leetcode.com/problems/rotate-array/>Leetcode (#189)</a>
 */
public class RotateArray {

    private static class MyTuple extends Tuple{


        @Override
        public int getSize() {
            return 0;
        }
    }
    /* We will offer two solutions: one that uses linear extra space and one that does not. */
    private static void rotateWithLinearSpace(Integer[] nums, Integer k){
        assert k >= 0 : "We were guaranteed non-negative k.";
        int n = nums.length;
        k = k %n;
        Integer[] copy = new Integer[n];

        //Copy last k elements to the front
        System.arraycopy(nums, n - k, copy, 0, k);
        // Copy first n - k elements to the back
        System.arraycopy(nums, 0, copy, k, n - k);
        // Copy back entire array
        System.arraycopy(copy, 0, nums, 0, n);
    }

    private static void rotateInPlaceWithConstantSpace(Integer[] nums, Integer k){
        assert k >= 0 : "We were guaranteed non-negative k.";
        int n = nums.length;
        k = k %n;

        /* We will take advantage of the fact that right rotation by k positions is equal to the
         *   following sequence of operations:
         *   (1) Reversion of the entire array
         *   (2) Reversion of the first k positions
         *   (3) Reversion of the last n - k positions.
         */

        reverseArray(nums, 0, n - 1);
        reverseArray(nums, 0, k - 1);
        reverseArray(nums, k, n - 1);
    }

    private static <T> void reverseArray(T[] array, int startPosInc, int endPosInc){
        int i = startPosInc;
        int j = endPosInc;
        while(i < j){
            swap(array, i, j);
            i++;
            j--;
        }
    }

    private static <T> void swap(T[] array, int i, int j){
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        Map<Pair<Integer[], Integer>, Integer[]> testCases = Map.of(
                new Pair<>(new Integer[]{1, 2, 3, 4, 5, 6, 7}, 3), new Integer[]{5, 6, 7, 1, 2, 3, 4},
                new Pair<>(new Integer[]{-1, -100, 3, 99}, 2), new Integer[]{3, 99, -1, -100}
        );
        // Choose your preferred algorithm
        testCases.forEach((pair, outArray) -> {
                //rotateWithLinearSpace(pair.getValue0(), pair.getValue1());
                 rotateInPlaceWithConstantSpace(pair.getValue0(), pair.getValue1());
                checkArraysEqual(pair.getValue0(), outArray);
            });
    }
}
