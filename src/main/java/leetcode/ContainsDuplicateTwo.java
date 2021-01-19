package leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

import static util.TestingFramework.check;
import static util.TestingFramework.errMsg;

// https://leetcode.com/problems/contains-duplicate-ii/
public class ContainsDuplicateTwo {

    private static boolean containsNearbyDuplicate(int[] nums, int k) {
        assert k >= 1 && nums != null : "Bad args given: nums = " + Arrays.toString(nums) + " and k = " + k + ".";
        Set<Integer> mySet = new HashSet<>();
        for(int i = 0; i < nums.length; i++){
            if(mySet.contains(nums[i]))
                return true;
            else
                mySet.add(nums[i]);
            if(i - k >= 0)
                mySet.remove(nums[i - k]);  // Only maintain a width of k for the window.
        }
        return false;
    }

    public static void main(String[] args){
        int testCounter = -1;
        check(true, containsNearbyDuplicate(IntStream.of(5, -2, 3, 5).toArray(), 3), errMsg(++testCounter));
        check(false, containsNearbyDuplicate(IntStream.of(5, -2, 3, 5).toArray(), 2), errMsg(++testCounter));
        check(true, containsNearbyDuplicate(IntStream.of(0, 10, 5, -2, 10, 5).toArray(), 3), errMsg(++testCounter));
        check(true, containsNearbyDuplicate(IntStream.of(0, 10, 5, -2, 10, -2).toArray(), 2), errMsg(++testCounter));
        check(false, containsNearbyDuplicate(IntStream.of(0, 10, 5, -2, 10, -2).toArray(), 1), errMsg(++testCounter));
        check(true, containsNearbyDuplicate(IntStream.of(0, 10, 5, 0, 10, 0).toArray(), 2), errMsg(++testCounter));
        System.out.println("All tests passed.");
    }
}
