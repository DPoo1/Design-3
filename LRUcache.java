//approach-we are storing the (k,v)as nodes in a Doubly linkedList and also in the map for quick access.The recently used/accessed node are being moved towards the tail and least recently one are next to head in order. for GET we are getting the node and moving the node towards the tail and for PUT, we are checking if the key is in the map and updating the value accordingly while moving the node and also we are checking the capacity by our map's size, if we are not full we are adding it towards the end as well as to our map and if we are full we are removing the node next to head and from the map as well and adding the new node towards the end.
//time complexity-O(1)for get and put methods as we are using map to locate the node and since are using DLL, remove and add the node towards the end is in O(1) as well
//space Complexity-O(capacity) which is the auxillary space used by our map.
class LRUCache {
    Node head;
    Node tail;
    int capacity;

    HashMap<Integer, Node> map;

    class Node {
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
           
        }
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!map.containsKey(key))
            return -1;
    //we are returing the value and moving the node to end
        Node cur = map.get(key);
        removeNode(cur);
        updateNode(cur);
        return cur.value;
    }

    public void put(int key, int value) {
        //we have the key in the map 
        if (map.containsKey(key)) {
            Node cur = map.get(key);
            cur.value = value;
            removeNode(cur);
            updateNode(cur);
        } else {//we donot have the key in the map
            var cur = new Node(key, value);
            //case where we are full, we are removing the least used Node and adding the new one
            if (map.size() == capacity) {
                map.remove(head.next.key);
                removeNode(head.next);
                map.put(key, cur);
                updateNode(cur);
            } else {
                //we are adding towards the end as we still have capacity
                updateNode(cur);
                map.put(key, cur);
                
            }
        }
    }

    private void removeNode(Node cur) {
        //removing Node
        cur.prev.next = cur.next;
        cur.next.prev = cur.prev;
        cur.next = null;
        cur.prev = null;
    }

    private void updateNode(Node cur) {

        //updating node to the lastest position
        cur.next = tail;
        cur.prev = tail.prev;
        cur.prev.next = cur;
        tail.prev = cur;
    }

}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
