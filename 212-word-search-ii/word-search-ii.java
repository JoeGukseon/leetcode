class TrieNode {
    Map<Character, TrieNode> children;
    boolean isEndOfWord;

    public TrieNode() {
        children = new HashMap<>();
        isEndOfWord = false;
    }
}

class Trie {
    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            node.children.putIfAbsent(ch, new TrieNode());
            node = node.children.get(ch);
        }
        node.isEndOfWord = true;
    }
}

class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        Trie trie = new Trie();

        for (String word : words) {
            trie.insert(word);
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                char startLetter = board[i][j];
                if (trie.root.children.containsKey(startLetter)) {
                    backtrack(board, i, j, trie.root.children.get(startLetter), result, "" + startLetter);
                }
            }
        }

        return result;
    }

    private void backtrack(char[][] board, int i, int j, TrieNode trieNode, List<String> result, String path) {
    if (trieNode.isEndOfWord) {
        result.add(path);
        trieNode.isEndOfWord = false;
    }

    char original = board[i][j];
    board[i][j] = '#';

    int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    for (int[] dir : directions) {
        int ni = i + dir[0];
        int nj = j + dir[1];
        if (ni >= 0 && ni < board.length && nj >= 0 && nj < board[0].length && board[ni][nj] != '#' && trieNode.children.containsKey(board[ni][nj])) {
            backtrack(board, ni, nj, trieNode.children.get(board[ni][nj]), result, path + board[ni][nj]);
        }
    }

    board[i][j] = original;
}
}