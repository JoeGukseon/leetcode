class Solution(object):
    def maxSubarraySumCircular(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        def kadane(arr):
            max_sum = float('-inf')
            current_sum = 0
            for num in arr:
                current_sum = max(num, current_sum + num)
                max_sum = max(max_sum, current_sum)
            return max_sum

        n = len(nums)

        max_inner_sum = kadane(nums)

        total_sum = sum(nums)
        inverted_nums = [-num for num in nums]
        max_wrap_sum = total_sum + kadane(inverted_nums)

        return max(max_inner_sum, max_wrap_sum) if max_wrap_sum != 0 else max_inner_sum