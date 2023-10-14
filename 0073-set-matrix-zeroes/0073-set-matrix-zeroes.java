class Solution {
    public void setZeroes(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        boolean firstRowZero = false;
        boolean firstColZero = false;

        // 첫 번째 행에 0이 있는지 확인
        for (int j = 0; j < cols; j++) {
            if (matrix[0][j] == 0) {
                firstRowZero = true;
                break;
            }
        }

        // 첫 번째 열에 0이 있는지 확인
        for (int i = 0; i < rows; i++) {
            if (matrix[i][0] == 0) {
                firstColZero = true;
                break;
            }
        }

        // 나머지 행과 열을 확인하고 0이면 첫 번째 행과 열에 표시
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // 첫 번째 행을 0으로 설정
        for (int j = 1; j < cols; j++) {
            if (matrix[0][j] == 0) {
                setColumnZero(matrix, j);
            }
        }

        // 첫 번째 열을 0으로 설정
        for (int i = 1; i < rows; i++) {
            if (matrix[i][0] == 0) {
                setRowZero(matrix, i);
            }
        }

        // 첫 번째 행과 열을 0으로 설정
        if (firstRowZero) {
            setRowZero(matrix, 0);
        }

        if (firstColZero) {
            setColumnZero(matrix, 0);
        }
    }

    private static void setRowZero(int[][] matrix, int row) {
        for (int j = 0; j < matrix[0].length; j++) {
            matrix[row][j] = 0;
        }
    }

    private static void setColumnZero(int[][] matrix, int col) {
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][col] = 0;
        }
    }
}