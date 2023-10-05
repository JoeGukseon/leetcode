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

        Node current = head;
        while (current != null) {
            Node copy = new Node(current.val);
            copy.next = current.next;
            current.next = copy;
            current = copy.next;
        }

        current = head;
        while (current != null) {
            if (current.random != null) {
                current.next.random = current.random.next;
            }
            current = current.next.next;
        }

        Node newHead = head.next;
        current = head;
        Node newCurrent = newHead;
        while (current != null) {
            current.next = current.next.next;
            current = current.next;
            if (newCurrent.next != null) {
                newCurrent.next = newCurrent.next.next;
            }
            newCurrent = newCurrent.next;
        }
        
        return newHead;
    }
}