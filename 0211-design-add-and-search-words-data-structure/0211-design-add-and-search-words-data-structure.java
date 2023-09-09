class WordDictionary {
    Node root;  
    public WordDictionary() {
        this.root = new Node();
    }
    
    public void addWord(String word) {
        addWord(this.root, word);
    }
    private void addWord(Node node, String word) {
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

        addWord(child, word.substring(1));
    }
    
   public boolean search(String word) {
        return search(root, word, 0);
    }

    private boolean search(Node node, String word,int index) {
        if (index == word.length()) {
            return node.isEndOfWord;
        }

        char c = word.charAt(index);
        
        if (c != '.') {
            Node child = node.children.get(c);
            if (child == null) {
                return false;
            }
            return search(child, word, index + 1);
        } else {
            for (Node child : node.children.values()) {
                if (search(child, word, index + 1)) {
                    return true;
                }
            }
            return false;
        }
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
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */