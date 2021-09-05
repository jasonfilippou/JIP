package leetcode;

import lombok.NonNull;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * We are given an array of non-negative integers that represent asteroids in a row. For every integer:
 *  - The sign of the integer represents the direction of the asteroid. Positive means right, negative means left.
 *  - The magnitude (absolute value) of the integer represents the size of the asteroid.
 *
 *  All asteroids are assumed to have the same velocity. Two asteroids that face "towards" each other can collide. The
 *  rules of collision are as follows:

 *  - If asteroid X and asteroid Y collide and |X| > |Y| (resp. |Y|>|X|), then asteroid X "consumes" asteroid Y (resp.
 *  Y "consumes" X), which disappears. The size of X (resp. Y) remains the same as before the collision.
 *
 *  - Two asteroids of the same size will explode upon collision, and both disappear.
 *
 *  Some corollaries of the above facts are:
 *
 *   - Two asteroids of the same sign will never collide.
 *  - If asteroid X is to the left of asteroid Y and is negative, X and Y will never collide.
 *  - (Symmetric): If asteroid X is to the right of asteroid Y and is positive, X and Y will never collide.
 *
 *  Find the state of the asteroids after all collisions. LeetCode link: https://leetcode.com/problems/asteroid-collision/
 */
public class AsteroidCollision {

    public static void main(String[] args){
        int[][] examples = {
            new int[] {5, 10, -5}, // LC example 1
            new int[] {8, -8}, // LC example 2
            new int[] {10, 2, -5}, // LC example 3
            new int[] {-2, -1, 1, 2}, // LC example 4
            new int[] {8, -10, -10, -10, 10, -10, -8, 1, 3}, // My first test case
            new int[] {10, -5, -3, 9, -9, 8, -10}, // My second test case, should yield empty array.
        };
        for(int[] example : examples){
            System.out.println("Asteroid row " + Arrays.toString(example) + " becomes " + Arrays.toString(collisionStatus(example)));
        }
    }

    public static Integer[] collisionStatus(@NonNull int[] inputArray){
        Deque<Integer> myStack = new ArrayDeque<>();   // ArrayDeque<> is to be preferred over Stack<>, based on the official docs.
        assert Arrays.stream(inputArray).noneMatch(i->i==0) : "We can't have zero - sized asteroids.";
        for(int asteroid: inputArray){
            if(myStack.isEmpty() || !collide(myStack.peekLast(), asteroid)){
                myStack.addLast(asteroid);
            } else if(collide(myStack.peekLast(), asteroid)){
                int explosionResult = asteroid;
                do {
                    explosionResult = explosion(myStack.pollLast(), explosionResult); // Keep updating the result of the collisions0.
                } while(!myStack.isEmpty() && collide(myStack.peekLast(), explosionResult));  // Keep popping from the stack as necessary until collisions stop.
                if(explosionResult != 0) {
                    myStack.addLast(explosionResult);
                }
            }
        }   // O(n)
        return myStack.toArray(new Integer[0]);
    }

    private static boolean collide(int leftAsteroid, int rightAsteroid){
        return leftAsteroid > 0 && rightAsteroid < 0;
    }

    private static int explosion(int leftAsteroid, int rightAsteroid){
        assert leftAsteroid > 0 && rightAsteroid < 0 : "Invariant violation in explosion(int, int) method: leftAsteroid and rightAsteroid cannot collide with current signs.";
        int sizeDifference = leftAsteroid - Math.abs(rightAsteroid);
        if(sizeDifference > 0){ // Left asteroid was bigger.
            return leftAsteroid;
        } else if(sizeDifference == 0){ // They both exploded!
            return 0;
        } else {  // Right asteroid was bigger.
            return rightAsteroid;
        }
    }
}
