class Solution(object):
    def minPathSum(self, grid):
        """
        :type grid: List[List[int]]
        :rtype: int
        """
        m, n = len(grid), len(grid[0])

        dp = [[0] * n for _ in range(m)]

        # 첫 번째 행 초기화
        dp[0][0] = grid[0][0]
        for i in range(1, n):
            dp[0][i] = dp[0][i - 1] + grid[0][i]

        # 첫 번째 열 초기화
        for i in range(1, m):
            dp[i][0] = dp[i - 1][0] + grid[i][0]

        # 나머지 셀 계산
        for i in range(1, m):
            for j in range(1, n):
                dp[i][j] = min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j]

        #마지막 행 반환
        return dp[-1][-1]