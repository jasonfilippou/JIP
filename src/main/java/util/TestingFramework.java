package util;

import java.util.Objects;

/**
 * A general testing framework that emulates {@code jUnit}'s {@code assertEquals()}.
 * @author <a href="https://github.com/jasonfilippou">Jason Filippou</a>
 */
public class TestingFramework {

    /**
     * Checks to see if the provided assertion is {@literal true}.
     * @param assertion A {@literal boolean} assertion to check for truth.
     * @param excMessage A {@link String} message to throw if the assertion is not true.
     * @throws AssertionError if the assertion is {@literal false}
     */
    public static void checkAssertion(boolean assertion, String excMessage) throws AssertionError {
        if(!assertion){
            throw new AssertionError(excMessage);
        } else {
            System.out.println("Test passed");
        }
    }
    /**
     * Checks to see if two {@link Object} instances are equal, otherwise throws an instance of {@link AssertionError}.
     * This method is typically called by client code that wants to check the return type of a function, in the vein of:
     * {@code check(expectedValue, method(params), failurMessage)}.
     *
     * @param expected The {@link Object} instance we were expecting to see.
     * @param actual The {@link Object} instance we actually saw.
     * @param excMessage A hopefully informative message to send to the user.
     */
    public static void checkEquality(Object expected, Object actual, String excMessage){
        checkAssertion(expected.equals(actual), excMessage);
    }

    /**
     * Determines if two arrays are equal.
     * @param arrayOne The first array
     * @param arrayTwo The second array
     * @param <T> The type of the arrays' contents
     * @throws AssertionError If the arrays are not the same.
     */
    public static <T> void checkArraysEqual(T[] arrayOne, T[] arrayTwo) throws AssertionError {
        if(arrayOne.length != arrayTwo.length){
            throw new AssertionError("Array 1 length = " + arrayOne.length + " != Array 2 length = " + arrayTwo.length);
        }
        for(int i = 0; i < arrayOne.length; i++){
            if(!Objects.equals(arrayOne[i], arrayTwo[i])){
                throw new AssertionError("Array 1 element " + arrayOne[i] + " not equal to Array 2 element " + arrayTwo[i]);
            }
        }
        System.out.println("Test passed");
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
