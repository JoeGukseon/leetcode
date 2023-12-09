class Solution(object):
    def addBinary(self, a, b):
        """
        :type a: str
        :type b: str
        :rtype: str
        """
        result = []
        carry = 0

        i, j = len(a) - 1, len(b) - 1

        while i >= 0 or j >= 0 or carry > 0:
            numA = int(a[i]) if i >= 0 else 0
            numB = int(b[j]) if j >= 0 else 0

            _sum = numA + numB + carry

            result.append(_sum % 2)
            carry = _sum // 2

            i -= 1
            j -= 1

        return ''.join(map(str, result[::-1]))