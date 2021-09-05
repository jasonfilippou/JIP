package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Implement an LRU cache that allows O(1) searching of arbitrary elements. <a href="https://leetcode.com/problems/lru-cache/">Leetcode link</a>.
 *
 * @param <K> An immutable key type.
 * @param <V> A value type. Can be anything.
 */
public class LRUCache<K, V> {

    private final static int DEFAULT_CAPACITY = 10;

    /* class for Double Linked List node */
    private class DLLNode{
        K key;
        V value;
        DLLNode next;
        DLLNode previous;

        /* Constructors for node type */;
        DLLNode(DLLNode next, DLLNode previous, K key, V value){
            this.key = key;
            this.value = value;
            this.next = next;
            this.previous = previous;
        }

        DLLNode(DLLNode next, DLLNode previous){
            this(next, previous, null, null);
        }

        DLLNode(K key, V value){
            this(null, null, key, value);
        }

        DLLNode(){
            this(null, null, null, null);
        }
    }

    /* private fields of LRUCache */
    private DLLNode head;           // MRU element will always be in head.
    private DLLNode tail;           // LRU element will be pointed to by this tail pointer.
    private final HashMap<K, DLLNode> myMap;  // This map will allow searching and removing arbitrary elements in O(1).
    private long capacity;       // Maximum #elements cache can hold, set at instantiation time, but mutable.
    private long currentSize;    // Number of current elements.

    /* Constructors for LRUCache. */

    public LRUCache(long capacity,K key, V value) {
        if(capacity <  0) {
            throw new IllegalArgumentException("Capacity given: " + capacity + " was negative: We need non-negative capacity.");
        }
        myMap = new HashMap<>();
        this.capacity = capacity;
        if(key != null && value != null) {
            head = new DLLNode(key, value);
            tail = head;
        }
    }

    public LRUCache(K key, V value){
        this(DEFAULT_CAPACITY,  key, value);
    }

    public LRUCache(long capacity){
        this(capacity, null, null);
    }

    public LRUCache(){
        this(DEFAULT_CAPACITY);
    }

    /* Careful here: if key is found, you need to update value. Also, if cache is full, evict LRU element and then add.
    * Don't forget to increase current count if, and only if, you have a NEW <K, V> pair inserted. */
    public void put(K key, V value){
        DLLNode node = myMap.get(key);        // O(1) with the help of our hash table.
        if(node != null){
            replace(key, value);
            moveToHead(node);
        } else if(atCapacity()){
            evictLRUEntry();
            addNewEntry(key, value);
        } else{
            addNewEntry(key, value);
            currentSize++;
        }
    }

    /* Return `null` if we do not find the key. Also note that searching the element touches it, so it's now the MRU element. *
     * This needs to be O(1) and work with an arbitrary key. */
    public V get(K key){
        DLLNode mappedNode = myMap.get(key);
        if(mappedNode == null){
            return null;
        } else {
            moveToHead(mappedNode);    // It's now the MRU!
            return mappedNode.value;
        }
    }

    public long getCapacity(){
        return capacity;
    }

    public LRUCache<K, V> setCapacity(long capacity){
        this.capacity = capacity;
        return  this;       // To enable chained calls.
    }

    public long getDefaultCapacity(){
        return DEFAULT_CAPACITY;
    }

    public boolean atCapacity(){
        return currentSize == capacity;
    }

    public boolean isEmpty(){
        return currentSize == 0;
    }

    /* The following two methods are here for debugging, and do NOT affect the state of the cache. */
    public K getLRUKey(){
        return currentSize > 0 ? tail.key : null;
    }

    public K getMRUKey(){
        return currentSize > 0 ? head.key : null;
    }

    /* Private utilities. */

    private void evictLRUEntry(){   // Element at tail needs to go bye-bye and removed from hash, too.
        assert currentSize > 0 : "Invariant violation: evicting an LRU entry from an empty cache.";
        myMap.remove(tail.key);          // And this is why you need both the key and the value in your list nodes.
        popTail();
    }

