class Solution(object):
    def __init__(self):
        self.result_count = 0

    def totalNQueens(self, n):
        """
        :type n: int
        :rtype: int
        """
        placement = [-1] * n
        self.place_queens(0, n, placement)
        return self.result_count

    def place_queens(self, row, n, placement):
        if row == n:
            self.result_count += 1
            return

        for col in range(n):
            if self.is_valid_placement(row, col, placement):
                placement[row] = col
                self.place_queens(row + 1, n, placement)
                placement[row] = -1

    def is_valid_placement(self, row, col, placement):
        for i in range(row):
            if placement[i] == col or abs(placement[i] - col) == row - i:
                return False
        return True