package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import static util.TestingFramework.checkEquality;
import static util.TestingFramework.errMsg;

//https://leetcode.com/problems/contains-duplicate-iii/
public class ContainsDuplicateThree {

    private static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) { // k : difference in indices, t: difference in values
        assert k  >= 1 && t >= 0 && nums!= null : "We were given bad arguments: nums=" + Arrays.toString(nums) + ", k = " + k + ", t = " + t + ".";
        // We will bin every given element to a provided bucket.
        Map<Integer, Integer> buckets = new HashMap<>();
        int bucketWidth = t + 1; // Make buckets large enough to hold keys t ints apart.
        for(int i = 0; i < nums.length; i++){
            int bucketID = getBucketId(nums[i], bucketWidth);
            if(buckets.containsKey(bucketID) || nearbyBucketsContainAlmostDuplicate(buckets, bucketID, bucketWidth, nums[i])) // Same bucket ID means you have another key being t positions apart from you
                return true;
            buckets.put(bucketID, nums[i]);
            if(i - k >= 0)
                buckets.remove(getBucketId(nums[i - k], bucketWidth));  // Only maintain a width of k for our buckets.
        }
        return false;
    }

    private static boolean nearbyBucketsContainAlmostDuplicate(Map<Integer, Integer>buckets, int bucketID, int bucketWidth, int key){
        return (buckets.containsKey(bucketID - 1) && Math.abs(buckets.get(bucketID - 1) - key) < bucketWidth )
                                                ||
                (buckets.containsKey(bucketID + 1) && Math.abs(buckets.get(bucketID + 1) - key) < bucketWidth );
    }

    private static int getBucketId(int key, int bucketWidth){
        return (key < 0) ? ( (key + 1 )/ bucketWidth)  - 1 : key / bucketWidth; // We want all keys in {-w, -w + 1, .... , -2, -1 } to go to bucket with id - 1.
    }


    public static void main(String[] args){
        int testCounter = -1;
        checkEquality(true, containsNearbyAlmostDuplicate(IntStream.of(1, 2, 3, 1).toArray(), 3, 0), errMsg(++testCounter));
        checkEquality(false, containsNearbyAlmostDuplicate(IntStream.of(1, 2, 3, 1).toArray(), 2, 0), errMsg(++testCounter));
        checkEquality(true, containsNearbyAlmostDuplicate(IntStream.of(1, 0, 1, 1).toArray(), 1, 2), errMsg(++testCounter));
        checkEquality(true, containsNearbyAlmostDuplicate(IntStream.of(1, 0, 1, 1).toArray(), 2, 2), errMsg(++testCounter));
        checkEquality(true, containsNearbyAlmostDuplicate(IntStream.of(1, 0, 1, 1).toArray(), 2, 0), errMsg(++testCounter));
        checkEquality(true, containsNearbyAlmostDuplicate(IntStream.of(1, 0, 1, 1).toArray(), 1, 0), errMsg(++testCounter));
        checkEquality(false, containsNearbyAlmostDuplicate(IntStream.of(5, 10, 16, -20, 25, 5, 16).toArray(), 3, 3), errMsg(++testCounter));
        checkEquality(false, containsNearbyAlmostDuplicate(IntStream.of(-10, -7, 0, -3, 10, 7, 3).toArray(), 7, 2), errMsg(++testCounter));
        checkEquality(false, containsNearbyAlmostDuplicate(IntStream.of(10, -20, 0, 7, -4, 16, -1).toArray(), 1, 4),  errMsg(++testCounter));
        checkEquality(true, containsNearbyAlmostDuplicate(IntStream.of(10, -20, 0, 7, -4, 16, -1).toArray(), 2, 4), errMsg(++testCounter));
        System.out.println("Passed all tests.");
    }
}


