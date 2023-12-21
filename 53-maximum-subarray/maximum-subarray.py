class Solution(object):
    def maxSubArray(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        return self.max_subarray_helper(nums, 0, len(nums) - 1)

    def max_subarray_helper(self, nums, left, right):
        if left == right:
            return nums[left]

        mid = left + (right - left) // 2

        left_max = self.max_subarray_helper(nums, left, mid)
        right_max = self.max_subarray_helper(nums, mid + 1, right)
        cross_max = self.max_crossing_sum(nums, left, mid, right)

        return max(left_max, right_max, cross_max)

    def max_crossing_sum(self, nums, left, mid, right):
        left_sum = float('-inf')
        sum = 0
        for i in range(mid, left - 1, -1):
            sum += nums[i]
            left_sum = max(left_sum, sum)

        right_sum = float('-inf')
        sum = 0
        for i in range(mid + 1, right + 1):
            sum += nums[i]
            right_sum = max(right_sum, sum)

        return left_sum + right_sum
