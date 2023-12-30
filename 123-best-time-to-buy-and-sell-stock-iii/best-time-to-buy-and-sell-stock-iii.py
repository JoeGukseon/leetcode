class Solution(object):
    def maxProfit(self, prices):
        """
        :type prices: List[int]
        :rtype: int
        """
        if len(prices) <= 1:
            return 0

        n = len(prices)

        dp = [[0] * n for _ in range(3)]

        for i in range(1, 3):
            maxDiff = -prices[0]
            for j in range(1, n):
                dp[i][j] = max(dp[i][j - 1], prices[j] + maxDiff)

                maxDiff = max(maxDiff, dp[i - 1][j] - prices[j])

        return dp[2][n - 1]