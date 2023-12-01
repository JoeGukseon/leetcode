bool searchWord(char** board, char* word, int index, int row, int col, bool** visited, int m, int n) {
    if (index == strlen(word)) {
        return true;
    }

    if (row < 0 || row >= m || col < 0 || col >= n || visited[row][col] || board[row][col] != word[index]) {
        return false;
    }

    visited[row][col] = true;

    bool result = searchWord(board, word, index + 1, row + 1, col, visited, m, n)
        || searchWord(board, word, index + 1, row - 1, col, visited, m, n)
        || searchWord(board, word, index + 1, row, col + 1, visited, m, n)
        || searchWord(board, word, index + 1, row, col - 1, visited, m, n);

    visited[row][col] = false;

    return result;
}

bool exist(char** board, int boardSize, int* boardColSize, char* word) {
    bool** visited = (bool**)malloc(boardSize * sizeof(bool*));
    for (int i = 0; i < boardSize; i++) {
        visited[i] = (bool*)malloc(boardColSize[i] * sizeof(bool));
        memset(visited[i], false, boardColSize[i] * sizeof(bool));
    }
    for (int i = 0; i < boardSize; i++) {
        for (int j = 0; j < boardColSize[i]; j++) {
            if (searchWord(board, word, 0, i, j, visited, boardSize, boardColSize[i])) {
                for (int k = 0; k < boardSize; k++) {
                    free(visited[k]);
                }
                free(visited);
                return true;
            }
        }
    }

    for (int i = 0; i < boardSize; i++) {
        free(visited[i]);
    }
    free(visited);
    return false;
}