    private void moveToHead(DLLNode node){
        if(node == head){
            return;
        }
        if(node.next != null) {  // Might be the tail element!
            node.next.previous = node.previous;
        }
        node.previous.next = node.next;
        if(node == tail ) {
            tail = tail.previous;
        }
        node.next = head;
        node.previous = null;
        head.previous = node;
        head = node;
    }

    private void popTail(){             // This only pops the tail from the list, it does not affect the hash.
        tail.previous.next = tail.next; // Disconnects it from the rest of the list.
        tail = tail.previous;       // Now there is no path to the old tail from the stack, so it should be garbage collectable.
    }

    private void addNewEntry(K key, V value){   // New entries are always pushed to the front.
        DLLNode newNode = new DLLNode(key, value);
        myMap.put(key, newNode);
        newNode.next = head;
        if(head != null) {
            head.previous = newNode;
        }
        head = newNode;
        if(tail == null){
            tail = head;
        }
    }

    private void replace(K key, V value){
        assert currentSize > 0 : "Invariant violation: attempting to replace a key in an empty cache.";
        myMap.get(key).value = value;
    }

    public static void main(String[] args){
        Map<Integer, String> exampleMap = new HashMap<>() {{
            put(1, "Osama");
            put(2, "Derek");
            put(3, "Mary");
            put(4, "Hasan");
            put(5, "Rohan");
            put(6, "Jason");
            put(7, "Jason");       // Nobody disallows mapping different keys to identical, yet distinct values. Could also point to the previous entry.
            put(8, "Deshawn");
            put(9, "Tessa");
            put(10, "Catherine");
        }};
        LRUCache<Integer, String> lruCache = new LRUCache<>(exampleMap.size());
        assertCondition(lruCache.isEmpty());
        for(Map.Entry<Integer, String> entry: exampleMap.entrySet()){
            System.out.println("Inserting pair <" + entry.getKey() + "," + entry.getValue() + "> into cache");
            lruCache.put(entry.getKey(), entry.getValue());
        }

        assertCondition(1 == lruCache.getLRUKey());
        assertCondition(10 == lruCache.getMRUKey());
        assertCondition(lruCache.atCapacity());
        System.out.println("Searching for the value associated with key 1...");
        assertCondition("Osama".equals(lruCache.get(1)));   // After this call, <1, Osama> will no longer the LRU tuple
        assertCondition(2 == lruCache.getLRUKey());     // It is <2, Derek> now that is the LRU tuple.
        System.out.println("Adding entry <11, Bob>");
        lruCache.put(11, "Bob"); // Derek should be evicted, he's the LRU at the point of calling this method.
        assertCondition(lruCache.atCapacity()); // We are still at capacity.
        System.out.println("Searching for the value associated with key 2...");
        assertCondition(null == lruCache.get(2));    // If Derek was successfully evicted, the key '2' should no longer be found.
        assertCondition(11 == lruCache.getMRUKey());// And <11, Bob> should be the new MRU tuple.
        assertCondition(3 == lruCache.getLRUKey());// While the new LRU tuple needs to be <3, "Mary">.
        System.out.println("Searching for the value associated with key 11...");
        assertCondition("Bob".equals(lruCache.get(11)));    // We should be able to find the just inserted tuple. Was MRU, is still MRU.

        // At this point, it is <3, Mary> that is the LRU element, and we are STILL at capacity.
        System.out.println("Adding entry <5, Mitsos>");
        lruCache.put(5, "Mitsos");  // This should NOT evict <3, Mary> because it merely replaces the tuple <5, "Rohan">.
        assertCondition("Mary".equals(lruCache.get(3)));    // Now it is <3, Mary> that is the MRU!
        assertCondition(3 == lruCache.getMRUKey());
        assertCondition(4 == lruCache.getLRUKey());// While the LRU should now be <4, Hasan>
        System.out.println("Done!");
    }

    private static void assertCondition(boolean condition){
        if(!condition){
            throw new AssertionError("Condition was violated.");
        }
    }
}




