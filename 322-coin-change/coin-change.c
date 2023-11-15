#include <limits.h>

int min(int a, int b) {
    return (a < b) ? a : b;
}

int coinChange(int* coins, int coinsSize, int amount) {
    int dp[amount + 1];

    dp[0] = 0;
    for (int i = 1; i <= amount; i++) {
        dp[i] = INT_MAX;
    }

    for (int i = 0; i < coinsSize; i++) {
        for (int j = coins[i]; j <= amount; j++) {
            if (dp[j - coins[i]] != INT_MAX) {
                dp[j] = min(dp[j], dp[j - coins[i]] + 1);
            }
        }
    }

    return (dp[amount] == INT_MAX) ? -1 : dp[amount];
}