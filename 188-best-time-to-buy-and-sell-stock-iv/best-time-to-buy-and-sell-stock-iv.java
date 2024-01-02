class Solution {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;

        int[][] dp = new int[k + 1][n];

        for (int i = 0; i < n; i++) {
            dp[0][i] = 0;
        }

        for (int t = 1; t <= k; t++) {
            int maxDiff = -prices[0];

            for (int i = 1; i < n; i++) {
                dp[t][i] = Math.max(dp[t][i - 1], prices[i] + maxDiff);
                maxDiff = Math.max(maxDiff, dp[t - 1][i] - prices[i]);
            }
        }

        return dp[k][n - 1];
    }
}