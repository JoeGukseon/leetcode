class Solution(object):
    def plusOne(self, digits):
        """
        :type digits: List[int]
        :rtype: List[int]
        """
        n = len(digits)
        carry = 1

        for i in range(n - 1, -1, -1):
            total = digits[i] + carry
            digits[i] = total % 10
            carry = total // 10

        # 만약 마지막까지 전파된 carry가 있다면, 배열을 확장하고 맨 앞에 carry를 추가합니다.
        if carry > 0:
            digits.insert(0, carry)

        return digits