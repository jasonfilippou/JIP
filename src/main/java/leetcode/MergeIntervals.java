package leetcode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <a href = https://leetcode.com/problems/merge-intervals/description/>Leetcode 56</a>"
 */
public class MergeIntervals {

    private static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (intOne, intTwo) -> {
            if(intOne[0] < intTwo[0]){
                return -1;
            } else if(intOne[0] > intTwo[0]){
                return 1;
            } else {
                return intOne[1] - intTwo[1];
            }
        });
        ArrayDeque<int[]> retVal = new ArrayDeque<>();
        retVal.add(intervals[0]);
        for(int i = 1; i < intervals.length; i++){
            int[] lastInterval = retVal.peekLast();
            assert lastInterval != null : "We should always have an interval in our stack";
            int[] currentInterval = intervals[i];
            if(currentInterval[0] <= lastInterval[1]){
                retVal.pollLast();
                retVal.addLast(mergeIntervals(currentInterval, lastInterval));
            } else{
                retVal.addLast(currentInterval);
            }
        }
        return retVal.toArray(new int[retVal.size()][2]);
    }

    private static int[] mergeIntervals(int[] first, int[] second) {
        return new int[]{
            Math.min(first[0], second[0]),
            Math.max(first[1], second[1])
        };
    }

    public static void main(String[] args) {
        List<int[][]> testCases = List.of(
            new int[][]{ {1,3}, {2,6}, {8,10}, {15,18} }, // LC1
            new int[][]{ {1, 4}, {4, 5}}, // LC2
            new int[][]{{2,7}, {0,3}, {10,14}, {13,17}, {13,17}, {19, 25}, {18, 26}} // Mine
        );
        testCases.forEach(interval -> System.out.println("Interval " + Arrays.stream(interval).map(Arrays::toString).collect(Collectors.joining(", ")) +
                " was merged into: " + Arrays.stream(merge(interval)).map(Arrays::toString).collect(Collectors.joining(", ")) + "."));
    }

}
