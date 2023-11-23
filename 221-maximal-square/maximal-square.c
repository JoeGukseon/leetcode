int minimum(int a, int b, int c) {
    int min = a;
    if (b < min) {
        min = b;
    }
    if (c < min) {
        min = c;
    }
    return min;
}


int maximalSquare(char** matrix, int matrixSize, int* matrixColSize) {
    int m = matrixSize;
    int n = matrixColSize[0];

    int dp[m][n];
    int maxSide = 0;

    for (int i = 0; i < m; i++) {
        dp[i][0] = matrix[i][0] - '0';
        maxSide = dp[i][0] > maxSide ? dp[i][0] : maxSide;
    }
    for (int j = 0; j < n; j++) {
        dp[0][j] = matrix[0][j] - '0';
        maxSide = dp[0][j] > maxSide ? dp[0][j] : maxSide;
    }

    for (int i = 1; i < m; i++) {
        for (int j = 1; j < n; j++) {
            if (matrix[i][j] == '1') {
                dp[i][j] = minimum(dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]) + 1;
                maxSide = dp[i][j] > maxSide ? dp[i][j] : maxSide;
            } else {
                dp[i][j] = 0;
            }
        }
    }

    return maxSide * maxSide;


}