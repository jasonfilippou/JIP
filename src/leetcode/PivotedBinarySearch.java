package leetcode;

import java.util.stream.IntStream;

import static util.AssertionChecker.check;

// https://leetcode.com/problems/search-in-rotated-sorted-array/
public class PivotedBinarySearch {

    private static int pivotedBinarySearch(int[] arr, int key){
        int pivot = getPivot(arr, 0, arr.length - 1);
        if(pivot == -1) return binSearch(arr, key, 0, arr.length - 1);
        if(key == arr[pivot])
            return pivot;
        if(arr[0] <= key)
            return binSearch(arr, key, 0, pivot - 1);
        return binSearch(arr, key, pivot + 1, arr.length - 1);
    }

    private static int getPivot(int[] arr) {
        return getPivot(arr, 0, arr.length - 1);
    }
    private static int getPivot(int[] arr, int low, int high){
        if(high < low) return -1;
        if(low == high) return low;
        int mid = (low + high) / 2;
        if(mid < high && arr[mid] > arr[mid + 1]) return mid;
        else if(mid > low && arr[mid -1] > arr[mid]) return mid-1;
        if(arr[low] >= arr[mid])
            return getPivot(arr, low, mid - 1);
        return getPivot(arr, mid + 1, high);
    }

    private static int binSearch(int[] arr, int key, int start, int end){
        if(start > end)
            return -1;
        int mid = (start + end) / 2;
        if(arr[mid] == key)
            return mid;
        else if(arr[mid] > key)
            return binSearch(arr, key, 0, mid - 1);
        else
            return binSearch(arr, key, mid + 1, end);
    }

    public static void  main(String[] args){
        int testCtr = 0;

        // Pivot tests first
        check(getPivot(IntStream.of(8, 12, 14, 0, 1).toArray()) == 2, failureString(++testCtr));
        check(getPivot(IntStream.of(1, 8, 12, 14, 0).toArray()) == 3, failureString(++testCtr));
        check(getPivot(IntStream.of(-5, -4, 2, 3, 11, 100, -90, -80).toArray()) == 5, failureString(++testCtr));
        check(getPivot(IntStream.of(-5, -4, 2, 3, 11, 100, -90).toArray()) == 5, failureString(++testCtr));
        System.out.println("Passed pivot tests...");

        // Bin Search tests
        check(pivotedBinarySearch(IntStream.of(8, 12, 14, 0, 1).toArray(), 8) == 0, failureString(++testCtr));
        check(pivotedBinarySearch(IntStream.of(8, 12, 14, 0, 1).toArray(), 12) == 1, failureString(++testCtr));
        check(pivotedBinarySearch(IntStream.of(8, 12, 14, 0, 1).toArray(), 14) == 2, failureString(++testCtr));
        check(pivotedBinarySearch(IntStream.of(8, 12, 14, 0, 1).toArray(), 0) == 3, failureString(++testCtr));
        check(pivotedBinarySearch(IntStream.of(8, 12, 14, 0, 1).toArray(), 1) == 4, failureString(++testCtr));
        check(pivotedBinarySearch(IntStream.of(4, 5, 6, 7, 0, 1, 2).toArray(), 0) == 4, failureString(++testCtr));
        System.out.println("Passed binary search tests...");

        System.out.println("Passed all tests!");
    }

    private static String failureString(int counter){
        return "Failed test #" + counter + ".";
    }
}
