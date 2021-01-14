package hackerrank;
import java.util.ArrayList;
import java.util.List;
/**
 * Given a non-negative integer {@code k}, return all the strings consisting of the characters &quot; 1 &quot; and &quot; 2 &quot; such that the sum of all the digits is
 * {@code k}. The output list needs to be sorted in lexicographical order.
 *
 * Constraints:
 *  <ul>
 *      <li>0 &#8804; {@code k} &#8804; 20</li>
 *  </ul>
 */
public class SumWithOnesAndTwos
{
	public static List<String> sumKStrings(int k)
	{
		// Write your code here

    /* Jason's notes:

        - Considering the size of input, an exponential complexity (2^k) backtracking solution is acceptable.

        - I will also *not* use a StringBuffer to build the accepted strings, because my depth will be at most 20 and using Strings
        will lead to a cleaner implementation, as we will see.

        - Logic: Maintain a String called "currentSolution", which will play the role of the accumulator. The current sum that is
        implicitly accumulated by testing different values (following the tree's branches, effectively) will also be maintained.
        If by adding the current sum to the value we tested yields k, we can add the built string to our returned list.

        - By testing '1's before '2's I have guarantees of lexicographic addition of the paths. Lists add to the end.

        - Time: 2^k

        - Space: There are quadratically many ways to write a positive integer as a sum of positive integers, so the list will have
        O(k^2) elements. Now, every one varies in length, but the biggest one is k (1111.....1). So I would say that this is O(k^2)
        if we count strings, O(k^3) if we count characters. Favorable constants in both cases.\

        - Still was not able to finish within 45 minutes It took me close to 55 :( At least I'm passing all test cases.
    */

		final List<String> retVal = new ArrayList<>();
		storeSolutions(k, retVal);
		return retVal;
	}

	private static void storeSolutions(final int targetNum, final List<String> accum)
	{
		storeSolutions(targetNum, 0, "", accum);            // Starting with sum=0 and empty String as initial values.
	}

	private static void storeSolutions(final int targetNum, final int currentSum,
	                                   final String currentPath, final List<String> accum)
	{
		boolean storedCurrentPath = false;
		// Check 1 first, to preserve lexicographic order of addition into accum.
		if(currentSum < targetNum)          // Try 1 again.
		{
			storeSolutions(targetNum, currentSum + 1, currentPath + "1", accum);
		}
		else if(currentSum == targetNum)     // Selecting 1 achieved our goal. Add path to argument collection.
		{
			accum.add(currentPath);
			storedCurrentPath = true;
		}

		// Now check 2. We definitely need to, in spite of what happened with '1', therefore no else-if, but a simple if.
		if(currentSum + 1 < targetNum)          // Is + 1 strictly smaller than targetNum? Then try +2!
		{
			storeSolutions(targetNum, currentSum + 2, currentPath + "2", accum);
		}
		else if(currentSum == targetNum && !storedCurrentPath)
		{
			accum.add(currentPath);
		}
		// No reason to have cases for '>', since we just exit the stack frame in those situations.
	}
}
