package StringPairs;

import java.util.LinkedList;

/**
 * <p>A &quot; runner &quot; class whose sole purpose is to run the program with sample inputs. </p>
 *
 * @see StringPairs
 * @see ListNode
 * @see TreeNode
 * @author <a href = "mailto:jason.filippou@gmail.com">Jason Filippou</a>
 */
public class Runner {

    /**
     * <p>Standard <tt>main</tt> method</p>.
     * @param args: The cmd arguments. Not used in this implementation.
     *
     */
    public static void main(String[] args){

        /*
           Test case #1: (A, C) -> (C, Q)-> (C, R) ->( B, A) ->(A, Q)
         */

        System.out.println("==========================================================");
        System.out.println("Test case #1: (A, C) -> (C, Q)-> (C, R) ->( B, A) ->(A, Q)");
        System.out.println("==========================================================");

        LinkedList<ListNode> input = new LinkedList<>();
        input.add(new ListNode("A", "C"));
        input.add(new ListNode("C", "Q"));
        input.add(new ListNode("C", "R"));
        input.add(new ListNode("B", "A"));
        input.add(new ListNode("A", "Q"));

        StringPairs ds = new StringPairs(input);
        ds.buildTree();
        ds.printForest();

        input = null;
        ds = null;
        System.gc();

        /*
           Test case #2: (R, M) -> (R, T)-> (S, R) ->(S, T)
         */

        System.out.println("=============================================");
        System.out.println("Test case #2: (R, M) -> (R, T)-> (S, R) ->(S, T)");
        System.out.println("=============================================");

        input = new LinkedList<>();
        input.add(new ListNode("R", "M"));
        input.add(new ListNode("R", "T"));
        input.add(new ListNode("S", "R"));
        input.add(new ListNode("S", "T"));

        ds = new StringPairs(input);
        ds.buildTree();
        ds.printForest();

        input = null;
        ds = null;
        System.gc();

        /*
           Test case #3: (T, A) -> (A, G) -> (Q, A) -> (Q, T) -> (R, B) -> (Q, R) -> (M, N) -> (N, R) -> (N, Z) -> (S, X) -> (S, E) -> (E, X)
         */
        System.out.println("==================================================================================================================================");
        System.out.println("Test case #3: (T, A) -> (A, G) -> (Q, A) -> (Q, T) -> (R, B) -> (Q, R) -> (M, N) -> (N, R) -> (N, Z) -> (S, X) -> (S, E) -> (E, X)");
        System.out.println("==================================================================================================================================");

        input = new LinkedList<>();
        input.add(new ListNode("T", "A"));
        input.add(new ListNode("A", "G"));
        input.add(new ListNode("Q", "A"));
        input.add(new ListNode("Q", "T"));
        input.add(new ListNode("R", "B"));
        input.add(new ListNode("Q", "R"));
        input.add(new ListNode("M", "N"));
        input.add(new ListNode("N", "R"));
        input.add(new ListNode("N", "Z"));
        input.add(new ListNode("S", "X"));
        input.add(new ListNode("S", "E"));
        input.add(new ListNode("E", "X"));

        ds = new StringPairs(input);
        ds.buildTree();
        ds.printForest();
    }


}

