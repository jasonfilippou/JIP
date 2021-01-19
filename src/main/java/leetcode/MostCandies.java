package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/kids-with-the-greatest-number-of-candies/
public class MostCandies {

    private static List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        assert candies != null && candies.length >= 2 && candies.length <= 100 && extraCandies >= 1 && extraCandies <= 50:
                "Bad inputs: candies =  " + Arrays.toString(candies) + ", extraCandies = " + extraCandies + ".";
        List<Boolean> retVal = new ArrayList<>(candies.length); // Pre-allocate for efficiency
        // We will do two passes, maintaining O(n) complexity, albeit with a constant of 2 up front.
        int maxCandies = getMaxCandies(candies);
        for (int candy : candies)  // enhanced for with primitives ???
            retVal.add((candy + extraCandies >= maxCandies));
        return  retVal;
    }

    private static int getMaxCandies(int[] candies){
        int max = candies[0]; // Asserted safe by top-level method
        for(int i = 1; i < candies.length; i++)
            if(candies[i] > max)
                max = candies[i];
        return max;
    }

    public static void main(String[] args){
        System.out.println(kidsWithCandies(new int[]{1, 1}, 2));
        System.out.println(kidsWithCandies(new int[]{1, 3}, 1));
        System.out.println(kidsWithCandies(new int[]{2, 1, 6, 8, 9}, 3));
        System.out.println(kidsWithCandies(new int[]{2, 1, 6, 8, 9}, 2));
        System.out.println(kidsWithCandies(new int[]{2, 1, 6, 8, 9}, 1));
    }
}
