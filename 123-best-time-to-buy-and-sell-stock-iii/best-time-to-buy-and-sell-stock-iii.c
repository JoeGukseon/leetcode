int max(int a, int b) {
    return (a > b) ? a : b;
}

int maxProfit(int* prices, int pricesSize) {
    if (pricesSize <= 1) {
        return 0;
    }

    int dp[3][pricesSize];
    memset(dp, 0, sizeof(dp));

    for (int i = 1; i <= 2; i++) {
        int maxDiff = -prices[0];
        for (int j = 1; j < pricesSize; j++) {
            dp[i][j] = max(dp[i][j - 1], prices[j] + maxDiff);

            maxDiff = max(maxDiff, dp[i - 1][j] - prices[j]);
        }
    }

    return dp[2][pricesSize - 1];
}