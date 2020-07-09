package leetcode;

import java.util.Stack;
import java.util.StringTokenizer;

import static util.AssertionChecker.check;
import static util.AssertionChecker.errMsg;

// https://leetcode.com/problems/reverse-words-in-a-string/

/**
 * This solution uses a custom tokenizing class to tokenize the string from end to start,
 * avoiding the need for an extra stack.
 *
 * @see ReverseWordsInStringWithTok
 * @see StringTokenizer
 * @see <a href="https://leetcode.com/problems/reverse-words-in-a-string/">https://leetcode.com/problems/reverse-words-in-a-string/</a>
 */
public class ReverseWordsInStringWithReverseTok {

    private static String reverseWords(String s){
        assert s != null : "We were provided a null String.";
        ReverseStringTokenizer tokenizer = new ReverseStringTokenizer(s);
        if(!tokenizer.hasMoreTokens()) return "";
        Stack<String> st = new Stack<>();
        while(tokenizer.hasMoreTokens())
            st.push(tokenizer.nextToken());
        StringBuilder builder = new StringBuilder();
        while(!st.isEmpty()) {
            builder.append(st.pop()).append(" ");
        }
        return builder.deleteCharAt(builder.length() - 1).toString(); // Delete the last space character.
    }

    public static void main(String[] args){
        int testCounter = -1;
        check("", reverseWords(""), errMsg(++testCounter)); // Trivial case 1
        check("", reverseWords(" "), errMsg(++testCounter)); // Trivial case 2
        check("", reverseWords("       "), errMsg(++testCounter)); // Trivial case 3
        check("Filippou Jason", reverseWords("Jason Filippou"), errMsg(++testCounter)); // A simple one.
        check("Filippou Jason", reverseWords("Jason Filippou "), errMsg(++testCounter)); // No leading whitespaces
        check("Filippou Jason", reverseWords(" Jason Filippou"), errMsg(++testCounter)); // No ending whitespaces
        check("Filippou Jason", reverseWords(" Jason Filippou "), errMsg(++testCounter)); // No leading *and* ending whitespaces
        check("Filippou Jason", reverseWords(" Jason       Filippou "), errMsg(++testCounter)); // No interim whitespaces.
        check("George is gay" , reverseWords("gay is George"), errMsg(++testCounter)); // George is gay.
        check("stout! and short teapot, little a I'm", reverseWords("I'm a little teapot, short and stout!"), errMsg(++testCounter)); // Longer
        check("! stout and short , teapot little a m I'", reverseWords("I' m a little teapot , short and stout !"), errMsg(++testCounter)); // oof
        System.out.println("Not only did I pass all tests, but George is still gay!");
    }

}

// This class only works with the whitespace character: ' '
class ReverseStringTokenizer{

    private final String originalString;
    private int currIdx;

    ReverseStringTokenizer(final String s){
        assert s != null : "Provided a null string";
        originalString = s;
        currIdx = s.length() - 1;
    }

    boolean hasMoreTokens(){
        return (currIdx > -1);
    }

    String nextToken(){
        int nextIdx = advanceThroughWhitespace(currIdx,originalString);
        if(nextIdx == -1) {
            assert !hasMoreTokens() : "When nextIdx is advanced to -1, this means that there are no more tokens, and hasMoreTokens() should return false.";
            return ""; // Null string
        }
        String nextToken = originalString.substring(nextIdx, currIdx);
        currIdx = advanceThroughWhitespace(nextIdx, originalString);
        return nextToken;
    }


    private int advanceThroughWhitespace(final int currIdx, final String s){
        assert s!= null && currIdx > -1 && currIdx < s.length() : " Bad args";
        int i;
        for(i = currIdx; (i > -1) && s.charAt(i) == ' '; i--); // empty for works!
        return  i;
    }



}
