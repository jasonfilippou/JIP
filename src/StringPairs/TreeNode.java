package StringPairs;

import java.util.LinkedList;
import java.util.List;

/**
 * <p><tt>TreeNode</tt> is a container for the data required by our implicit tree's nodes.</p>
 *  @author <a href = "mailto:jason.filippou@gmail.com">Jason Filippou</a>
 * @see ListNode
 */
public class TreeNode {
    boolean isRoot; // Is this node the root of a sub-forest?
    List<String> children;
    TreeNode() {
        this.isRoot = true;
        children = new LinkedList<String>();
    }
}
