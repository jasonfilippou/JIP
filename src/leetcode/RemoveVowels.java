package leetcode;

import static util.AssertionChecker.check;

// https://leetcode.com/problems/remove-vowels-from-a-string/
public class RemoveVowels {

    private static String removeVowels(String str) {
        final StringBuilder strBuilder = new StringBuilder();
        for(int i = 0; i < str.length(); i++)
            if(!isVowel(str.charAt(i)))
                strBuilder.append(str.charAt(i));
        return strBuilder.toString();
    }

    private static boolean isVowel(char c){
        // 'y' is *not* considered a vowel in this problem.
        return (c == 'a') || (c == 'e') || (c == 'i') || (c == 'o') || (c == 'u') ||
                (c == 'A') || (c == 'E') || (c == 'I') || (c == 'O') || (c == 'U');
    }

    public static void main(String[] args) {
        int testCtr = -1;
        check(removeVowels("Jason").equals("Jsn"), errMsg(++testCtr));
        check(removeVowels("jason").equals("jsn"), errMsg(++testCtr));
        check(removeVowels("Canada Lakes").equals("Cnd Lks"), errMsg(++testCtr));
        check(removeVowels("Republic of Granadia").equals("Rpblc f Grnd"), errMsg(++testCtr));
        check(removeVowels("").equals(""), errMsg(++testCtr));
        check(removeVowels(" ").equals(" "), errMsg(++testCtr));
        check(removeVowels("  a m ").equals("   m "), errMsg(++testCtr));
    }

    private static String errMsg(int ctr){
        return "Test " + ctr + " failed.";
    }
}
