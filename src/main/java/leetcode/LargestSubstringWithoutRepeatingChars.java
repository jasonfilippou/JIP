package leetcode;

import java.util.HashMap;
import java.util.Map;

import static util.TestingFramework.checkEquality;

/**
 * <p>
 * Given a string s, find and return the length of the longest substring without repeating characters.
 * <a href = https://leetcode.com/problems/longest-substring-without-repeating-characters/description/>Leetcode 3</a>
 * @author jason
 */
public class LargestSubstringWithoutRepeatingChars {

    private static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> charCounts = new HashMap<>();
        int answer = 0, rightPtr = 0, leftPtr = 0, n = s.length();
        while(rightPtr < n) {
            addToMap(charCounts, s.charAt(rightPtr));
            while(leftPtr < rightPtr && duplicatesInMap(charCounts)){
                deleteFromMap(charCounts, s.charAt(leftPtr));
                leftPtr++;
            }
            int length = rightPtr - leftPtr + 1;
            answer = Math.max(answer, length);
            rightPtr++;
        }
        return answer;
    }

    private static void addToMap(Map<Character, Integer> charCounts, char ch) {
        charCounts.put(ch, charCounts.getOrDefault(ch, 0) + 1);
    }

    private static void deleteFromMap(Map<Character, Integer> charCounts, char ch) {
        charCounts.put(ch, charCounts.get(ch) - 1);
    }

    private static boolean duplicatesInMap(Map<Character, Integer> charCounts) {
        return charCounts.entrySet().stream().anyMatch(entry -> entry.getValue() > 1);
    }

    public static void main(String[] args) {
        Map<String, Integer> testCases = Map.of(
        "abcabcbb", 3,
        "bbbbb", 1,
        "pwwkew", 3,
        "deadmanwalking", 7
        );
        testCases.forEach((string, expectedMaxLength) -> checkEquality(lengthOfLongestSubstring(string), expectedMaxLength,
                "For string " + string + ", expected length equal to " + expectedMaxLength + ", instead got " + lengthOfLongestSubstring(string) + "."));
    }
}
