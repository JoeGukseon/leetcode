class Solution {
    public void gameOfLife(int[][] board) {
        int yLen = board.length;
        int xLen = board[0].length;

        int[] dirX = {-1, -1, -1, 0, 0, 1, 1, 1}; //8가지 방향
        int[] dirY = {-1, 0, 1, -1, 1, -1, 0, 1};

        int[][] nextGeneration = new int[yLen][xLen];

        for (int i = 0; i < yLen; i++) {
            for (int j = 0; j < xLen; j++) {
                int neiCnt = 0; // 이웃 갯수
                for (int k = 0; k < 8; k++) {
                    int ni = i + dirX[k];
                    int nj = j + dirY[k];
                    // 유효칸안에서 이웃 확인
                    if (ni >= 0 && ni < yLen && nj >= 0 && nj < xLen && board[ni][nj] == 1) {
                        neiCnt++;
                    }
                }
                
                //연산
                if (board[i][j] == 1) {
                    if (neiCnt < 2 || neiCnt > 3) {
                        nextGeneration[i][j] = 0; // Under-population 또는 Over-population
                    } else {
                        nextGeneration[i][j] = 1; // Stable population
                    }
                } else {
                    if (neiCnt == 3) {
                        nextGeneration[i][j] = 1; // Reproduction
                    }
                }
            }
        }
        for (int i = 0; i < yLen; i++) {
            System.arraycopy(nextGeneration[i], 0, board[i], 0, xLen);
        }
    }
}