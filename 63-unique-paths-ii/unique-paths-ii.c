int uniquePathsWithObstacles(int** obstacleGrid, int obstacleGridSize, int* obstacleGridColSize) {
    int m = obstacleGridSize;
    int n = obstacleGridColSize[0];

    if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) {
        return 0;
    }

    int** dp = (int**)malloc(m * sizeof(int*));
    for (int i = 0; i < m; i++) {
        dp[i] = (int*)malloc(n * sizeof(int));
    }

    dp[0][0] = 1;

    for (int i = 1; i < m; i++) {
        if (obstacleGrid[i][0] == 0) {
            dp[i][0] = dp[i - 1][0];
        } else {
            dp[i][0] = 0;
        }
    }

    for (int j = 1; j < n; j++) {
        if (obstacleGrid[0][j] == 0) {
            dp[0][j] = dp[0][j - 1];
        } else {
            dp[0][j] = 0;
        }
    }

    for (int i = 1; i < m; i++) {
        for (int j = 1; j < n; j++) {
            if (obstacleGrid[i][j] == 0) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            } else {
                dp[i][j] = 0;
            }
        }
    }

    int result = dp[m - 1][n - 1];

    for (int i = 0; i < m; i++) {
        free(dp[i]);
    }
    free(dp);

    return result;
}