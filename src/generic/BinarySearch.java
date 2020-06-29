package generic;

import java.util.stream.IntStream;

import static util.AssertionChecker.check;

public class BinarySearch {

    public static int binSearch(int[] arr, int key) { // Do recursive by default.
        return binSearch(arr, key, true);
    }

    private static int binSearch(int[] arr, int key, boolean recursive){
        assert arr != null : "Provided null array.";
        return recursive ? binSearchRec(arr, key) : binSearchIter(arr, key);
    }

    private static int binSearchRec(int[] arr, int key){
        return binSearchRec(arr, key, 0, arr.length - 1);
    }

    private static int binSearchRec(int[] arr, int key, int start, int end){
        if(start > end)
            return -1;
        int mid = (start + end) / 2;
        if(arr[mid] == key)
            return mid;
        else if(arr[mid] > key)
            return binSearchRec(arr, key, 0, mid - 1);
        else
            return binSearchRec(arr, key, mid + 1, end);
    }

    private static int binSearchIter(int[] arr, int key){
         int start = 0, end = arr.length -1;
         while(start <= end){
             int mid = (start + end) / 2;
             if(arr[mid] == key)
                 return mid;
             else if(arr[mid] < key)
                 start = mid + 1;
             else
                 end = mid - 1;
         }
         return -1;
    }

    public static void main(String[] args) {
        int testCtr = 0;
        check( binSearch(IntStream.of(0, 2, 4).toArray(), 0, true) == 0 , failureString(++testCtr));
        check( binSearch(IntStream.of(0, 2, 4).toArray(), 0, false) == 0, failureString(++testCtr));
        check( binSearch(IntStream.of(0, 2, 4).toArray(), 2, true) == 1, failureString(++testCtr));
        check( binSearch(IntStream.of(0, 2, 4).toArray(), 2, false) == 1, failureString(++testCtr));
        check( binSearch(IntStream.of(0, 2, 4).toArray(), 4, true) == 2, failureString(++testCtr));
        check( binSearch(IntStream.of(0, 2, 4).toArray(), 4, false) == 2, failureString(++testCtr));
        check( binSearch(IntStream.of(12, 15, 20, 22, 30, 40, 50, 52, 53).toArray(), 20, true   ) == 2, failureString(++testCtr));
        check( binSearch(IntStream.of(12, 15, 20, 22, 30, 40, 50, 52, 53).toArray(), 20, false   ) == 2, failureString(++testCtr));
        check( binSearch(IntStream.of(12, 15, 20, 22, 30, 40, 50, 52, 53).toArray(), 50, true   ) == 6, failureString(++testCtr));
        check( binSearch(IntStream.of(12, 15, 20, 22, 30, 40, 50, 52, 53).toArray(), 50, false   ) == 6, failureString(++testCtr));
        check( binSearch(IntStream.of(12, 15, 20, 22, 30, 40, 50, 52, 53).toArray(), 21, true  ) == -1, failureString(++testCtr));
        check( binSearch(IntStream.of(12, 15, 20, 22, 30, 40, 50, 52, 53).toArray(), 21, false  ) == -1, failureString(++testCtr));

        System.out.println("All tests passed!");
    }

    private static String failureString(int counter){
        return "Failed test #" + counter + ".";
    }
}
