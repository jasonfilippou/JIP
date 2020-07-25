package leetcode;

import java.util.*;

import static util.TestingFramework.check;
import static util.TestingFramework.errMsg;

public class UniqueWordAbbreviation {

    private Map<String, Set<String>> abbrvsToWords; // Will map abbreviations to original words.

    public UniqueWordAbbreviation(String[] dictionary) {
        buildDictionary(dictionary);
    }

    private String abbreviate(String s){
        if(s.length() <= 2) return  s;
        return s.charAt(0) + String.valueOf(s.length() - 2 ) + s.charAt(s.length() - 1);
    }

    private void buildDictionary(String[] dictionary){
        abbrvsToWords = new HashMap<>();
        for(String s: dictionary){
            String abbrv = abbreviate(s);
            if(abbrvsToWords.containsKey(abbrv))
                abbrvsToWords.get(abbrv).add(s); // Add the string as another one that abbreviates to `abbrv`
            else
                abbrvsToWords.put(abbrv, new HashSet<>(Collections.singletonList(s))); // First time we see the abbreviation, add it with the source string.
        }
    }

    private boolean isUnique(String word) {
        String abbrv = abbreviate(word);
        if(!abbrvsToWords.containsKey(abbrv)) {
            return true;
        } else{
            Set<String> originalWords = abbrvsToWords.get(abbrv);
            return originalWords.contains(word) && originalWords.size() == 1;   // Unique string that produced the abbreviation.
        }
    }

    private static List<Boolean> isUnique(String[] dictionary, String[] words){
        assert dictionary != null && words != null : "Provided arguments: dictionary = " + Arrays.toString(dictionary) + ", words = " + Arrays.toString(words) + ".";
        UniqueWordAbbreviation uabbrv = new UniqueWordAbbreviation(dictionary);
        List<Boolean> retVal = new ArrayList<>();
        for(String w: words)
            retVal.add(uabbrv.isUnique(w));
        return retVal;
    }

    public static void main(String[] args){
        int testCounter = -1;
        check(Collections.singletonList(true), isUnique(new String[]{"kelly"},
                new String[]{"kelly"}), errMsg(++testCounter));
        check(Collections.singletonList(false), isUnique(new String[]{"kelly"},
                new String[]{"kerry"}), errMsg(++testCounter));
        check(Collections.singletonList(false), isUnique(new String[]{"kelly"},
                new String[]{"karay"}), errMsg(++testCounter));
        check(Collections.singletonList(false), isUnique(new String[]{"kelly","karry"},
                new String[]{"kelly"}), errMsg(++testCounter));
        check(Collections.singletonList(false), isUnique(new String[]{"kelly", "karry"},
                new String[]{"karry"}), errMsg(++testCounter));
        check(Arrays.asList(false, false, true, true, false), isUnique(new String[]{"kelly", "karry"},
                new String[]{"karry", "kelly", "jason", "jonathan", "keiry"}), errMsg(++testCounter));
        check(Arrays.asList(true,true,false,true), isUnique(new String[]{"mary"},
                new String[]{"mike", "mikey", "miky", "mary"}), errMsg(++testCounter));
        check(Arrays.asList(false,true,false,true), isUnique(new String[]{"deer", "door", "cake", "card"},
                new String[]{"dear", "cart", "cane", "make"}), errMsg(++testCounter));

        System.out.println("All tests passed.");
    }



}

