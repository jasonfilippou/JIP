package util;

/**
 * A general testing framework that emulates {@code jUnit}'s {@code assertEquals()}.
 * @author <a href="https://github.com/jasonfilippou">Jason Filippou</a>
 */
public class TestingFramework {

    /**
     * Checks to see if two {@link Object} instances are equal, otherwise throws an instance of {@link AssertionError}.
     * This method is typically called by client code that wants to check the return type of a function, in the vein of:
     * {@code check(expectedValue, method(params), failurMessage)}.
     *
     * @param expected The {@link Object} instance we were expecting to see.
     * @param actual The {@link Object} instance we actually saw.
     * @param message A hopefully informative message to send to the user.
     */
    public static void check(Object expected, Object actual, String message){
        if(!expected.equals(actual))
            throw new AssertionError(message + " Expected: " + expected.toString() + " but actual: " + actual.toString() + ".");
    }

    /**
     * A simple {@link String} which uses an error counter to format itself.
     * @param ctr An integer representing the increasing counter of the current test.
     * @return The {@link String} {@code \"Test CTR failed.\"}, where {@code CTR} is the actual value of the parameter {@code ctr}.
     */
    public static String errMsg(int ctr){
        return "Test " + ctr + " failed.";
    }

}
