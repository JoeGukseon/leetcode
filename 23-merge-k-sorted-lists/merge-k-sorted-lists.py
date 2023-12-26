# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution(object):
    def mergeKLists(self, lists):
        """
        :type lists: List[ListNode]
        :rtype: ListNode
        """
        min_heap = []
    
        for i, lst in enumerate(lists):
            if lst:
                heapq.heappush(min_heap, (lst.val, i, lst))
        
        dummy = ListNode()
        current = dummy
        
        while min_heap:
            val, i, node = heapq.heappop(min_heap)
            
            current.next = node
            current = current.next
            
            if node.next:
                heapq.heappush(min_heap, (node.next.val, i, node.next))
        
        return dummy.next