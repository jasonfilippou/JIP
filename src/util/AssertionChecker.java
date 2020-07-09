package util;

public class AssertionChecker {
    public static void check(Object expected, Object actual, String message){
        if(!expected.equals(actual))
            throw new AssertionError(message + " Expected was: " + expected.toString() + " but actual was: " + actual.toString() + ".");
    }

    public static String errMsg(int ctr){
        return "Test " + ctr + " failed.";
    }
}
