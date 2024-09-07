package leetcode;

import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static util.TestingFramework.checkArraysEqual;

/**
 * <p>
 * We are given an array of non-overlapping integers intervals, where intervals[i] = [start_i, end_i] represents
 * the start and the end (both inclusive) of the ith interval. This array is sorted by ascending order of start_i.
 * <p>
 * We are also given an interval newInterval = [start, end].
 * <p>
 * The task is to insert the new interval into the list of existing intervals. The array should still be sorted
 * by ascending start time and consist of non-overlapping intervals.
 *
 * <a href = "https://leetcode.com/problems/insert-interval/submissions/1380401873/">Leetcode 57</a></>
 */
public class InsertInterval {

    private static int[][] insertInterval(int[][] intervals, int[] newInterval) {
        assert intervals != null && newInterval != null && newInterval.length == 2: "Problem constraints violated";
        List<int[]> retVal = new ArrayList<>();
        int i = 0, n = intervals.length;

        // First, insert all intervals that finish before we begin.
        while (i < n && intervals[i][1] < newInterval[0]) {
            retVal.add(intervals[i++]);
        }

        // Consolidate all intervals that overlap with our new one,
        // by performing successive merges. Insert the merged interval.
        while(i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        retVal.add(newInterval);
        // Lastly, insert all intervals that begin after the merged interval finishes.
        while(i < n){
            retVal.add(intervals[i++]);
        }
        return retVal.toArray(new int[retVal.size()][]);
    }

    public static void main(String[] args) {
        Map<int[][], int[]> testCases = Map.of(
            new int[][]{ {1, 3}, {6, 9} }, new int[]{2, 5}, // LC1
            new int[][]{ {2, 5}, {10, 11}, {13, 20}, {28, 32}}, new int[]{11, 28}, // My own
            new int[][]{ {1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}}, new int[]{4, 8} // LC2
        );
        testCases.forEach((intervals, newInterval) -> System.out.println("Inserting interval " + Arrays.toString(newInterval) +
                " into intervals " + Arrays.stream(intervals).map(Arrays::toString).collect(Collectors.joining(", ")) + " results in merged intervals: "
                + Arrays.stream(insertInterval(intervals, newInterval)).map(Arrays::toString).collect(Collectors.joining(", "))));
    }
}
