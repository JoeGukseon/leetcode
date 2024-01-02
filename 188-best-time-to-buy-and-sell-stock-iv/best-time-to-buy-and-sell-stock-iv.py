class Solution(object):
    def maxProfit(self, k, prices):
        """
        :type k: int
        :type prices: List[int]
        :rtype: int
        """
        n = len(prices)

        if n == 0 or k == 0:
            return 0

        dp = [[0] * n for _ in range(k + 1)]

        for i in range(n):
            dp[0][i] = 0

        for t in range(1, k + 1):
            maxDiff = -prices[0]

            for i in range(1, n):
                dp[t][i] = max(dp[t][i - 1], prices[i] + maxDiff)
                maxDiff = max(maxDiff, dp[t - 1][i] - prices[i])

        return dp[k][n - 1]