package leetcode;

import static util.TestingFramework.checkEquality;

import java.util.ArrayDeque;
import java.util.Map;

/**
 * <a href = "https://leetcode.com/problems/simplify-path/description/">Leetcode 71</a>
 */
public class SimplifyPath {
  public static String simplifyPath(String path) {
    ArrayDeque<String> stack = new ArrayDeque<>();
    String[] dirs = path.split("/"); // Might yield empty dirs, which we will ignore
    for(String dir : dirs){
      if(dir.equals(".") || dir.isEmpty()){
        continue;
      } else if(dir.equals("..")){ // Go up one dir (erase it) by popping the stack
        if(!stack.isEmpty()){
          stack.pollFirst();
        }
      } else {
        stack.addFirst(dir);
      }
    }
    StringBuilder sb = new StringBuilder();
    sb.append("/");
    while(!stack.isEmpty()){
      sb.append(stack.pollLast());
      if(!stack.isEmpty()){
        sb.append("/");
      }
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    Map<String, String> testCases = Map.of(
        "/", "/",
        "/home/", "/home",
        "/home/Documents/", "/home/Documents",
        "/home/../Documents", "/Documents",
        "/home////Documents", "/home/Documents",
        "/etc/init.d/...", "/etc/init.d/...",
        "/boot/./configs", "/boot/configs",
        "/boot/../../../", "/",
        "/etc/default/../init", "/etc/init",
        "/home////../...", "/..."
    );

    testCases.forEach((testCase, expectedResult) -> checkEquality(simplifyPath(testCase), expectedResult,
        "Test case " + testCase + " should map to " + expectedResult + " but instead mapped to "
        + simplifyPath(testCase) + "."));

  }
}
