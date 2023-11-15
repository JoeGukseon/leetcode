int max(int a, int b) {
    return (a > b) ? a : b;
}

int lengthOfLIS(int* nums, int numsSize) {
    int dp[numsSize];
    for (int i = 0; i < numsSize; i++) {
        dp[i] = 1;
    }

    for (int i = 1; i < numsSize; i++) {
        for (int j = 0; j < i; j++) {
            if (nums[i] > nums[j]) {
                dp[i] = max(dp[i], dp[j] + 1);
            }
        }
    }

    int max_length = 1;
    for (int i = 0; i < numsSize; i++) {
        if (dp[i] > max_length) {
            max_length = dp[i];
        }
    }

    return max_length;
}