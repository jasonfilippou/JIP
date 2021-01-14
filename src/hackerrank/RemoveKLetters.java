package hackerrank;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RemoveKLetters
{
	public static List<String> removeKStrings(String s, int k)
	{
		/*
            Jason's notes:

            - Size of input for this problem was 0 to 16, which is tractable for a backtracking solution.

            - Algorithm:

                (*) We will backtrack on the characters of the input string. Every level of the recursion will contain information
                about which the currently scanned character is, and whether at the current frame we have the capacity to "erase"
                more characters.

                (*) Since a given character might have been "removed" in previous indices of the string, we will maintain a
                Set of visited characters. This is O(n) space, since we can be given a string with completely distinct
                characters (like abcdef).

                (*) At every recursion level:

                    (-) If the currently examined character is beyond the length of the string, just admit the solution that
                    has been built in accum. Otherwise, you do the rest of the stuff in this list.

                    (-) If the examined character is in the visited nodes hash, you need to simply erase him and examine the
                    next one in the upcoming recursive call. DO NOT decrement k.

                    (-) If not, we need to ask if we are still allowed to erase characters. To do this, we will simply decrement k
                    in the frames that we must.

                        (+) If currentK > 0, we have two branches: the left one will NOT select the character, by putting him in the
                        hash and DECREMENTING currentK in the recursive call. Once the recursive call returns (this one, the left one)
                        we must erase the current character from our set.

                        (*) If currentK = 0, we are in a situation where we are encountering a character that we have not erased
                        before, but we are still not allowed to erase it, because we have erased too many characters. So we can only
                        branch out once, and we keep the character.

                    (-) To build towards acceptable solution in the leaves of the recursion, I will use the String class. The length
                    of the strings I receive is small, so I will have at most 15 String concatenations down the recursion tree. It's ok to not use a
                    StringBuffer here. It will also make the code cleaner.
        */

		final List<String> retVal = new ArrayList<>();
		final String solutionAccum = "";
		final Set<Character> erasedChars = new HashSet<>();
		admitStrings(s, k, 0, erasedChars, solutionAccum, retVal);
		return retVal;
	}

	private static void admitStrings(final String s, final int k, final int currIdx, final Set<Character> erasedChars,
	                                 final String accum, final List<String> solutions)
	{
		if(currIdx == s.length())
		{
			solutions.add(accum);
		}
		else
		{
			final char currentChar = s.charAt(currIdx);
			if(erasedChars.contains(currentChar))                                       // Amortized O(1) complexity
			{
				admitStrings(s, k, currIdx + 1, erasedChars, accum, solutions);         // Notice that accum is untouched.
			}
			else
			{
				if(k > 0)                   // We are allowed to branch twice: erasing currentChar on left, keeping him on right.
				{
					// To maintain lexicographical order, we will first recurse "left", "erasing" current char
					// by putting him in hashset.
					erasedChars.add(currentChar);
					admitStrings(s, k-1, currIdx + 1, erasedChars, accum, solutions);      // accum still untouched, since we erase

					// Now we will go right. To do this, we need to remove current char from erased chars before we recurse.
					erasedChars.remove(currentChar);
					admitStrings(s, k, currIdx + 1, erasedChars, accum + currentChar, solutions); // Now we touch accum!
					// Nothing to do when we ascend from the right!
				}
				else            // We are not allowed to branch twice because we have already deleted enough chars.
				{
					admitStrings(s, k, currIdx + 1, erasedChars, accum + currentChar, solutions);
				}
			}
		}
	}
}
