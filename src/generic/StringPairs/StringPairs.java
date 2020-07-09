package generic.StringPairs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * Given a list of nodes that contain two strings that form a parent -&gt; child hierarchy, print the forest that the list
 * represents. For example, the following input:
 * 
 * (A , C) -&gt; ( C, Q) -&gt; ( C, R) -&gt; ( B, A) -&gt; ( A, Q)
 *
 * We want to print out:
 *
 * B
 * . A
 * . . C
 * . . . Q
 * . . . R
 * . Q
 *
 * @author <a href = "mailto:jason.filippou@gmail.com">Jason Filippou</a>
 * @see ListNode
 * @see TreeNode
 */
public class StringPairs {

    private LinkedList<TreeNode> roots;
    private HashMap<String, TreeNode> hash;
    private List<ListNode> inputList;

     /**
     * Simple constructor that initializes the object with the given list
      *@throws RuntimeException if the argument provides is a null reference or an empty list.
     * @param inputList The input list of pairs (parent, child).
      * @see ListNode
     */
    public StringPairs(LinkedList<ListNode> inputList){
        if(inputList == null || inputList.size() == 0)
            throw new RuntimeException("Input list provided is null or empty!");
        this.inputList = inputList; // No carbon copy needed
        roots = new LinkedList<>();
        hash = new HashMap<>();
    }

    /**
     * Builds an internal implicit tree based on pointers from the hashes.
     */
    public void buildTree(){
        assert inputList != null && inputList.size() > 0 : "buildTree() invariant violated: input list should be non-null and non-empty.";
        inputList.forEach(node->
        {
            String parentName = node.parent;
            String childName = node.child;

            // If the hash already contains the parent name, we have encountered this parent
            // // string before. We need to examine the child.

            if(hash.containsKey(parentName)) {
                TreeNode parentNode = hash.get(parentName);

                // If the child is also in the hash, we have two cases.

                if (hash.containsKey(childName)){
                    TreeNode childNode = hash.get(childName);

                    // (1) We either contain a duplicate entry, in which case there is nothing to do
                    // besides check for an invariant...

                    if(parentNode.children.contains(childName)) {
                        assert !childNode.isRoot : "buildTree() invariant violated: Parent " + parentName +
                                " was pointing to child " + childName + " but the child was considered a subforest root";
                    } else{

                        // (2) We have already encountered both the parent and the child as roots of individual
                        // subforests, and we need to connect them appropriately.

                        assert childNode.isRoot : "buildTree() invariant violated: child " + childName +
                                " should be a subforest root if it hasn't been connected to a father node. ";
                        parentNode.children.add(childName);
                        childNode.isRoot = false;
                    }
                } else {

                    // If we don't have the child in the hash, we need to:
                    // (1) Add it as a child in the parent node.
                    // (2) Add it to the hash.
                    // (3) Make sure it is NOT considered a subforest root.

                    parentNode.children.add(childName);
                    TreeNode newChildNode = new TreeNode();
                    newChildNode.isRoot=false;
                    hash.put(childName, newChildNode);

                }
            } else { // First time we encounter this parent in the input sequence.

                // First thing we need to do is create the parent node, connect to the child
                // And add to the hash.

                TreeNode parentNode = new TreeNode();
                hash.put(parentName, parentNode);
                parentNode.children.add(childName);

                // We examine if the child is already in the hash. If not, we make sure we connect it.
                if(!hash.containsKey(childName)) { // But the child might already be there! Then, connect!
                    TreeNode childNode = new TreeNode();
                    childNode.isRoot = false;
                    hash.put(childName, childNode);
                } else{ // Just make sure it's not considered a root anymore.
                    hash.get(childName).isRoot = false;
                }

            }
        });
    }

    /**
     * Prints the forest required by the project specification.
     */
    public void printForest(){
        hash.keySet().forEach(key-> {
            TreeNode keyNode = hash.get(key);
            if(keyNode.isRoot)
                printDepthFirst(key, "");
        });
    }
    
    private void printDepthFirst(String key, String prefix){
        System.out.println(prefix + key);
        for (String child : hash.get(key).children) {
            assert child != null : "printDepthFirst() invariant violated: for-each loop should loop only through non-null children.";
            printDepthFirst(child, ". " + prefix );
        }
    }


}
