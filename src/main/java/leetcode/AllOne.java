package leetcode;

public class AllOne {
    private DoublyLinkedListNode head;
    private HashMap<String, DoublyLinkedListNode> map;

    public AllOne() {
        head = null;
        map = new HashMap<>();
    }

    public void inc(String key) {
        if(map.contains(key)){
            map.get(key).inc();
        } else {
            DoublyLinkedListNode newNode = new DoublyLinkedListNode(key, 1);
            map.add(key, newNode);
            if(head == null){
                head = new DoublyLinkedListNode(newNode.getKey(), newNode.getCount(),
                        newNode, newNode);
            } else {
                insertIntoList(head, newNode);
            }
        }
    }

    public void dec(String key) {
        assert map.contains(key) : "Problem invariant violated.";
        map.get(key).dec();
        if(map.get(key).getCount() == 0){
            removeFromList(map.get(key));
            map.remove(key);
        }
    }

    public String getMaxKey() {
        return head.getPrevious().getKey();
    }

    public String getMinKey() {
        return head.getKey();
    }

    private static class DoublyLinkedListNode {
        private int count;
        private String key;
        private DoublyLinkedListNode previous;
        private DoublyLinkedListNode next;

        public DoublyLinkedListNode(String key, int count, DoublyLinkedListNode previous,
                                    DoublyLinkedListNode next){
            this.key = key;
            this.count = count;
            this.previous = previous;
            this.next = next;
        }

        public DoublyLinkedListNode(String key, int count){
            this(key, count, null, null);
        }

        @Override
        public boolean equals(Object otherNode){
            if(otherNode == null) return false;
            DoublyLinkedListNode otherNodeCasted = ((DoublyLinkedListNode)otherNode);
            return this.key.equals(otherNodeCasted.getKey()) && this.count == otherNodeCasted.getCount();
        }

        public int getCount(){
            return count;
        }

        public String getKey(){
            return key;
        }

        public DoublyLinkedListNode getPrevious(){
            return previous;
        }

        public DoublyLinkedListNode getNext(){
            return next;
        }

        public void setPrevious(DoublyLinkedListNode previous){
            this.previous = previous;
        }

        public void setNext(DoublyLinkedListNode next){
            this.next = next;
        }

        public void inc(){
            count++;
        }

        public void dec(){
            count--;
        }

    }

    private void insertIntoList(DoublyLinkedListNode head, DoublyLinkedListNode nodeToInsert){
        assert head != null;
        // Maintain sorted ascending order based on count.
        DoublyLinkedListNode nodeScanned = head;
        while(nodeScanned.getCount() < nodeToInsert.getCount() && node != head.getPrevious()){
            nodeScanned = nodeScanned.getNext();
        }
        node.next = new DoublyLinkedListNode(nodeToInsert.getKey(), nodeToInsert.getCount(),
                node, node.next);
    }

    private void removeFromList(DoublyLinkedListNode nodeToDelete){
        DoublyLinkedListNode nodeScanned = head;
        // Recall that we are guaranteed existence of element
        // before deletion
        while(!nodeToDelete.equals(nodeScanned)){
            nodeScanned = nodeScanned.getNext();
        }
        nodeToDelete.getPrevious().setNext(nodeToDelete.getNext());
        nodeToDelete.getNext().setPrevious(nodeToDelete.getPrevious());

    }
}
