class LRUCache {

    private final int capacity;
    private final Map<Integer, Node> cache;
    private final Node head;
    private final Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>(capacity);
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            // 키가 이미 존재하면 값 업데이트하고 맨 앞으로 이동
            Node node = cache.get(key);
            node.value = value;
            moveToHead(node);
        } else {
            // 키가 존재하지 않으면 새로운 노드를 맨 앞에 추가
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            addToFront(newNode);

            // 용량을 초과하면 가장 최근에 사용되지 않은 노드를 제거
            if (cache.size() > capacity) {
                Node removedNode = removeFromTail();
                cache.remove(removedNode.key);
            }
        }
    }
    public int get(int key) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            // 키에 해당하는 노드를 맨 앞 (가장 최근에 사용된) 으로 이동
            moveToHead(node);
            return node.value;
        }
        return -1;
    }
    private void moveToHead(Node node) {
        removeNode(node);
        addToFront(node);
    }
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addToFront(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    private Node removeFromTail() {
        Node removedNode = tail.prev;
        removeNode(removedNode);
        return removedNode;
    }

    private static class Node {
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */