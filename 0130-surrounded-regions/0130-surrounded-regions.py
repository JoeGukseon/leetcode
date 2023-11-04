class Solution(object):
    def solve(self, board):
        """
        :type board: List[List[str]]
        :rtype: None Do not return anything, modify board in-place instead.
        """
        if not board or not board[0]:
            return
        
        rows, cols = len(board), len(board[0])
        for i in range(rows):
            if board[i][0] == 'O':
                self.dfs(board, i, 0)
            if board[i][cols - 1] == 'O':
                self.dfs(board, i, cols - 1)
        
        for j in range(cols):
            if board[0][j] == 'O':
                self.dfs(board, 0, j)
            if board[rows - 1][j] == 'O':
                self.dfs(board, rows - 1, j)

        for i in range(rows):
            for j in range(cols):
                if board[i][j] == 'O':
                    board[i][j] = 'X'
                elif board[i][j] == 'E':
                    board[i][j] = 'O'

    def dfs(self, board, row, col):
        rows, cols = len(board), len(board[0])

        if row < 0 or row >= rows or col < 0 or col >= cols or board[row][col] != 'O':
            return
        
        board[row][col] = 'E'
        
        self.dfs(board, row - 1, col)
        self.dfs(board, row + 1, col)
        self.dfs(board, row, col - 1)
        self.dfs(board, row, col + 1)
        