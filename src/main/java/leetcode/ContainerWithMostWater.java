package leetcode;

import java.util.Arrays;
import java.util.Map;

import static util.TestingFramework.checkEquality;

/**
 * <p>
 * Given an array heights that represents non-negative height vertical lines, compute the maximum amount of water
 * that two of those vertical lines can represent. The lines are assumed to have zero width.
 * <a href="https://leetcode.com/problems/container-with-most-water/submissions/1375562929/">Leetcode 11</a>
 */
public class ContainerWithMostWater {

    private static int maxArea(int[] heights){
        assert heights != null && heights.length >= 2;
        // Two-pointer approach
        int first = 0, second = heights.length - 1, maxArea = 0;
        while(first < second){
            maxArea = Math.max(maxArea, computeArea(heights, first, second));
            // The shorter line can't contribute to bigger areas
            // any more, so get rid of it
            if(heights[first] < heights[second]){
                first++;
            } else {
                second--;
            }
        }
        return maxArea;
    }

    private static int computeArea(int[] heights, int i, int j){
        assert j > i : "j = " + j + " should be greater than i = " + i + "!";
        return (j - i) * Math.min(heights[i], heights[j]);
    }

    public static void main(String[] args) {
        Map<int[], Integer> testCases = Map.of(
            new int[]{1, 1}, 1,
            new int[]{2, 3, 5, 1}, 4,
            new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}, 49
        );
        testCases.forEach((heights, expectedMaxArea) -> checkEquality(maxArea(heights), expectedMaxArea,
                "Expected max area for heights array: " + Arrays.toString(heights) + ": " + expectedMaxArea + " (instead got : " +
                        maxArea(heights) + ")"));
    }
}
