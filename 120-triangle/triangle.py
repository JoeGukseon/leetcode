class Solution(object):
    def minimumTotal(self, triangle):
        """
        :type triangle: List[List[int]]
        :rtype: int
        """
        rows = len(triangle)

        dp = [[0] * rows for _ in range(rows)]
        
        dp[rows - 1] = triangle[rows - 1]

        for i in range(rows - 2, -1, -1):
            for j in range(i + 1):
                dp[i][j] = triangle[i][j] + min(dp[i + 1][j], dp[i + 1][j + 1])

        return dp[0][0]
