/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        Map<Node, Node> nodeMap = new HashMap<>();

        Node current = head;
        while (current != null) {
            nodeMap.put(current, new Node(current.val));
            current = current.next;
        }

        current = head;
        while (current != null) {
            Node copy = nodeMap.get(current);
            copy.next = nodeMap.get(current.next);
            copy.random = nodeMap.get(current.random);
            current = current.next;
        }

        return nodeMap.get(head);
    }
}