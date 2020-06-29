package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

// https://leetcode.com/problems/letter-combinations-of-a-phone-number/
public class GenerateLetterCombinations {

    private static final int NUM_VALID_DIGITS = 8;

    public static List<String> letterCombinations(String digits) {
        assert digits != null : "Provided null string";
        if(digits.isEmpty()) return new ArrayList<>();
        List<List<Character>> assoc = associateDigitsToChars();
        assert assoc.size() == NUM_VALID_DIGITS : "There are 8 valid digits";
        List<String> possibleNames = new LinkedList<>();
        letterCombinations(assoc, possibleNames, "", digits, 0);
        return possibleNames;
    }

    private static  void letterCombinations(List<List<Character>> assoc, List<String> sols,
                                    String accum, String digits, int currIdx){
        assert 0 <= currIdx && currIdx <= digits.length() : "Found bad index: " + currIdx;
        if(currIdx == digits.length()){
            sols.add(accum.toString());
            return;
        }
        int currDigit = Character.getNumericValue(digits.charAt(currIdx));
        for(Character c: assoc.get(currDigit -2))
            letterCombinations(assoc, sols, accum + c, digits, currIdx + 1);
    }

    private static List<List<Character>>associateDigitsToChars(){
        List<List<Character>> retVal = new ArrayList<>(NUM_VALID_DIGITS); // For time and space efficiency
        addChars(retVal, "abc"); addChars(retVal, "def");
        addChars(retVal, "ghi"); addChars(retVal, "jkl");
        addChars(retVal, "mno"); addChars(retVal, "pqrs");
        addChars(retVal, "tuv"); addChars(retVal, "wxyz");
        return retVal;
    }

    private static void addChars(List<List<Character>> list, String string) {
        list.add(string.chars().mapToObj(c->(char)c).collect(Collectors.toList()));
    }

    public static void main(String[] args){
        System.out.println(letterCombinations("2"));
        System.out.println(letterCombinations("23"));
        System.out.println(letterCombinations("234"));
    }
}
