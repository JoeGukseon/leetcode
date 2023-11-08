class Solution(object):
    def searchMatrix(self, matrix, target):
        """
        :type matrix: List[List[int]]
        :type target: int
        :rtype: bool
        """
        if not matrix:
            return False

        m,n = len(matrix), len(matrix[0])
        left, right = 0, m * n - 1

        while left <= right:
            mid = left + (right - left) // 2
            midValue = matrix[mid//n][mid%n]

            if midValue == target:
                return True
            elif midValue < target:
                left = mid + 1
            else:
                right = mid - 1

        return False
        