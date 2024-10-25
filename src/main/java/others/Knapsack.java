package others;

import org.javatuples.Triplet;
import org.javatuples.Tuple;

import java.util.HashSet;
import java.util.Set;

public class Knapsack {

    public static void main(String[] args) {
        Triplet<int[], int[], Integer> params = new Triplet<>(new int[]{1, 2, 5, 6}, new int[]{2, 3, 4, 5}, 8);
        int n = params.getValue0().length, W = params.getValue2();
        int[][] array = buildKnapsackArray(params.getValue0(), params.getValue1(), params.getValue2());
        System.out.println("Maximum profit is: " + array[n][W]);
        Set<Integer> includedObjects = generateIncludedObjects(array, params.getValue1());
        System.out.println("Including objects: " + includedObjects);
    }

    private static int[][] buildKnapsackArray(int[] prices, int[] weights, int W){
        int n = prices.length;
        prices = prependWithValue(prices, 0);
        weights = prependWithValue(weights, 0);
        int[][] retVal = new int[n+1][W + 1]; // Initially swiped to zero
        for(int i = 1; i < n + 1; i++){
            for(int j = 1; j < W + 1; j++){
                if(i == 0 || j == 0){
                    retVal[i][j] = 0;
                } else if(weights[i] <= j){
                    // Maximum of either not taking the object or taking it.
                    retVal[i][j] = Math.max(retVal[i - 1][j], retVal[i - 1][j - weights[i]] + prices[i]);
                } else {
                    retVal[i][j] = retVal[i - 1][j];
                }
            }
        }
        return retVal;
    }

    private static int[] prependWithValue(int[] value, int valueToAdd){
        int[] retVal = new int[value.length + 1];
        retVal[0] = valueToAdd;
        System.arraycopy(value, 0, retVal, 1, retVal.length - 1);
        return retVal;
    }

    private static Set<Integer> generateIncludedObjects(int[][] knapsackArray, int[] weights){
        Set<Integer> includedObjects = new HashSet<>();
        int n = knapsackArray.length, w = knapsackArray[0].length - 1;
        for(int i = n - 1; i > 0; i--){
            if(knapsackArray[i - 1][w] != knapsackArray[i][w]) { // This item was selected
                includedObjects.add(i - 1);
                w -= weights[i - 1];
            }
        }
        return includedObjects;
    }
}
