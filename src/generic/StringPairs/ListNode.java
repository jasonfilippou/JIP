package generic.StringPairs;

/**
 * <p>{@link ListNode} is a container for the data carried by our input.</p>
 *
 * @see TreeNode
 *@author <a href = "mailto:jason.filippou@gmail.com">Jason Filippou</a>
 */
public  class ListNode{
    String parent, child;

    ListNode(String parent, String child){
        this.parent = parent;
        this.child = child;
    }
}