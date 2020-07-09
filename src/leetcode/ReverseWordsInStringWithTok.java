package leetcode;

import java.util.Stack;
import java.util.StringTokenizer;
import static util.AssertionChecker.check;
import static util.AssertionChecker.errMsg;

// https://leetcode.com/problems/reverse-words-in-a-string/

/**
 * This solution uses a {@link StringTokenizer}, which is not accepted by the
 * LeetCode engine. We will also build one that emulates the tokenizer's job.
 *
 * @see ReverseWordsInStringWithReverseTok
 * @see StringTokenizer
 * @see <a href="https://leetcode.com/problems/reverse-words-in-a-string/">https://leetcode.com/problems/reverse-words-in-a-string/</a>
 */
public class ReverseWordsInStringWithTok {

    private static final String DELIM= " ";

    private static String reverseWords(String s){
        assert s != null : "We were provided a null String.";
        StringTokenizer tokenizer = new StringTokenizer(s, DELIM);
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
