class Trie {
    Node root;
    public Trie() {
        this.root = new Node();
    }

    public void insert(String word) {
        insert(this.root, word);
    }


    private void insert(Node node, String word) {
        if (word.length() == 0) {
            node.isEndOfWord = true;
            return;
        }

        char c = word.charAt(0);
        Node child = node.children.get(c);

        if (child == null) {
            child = new Node();
            node.children.put(c, child);
        }

        insert(child, word.substring(1));
    }

    public boolean search(String word) {
        return search(root, word);
    }

    private boolean search(Node node, String word) {
        if (word.length() == 0) {
            return node.isEndOfWord;
        }

        char c = word.charAt(0);
        Node child = node.children.get(c);

        if (child == null) {
            return false;
        }

        return search(child, word.substring(1));
    }

    public boolean startsWith(String prefix) {
        return startsWith(root, prefix);
    }

    private boolean startsWith(Node node, String prefix) {
        if (prefix.length() == 0) {
            return true;
        }

        char c = prefix.charAt(0);
        Node child = node.children.get(c);

        if (child == null) {
            return false;
        }

        return startsWith(child, prefix.substring(1));
    }
}

class Node {
    public Map<Character, Node> children;
    public boolean isEndOfWord;

    public Node() {
        this.children = new HashMap<>();
        this.isEndOfWord = false;
    }
}
/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */