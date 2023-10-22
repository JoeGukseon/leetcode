/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k){
        ListNode curr = head;
        int count = 0;

        //커서를 이동 시키고
        while (curr != null && count < k) {
            curr = curr.next;
            count++;
        }

        if (count == k) {
            // 현재 k-그룹을 뒤집고 새로운 헤드를 얻는다
            ListNode newHead = reverse(head, k);

            // 남은 노드를 재귀적으로 뒤집고 연결한다
            head.next = reverseKGroup(curr, k);

            return newHead;
        } else {
            // k보다 적은 노드가 있는 경우 현재 헤드를 반환한다
            return head;
        }
    }
    private ListNode reverse(ListNode head, int k) {
        ListNode prev = null;
        ListNode curr = head;
        ListNode next = null;

        // k-그룹 내의 노드 뒤집기
        for (int i = 0; i < k; i++) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev; // 뒤집힌 k-그룹의 새로운 헤드
    }
}