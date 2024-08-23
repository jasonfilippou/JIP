package leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

import static util.TestingFramework.checkEquality;
import static util.TestingFramework.errMsg;

// https://leetcode.com/problems/contains-duplicate/
public class ContainsDuplicateOne {

    // This has O(n) extra space, but I don't see any other way to drop the time complexity from quadratic to linear.
    private static boolean containsDuplicate(int[] nums) {
        assert nums != null && nums.length >=2 : "Bad array given: " + Arrays.toString(nums) + ".";
        Set<Integer> myFilter = new HashSet<>();
        for(Integer i: nums)
            if(myFilter.contains(i)) // Amortized constant operation
                return true;
            else
                myFilter.add(i);    // Amortized constant operation
        return false;
    }

    public static void main(String[] args){
        int testCounter = -1;
        checkEquality(true, containsDuplicate(IntStream.of(1, 1).toArray()), errMsg(++testCounter));
        checkEquality(true, containsDuplicate(IntStream.of(1, 1, 2).toArray()), errMsg(++testCounter));
        checkEquality(true, containsDuplicate(IntStream.of(1, 2, 3, 1).toArray()), errMsg(++testCounter));
        checkEquality(false, containsDuplicate(IntStream.of(1, 2, 3, 4).toArray()), errMsg(++testCounter));
        checkEquality(false, containsDuplicate(IntStream.of(1, -1, 2, -10, -20, 0, 4, 20).toArray()), errMsg(++testCounter));
        System.out.println("All tests passed!");
    }
}
