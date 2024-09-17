package leetcode;

public class FindRightInterval {

    public int[] findRightInterval(int[][] intervals) {
        assert intervals != null && intervals.length > 0;
        int[][] intervalsSorted = retrieveSortedCopy(intervals);

    }

    private int[][] retrieveSortedCopy(int[][] intervals){
        int[][] intervalsSorted = new int[intervals.length][2];
        System.arraycopy(intervals, 0, intervalsSorted,  0, intervals.length);
        Arrays.sort(intervalsSorted, (int1, int2) ->{
            if(int1[0] > int2[0]){
                return 1;
            } else if(int1[0] == int2[0]){
                return 0;
            } else {
                return -1;
            };
        }
        return intervalsSorted;
    }
}
