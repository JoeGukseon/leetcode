int max(int a, int b) {
    return (a > b) ? a : b;
}

int maxProfit(int k, int* prices, int pricesSize) {
    int n = pricesSize;

    int **dp = (int **) malloc((k + 1) * sizeof(int *));
    for (int i = 0; i <= k; i++) {
        dp[i] = (int *) malloc(n * sizeof(int));
    }

    for (int i = 0; i <= k; i++) {
        dp[i][0] = 0;
    }
    for (int i = 0; i < n; i++) {
        dp[0][i] = 0;
    }

    for (int t = 1; t <= k; t++) {
        int maxDiff = -prices[0];

        for (int i = 1; i < n; i++) {
            dp[t][i] = max(dp[t][i - 1], prices[i] + maxDiff);
            maxDiff = max(maxDiff, dp[t - 1][i] - prices[i]);
        }
    }

    int result = dp[k][n - 1];
    for (int i = 0; i <= k; i++) {
        free(dp[i]);
    }
    free(dp);

    return result;
}