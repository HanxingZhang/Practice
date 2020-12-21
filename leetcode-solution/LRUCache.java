/*Solution1:
* We're asking to implement the LRU cache structure which provides the following operations in 0(1) time
* Get the key / Check if the key exists
* Put the key
* Delete the first added key
* The first two operations in O(1) time are provided by the standard hashmap, and the last one by linked list.
* Space complexity : O(capacity) since the space is used only for an ordered dictionary with at most capacity + 1 elements.
*
* Solution2:
* 1. The key to solve this problem is using a double linked list which enables us to quickly move nodes.
* 2. The LRU cache is a hash table of keys and double linked nodes. The hash table makes the time of get() to be O(1).
* The list of double linked nodes make the nodes adding/removal operations O(1)
*
* */

import java.util.HashMap;

public class LRUCache {

    private class Node{
        Node prev;
        Node next;
        int key;
        int value;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }

    private int capacity;
    private int count;
    private HashMap<Integer, Node> map;
    private Node head;
    private Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.count = 0;
        this.map = new HashMap<>();
        this.head = null;
        this.tail = null;
    }

    public void deleteNode(Node node) {
        if(node == head && node == tail) {
            head = null;
            tail = null;
        } else if (node == head) {
            head = head.next;
        } else if (node == tail) {
            tail = node.prev;
            tail.next = null;
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }

    public void addToHead(Node node) {
        if(head == null && tail == null) {
            head = node;
            tail = node;
        }
        node.next = head;
        node.next.prev = node;
        node.prev = null;
        head = node;
    }

    public int get(int key) {
        if(map.get(key) == null) {
            return -1;
        }
        Node node = map.get(key);
        int result = node.value;
        deleteNode(node);
        addToHead(node);
        return result;
    }

    public void put(int key, int value) {
        if(map.get(key) != null) {
            Node node = map.get(key);
            node.value = value;
            deleteNode(node);
            addToHead(node);
        } else {
            Node node = new Node(key, value);
            map.put(key, node);
            if(count < capacity) {
                count++;
                addToHead(node);
            } else {
                map.remove(tail.key);
                deleteNode(tail);
                addToHead(node);
            }
        }
    }
}
