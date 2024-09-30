package leetcode;


import javax.swing.plaf.IconUIResource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Node {

  public int count;

  public Set<String> keys = new HashSet<>();
  public Node prev = null;
  public Node next = null;

  public Node(int count) {
    this.count = count;
  }

  public Node(int count, String key) {
    this.count = count;
    keys.add(key);
  }
}

public class AllOne {
  //private DoublyLinkedListNode head;
  //private DoublyLinkedListNode tail;


  public Map<String, Node> keyToNode = new HashMap<>();
  private final Node head = new Node(0);
  private final Node tail = new Node(0);

  public AllOne() {
    head.next = tail;
    tail.prev = head;
  }


  private void addNewKey(String key) {
    if (head.next.count == 1) {
      head.next.keys.add(key);
    } else {
      insertAfter(head, new Node(1, key));
    }
    keyToNode.put(key, head.next);
  }

  private void incrementExistingKey(String key) {
    Node node = keyToNode.get(key);
    node.keys.remove(key);
    // Maintain sorted order of count for keys
    if (node.next == tail || node.next.count > node.count + 1) {
      insertAfter(node, new Node(node.count + 1));
    }
    node.next.keys.add(key);
    keyToNode.put(key, node.next);
    if (node.keys.isEmpty()) {
      remove(node);
    }
  }


  private void decrementExistingKey(String key) {
    Node node = keyToNode.get(key);
    node.keys.remove(key);
    if (node.count > 1) {
      if (node.prev == head || node.prev.count != node.count - 1) {
        insertAfter(node, new Node(node.count - 1)); // TODO: Should this insert after node.prev?
      }
      node.prev.keys.add(key);
      keyToNode.put(key, node.prev);
    } else {
      keyToNode.remove(key);
    }
    if (node.keys.isEmpty()) {
      remove(node);
    }
  }


  public void inc(String key) {
    if (keyToNode.containsKey(key)) {
      incrementExistingKey(key);
    } else {
      addNewKey(key);
    }
  }

  public void dec(String key) {
    // We are guaranteed existence of key
    decrementExistingKey(key);
  }

  public String getMaxKey() {
    return tail.prev == head ? "" : tail.prev.keys.iterator().next();
  }

  public String getMinKey() {
    return head.next == tail ? "" : head.next.keys.iterator().next();
  }

  private void insertAfter(Node node, Node newNode) {
    newNode.prev = node;
    newNode.next = node.next;
    node.next.prev = newNode;
    node.next = newNode;
  }

  private void remove(Node node) {
    node.prev.next = node.next;
    node.next.prev = node.prev;
  }


  /*
  ["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", "getMinKey"]
  [[], ["hello"], ["hello"], [], [], ["leet"], [], []]
   */
  public static void main(String[] args) {
    AllOne myObj = new AllOne();
    myObj.inc("apple");
    myObj.inc("banana");
    myObj.inc("apple");
    myObj.inc("orange");
    myObj.inc("banana");
    myObj.inc("banana");
    myObj.dec("apple");

    System.out.println("Max Key : " + myObj.getMaxKey());
    System.out.println("Min Key : " + myObj.getMinKey());

    myObj.inc("orange");
    myObj.inc("orange");
    myObj.inc("orange");
    myObj.inc("banana");

    System.out.println("Max Key : " + myObj.getMaxKey());
    System.out.println("Min Key : " + myObj.getMinKey());
  }
}