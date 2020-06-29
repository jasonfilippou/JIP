package util;

public class AssertionChecker {
    public static void check(boolean assertion, String message){
        if(!assertion)
            throw new AssertionError(message);
    }
}
