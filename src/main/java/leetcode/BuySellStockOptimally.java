package leetcode;

import java.util.Arrays;
import java.util.Map;

import static util.TestingFramework.checkEquality;

/**
 * Given an array prices, where prices[i] is the price of a given stock on the i-th day,
 * return the maximum profit that you can achieve by buying the stock on a given day, and
 * selling it on a different day in the future.
 * 1 <= |prices| <= 10^5
 * 0 <= prices[i] <= 10^4
 * <a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/">Leetcode link</a>
 * @author Jason
 */
public class BuySellStockOptimally {
    private static int maxProfit(int[] prices) {
        assert prices.length >= 1 : "We were guaranteed at least one day of prices";
        int minPrice = prices[0];
        int maxProfit = 0;
        for(int i = 1; i < prices.length; i++) {
            if(prices[i] < minPrice) {
                minPrice = prices[i];
            } else if(prices[i] - minPrice > maxProfit) {
                maxProfit = prices[i] - minPrice;
            }
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        final Map<int[], Integer> leetCodeTestCases = Map.of(
            new int[]{7,1,3,5,6,4}, 5,
            new int[]{7,6,4,3,1}, 0
        );
        leetCodeTestCases.forEach((prices, maxProfit) -> checkEquality(maxProfit(prices), maxProfit, "For the price list "
                + Arrays.toString(prices) + ", the maximum Profit is: " + maxProfit + ". Returned: " + maxProfit(prices)));
    }
}
