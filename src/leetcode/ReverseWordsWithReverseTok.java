package leetcode;

import java.util.StringTokenizer;

import static util.TestingFramework.check;
import static util.TestingFramework.errMsg;

// https://leetcode.com/problems/reverse-words-in-a-string/
/**
 * This solution uses a custom tokenizing class to tokenize the string from end to start,
 * avoiding the need for an extra stack.
 *
 * @see ReverseWordsWithTok
 * @see StringTokenizer
 * @see <a href="https://leetcode.com/problems/reverse-words-in-a-string/">https://leetcode.com/problems/reverse-words-in-a-string/</a>
 */
public class ReverseWordsWithReverseTok {

    private static String reverseWords(String s){
        assert s != null : "We were provided a null String.";
        ReverseStringTokenizer tokenizer = new ReverseStringTokenizer(s);
        if(!tokenizer.hasMoreTokens()) return "";
        StringBuilder builder = new StringBuilder();
        while(tokenizer.hasMoreTokens())
            builder.append(tokenizer.nextToken()).append(" ");
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
        check("George is married" , reverseWords(" married is George "), errMsg(++testCounter)); // George is married.
        check("stout! and short teapot, little a I'm", reverseWords("I'm a little teapot, short and stout!"), errMsg(++testCounter)); // Longer
        check("! stout and short , teapot little a m I'", reverseWords("I' m a little teapot , short and stout !"), errMsg(++testCounter)); // oof

        System.out.println("Not only did I pass all tests, but George is still married!");
    }

}

// This class only works with the whitespace character: ' '
class ReverseStringTokenizer{

    private final String string;
    private int startIdx, endIdx;

    ReverseStringTokenizer(final String s){
        assert s != null : "Provided a null string";
        string = s;
        endIdx = advanceThroughWhitespace(s.length() - 1);
        startIdx = advanceTillWhitespace(endIdx - 1);
    }

    boolean hasMoreTokens(){
        return (endIdx > -1);
    }

    String nextToken(){
        if(endIdx == -1) {
            assert !hasMoreTokens() : "When nextIdx is advanced to -1, this means that there are no more tokens, and hasMoreTokens() should return false.";
            return ""; // Null string
        }
        String nextToken = string.substring(startIdx + 1, endIdx + 1);
        endIdx = advanceThroughWhitespace(startIdx);
        startIdx = advanceTillWhitespace(endIdx);
        return nextToken;
    }


    private int advanceTillWhitespace(final int currIdx){
        assert currIdx < string.length() : " Bad arg: " + currIdx;
        int i;
        for(i = currIdx; (i > -1) && string.charAt(i) != ' '; i--); // empty for works!
        return  i;
    }

    private int advanceThroughWhitespace(final int currIdx){
        assert currIdx < string.length() : " Bad arg: " + currIdx;
        int i;
        for(i = currIdx; (i > -1) && string.charAt(i) == ' '; i--); // empty for works!
        return  i;
    }
}
