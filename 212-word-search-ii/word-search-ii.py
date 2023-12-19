class TrieNode:
    def __init__(self):
        self.children = {}
        self.isEndOfWord = False

class Trie:
    def __init__(self):
        self.root = TrieNode()

    def insert(self, word):
        node = self.root
        for char in word:
            if char not in node.children:
                node.children[char] = TrieNode()
            node = node.children[char]
        node.isEndOfWord = True

        
class Solution(object):
    def findWords(self, board, words):
        """
        :type board: List[List[str]]
        :type words: List[str]
        :rtype: List[str]
        """
        result = []
        trie = Trie()

        for word in words:
            trie.insert(word)

        def backtrack(node, i, j, path):
            if node.isEndOfWord:
                result.append(path)
                node.isEndOfWord = False

            original = board[i][j]
            board[i][j] = '#'

            directions = [(0, 1), (0, -1), (1, 0), (-1, 0)]
            for di, dj in directions:
                ni, nj = i + di, j + dj
                if 0 <= ni < len(board) and 0 <= nj < len(board[0]) and board[ni][nj] != '#' and board[ni][nj] in node.children:
                    backtrack(node.children[board[ni][nj]], ni, nj, path + board[ni][nj])

            board[i][j] = original

        for i in range(len(board)):
            for j in range(len(board[0])):
                start_letter = board[i][j]
                if start_letter in trie.root.children:
                    backtrack(trie.root.children[start_letter], i, j, start_letter)

        return result