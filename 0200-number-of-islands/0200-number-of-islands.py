class Solution(object):
    def numIslands(self, grid):
        """
        :type grid: List[List[str]]
        :rtype: int
        """
        if not grid or not grid[0]:
            return 0

        rows, cols = len(grid), len(grid[0])
        count = 0

        for i in range(rows):
            for j in range(cols):
                if grid[i][j] == '1':
                    count += 1
                    self.dfs(grid, i, j)

        return count

    def dfs(self, grid, i, j):
        rows, cols = len(grid), len(grid[0])

        if i < 0 or i >= rows or j < 0 or j >= cols or grid[i][j] == '0':
            return

        grid[i][j] = '0' 

        self.dfs(grid, i - 1, j)
        self.dfs(grid, i + 1, j)
        self.dfs(grid, i, j - 1)
        self.dfs(grid, i, j + 1)