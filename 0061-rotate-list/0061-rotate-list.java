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
    public ListNode rotateRight(ListNode head, int k) {
        // 예외 처리
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        // 마지막 tail 구하기
        int length = 1;
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }

        // 실제 회전 횟수 계산
        k = k % length;
        if (k == 0) {
            return head; // 회전할 필요가 없음
        }

        // k만큼 이동하여 새로운 헤드 찾기
        int stepsToNewHead = length - k - 1;
        ListNode newHead = head;
        for (int i = 0; i < stepsToNewHead; i++) {
            newHead = newHead.next;
        }

        // 리스트 회전
        ListNode rotatedHead = newHead.next;
        newHead.next = null; // 회전만큼의 다음 끊기
        tail.next = head; // 회전된 부분과 나머지 부분 연결

        return rotatedHead;
    }
}