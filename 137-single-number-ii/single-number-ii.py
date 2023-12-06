class Solution(object):
    def singleNumber(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        result = 0
        for i in range(32):
            bit_sum = sum((num >> i) & 1 for num in nums)
            result |= (bit_sum % 3) << i

        if result & (1 << 31):
            result -= 1 << 32

        return result