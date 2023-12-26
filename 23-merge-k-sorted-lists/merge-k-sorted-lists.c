/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */
struct ListNode* mergeKLists(struct ListNode** lists, int listsSize) {
    struct ListNode * head = NULL;
    int head_index;
    for (int i = 0; i < listsSize; i++) {
        if ((lists[i] != NULL && head == NULL) ||
            (lists[i] != NULL && head != NULL && head->val > lists[i]->val)) {
            head = lists[i];
            head_index = i;
        }
    }

    if (head == NULL) {
        return NULL;
    }

    lists[head_index] = lists[head_index]->next;
    head->next = mergeKLists(lists, listsSize);
    return head;
}