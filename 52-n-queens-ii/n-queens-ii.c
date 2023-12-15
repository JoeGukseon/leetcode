#define MAX_N 9

int result_count = 0;

int is_valid_placement(int row, int col, int placement[]) {
    for (int i = 0; i < row; i++) {
        if (placement[i] == col || abs(placement[i] - col) == row - i) {
            return 0;
        }
    }
    return 1;
}

void place_queens(int row, int n, int placement[]) {
    if (row == n) {
        result_count++;
        return;
    }

    for (int col = 0; col < n; col++) {
        if (is_valid_placement(row, col, placement)) {
            placement[row] = col;
            place_queens(row + 1, n, placement);
            placement[row] = -1;
        }
    }
}

int totalNQueens(int n) {
    int placement[MAX_N];
    for (int i = 0; i < MAX_N; i++) {
        placement[i] = -1;
    }

    place_queens(0, n, placement);
    int result = result_count;
    result_count = 0;
    return result;
}