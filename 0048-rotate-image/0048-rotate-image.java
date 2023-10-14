class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;

        // 행렬을 전치 (Transpose)합니다.
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // 행과 열을 교환합니다.
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // 각 행을 뒤집습니다.
        for (int i = 0; i < n; i++) {
            int start = 0;
            int end = n - 1;
            while (start < end) {
                // 열을 교환하여 행을 뒤집습니다.
                int temp = matrix[i][start];
                matrix[i][start] = matrix[i][end];
                matrix[i][end] = temp;
                start++;
                end--;
            }
        }
    }
}