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

        check( 0,binSearch(IntStream.of(0, 2, 4).toArray(), 0, true) , failureString(++testCtr));
        check( 0,binSearch(IntStream.of(0, 2, 4).toArray(), 0, false) , failureString(++testCtr));

        check( 1,binSearch(IntStream.of(0, 2, 4).toArray(), 2, true) , failureString(++testCtr));
        check( 1,binSearch(IntStream.of(0, 2, 4).toArray(), 2, false) , failureString(++testCtr));

        check( 2,binSearch(IntStream.of(0, 2, 4).toArray(), 4, true) , failureString(++testCtr));
        check( 2 ,binSearch(IntStream.of(0, 2, 4).toArray(), 4, false) , failureString(++testCtr));

        check( 2, binSearch(IntStream.of(12, 15, 20, 22, 30, 40, 50, 52, 53).toArray(), 20, true   ), failureString(++testCtr));
        check( 2, binSearch(IntStream.of(12, 15, 20, 22, 30, 40, 50, 52, 53).toArray(), 20, false   ), failureString(++testCtr));

        check( 6, binSearch(IntStream.of(12, 15, 20, 22, 30, 40, 50, 52, 53).toArray(), 50, true   ), failureString(++testCtr));
        check( 6, binSearch(IntStream.of(12, 15, 20, 22, 30, 40, 50, 52, 53).toArray(), 50, false   ), failureString(++testCtr));

        check( -1, binSearch(IntStream.of(12, 15, 20, 22, 30, 40, 50, 52, 53).toArray(), 21, true   ), failureString(++testCtr));
        check( -1, binSearch(IntStream.of(12, 15, 20, 22, 30, 40, 50, 52, 53).toArray(), 21, false   ), failureString(++testCtr));

        System.out.println("All tests passed!");
    }

    private static String failureString(int counter){
        return "Failed test #" + counter + ".";
    }
}
