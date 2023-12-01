class Solution {
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (searchWord(board, word, 0, i, j, visited)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean searchWord(char[][] board, String word, int index, int row, int col, boolean[][] visited) {
        if (index == word.length()) {
            return true; 
        }

        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || visited[row][col] || board[row][col] != word.charAt(index)) {
            return false;
        }

        visited[row][col] = true;

        boolean result = searchWord(board, word, index + 1, row + 1, col, visited)
                || searchWord(board, word, index + 1, row - 1, col, visited)
                || searchWord(board, word, index + 1, row, col + 1, visited)
                || searchWord(board, word, index + 1, row, col - 1, visited);

        visited[row][col] = false;
        return result;
    }
}