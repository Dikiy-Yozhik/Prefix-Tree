package trie;

public class TrieNode {
    private static final int ALPHABET_SIZE = 26;

    private final TrieNode[] children;
    private boolean endOfWord;

    public TrieNode() {
        this.children = new TrieNode[ALPHABET_SIZE];
        this.endOfWord = false;
    }

    private int charToIndex(char c) {
        return c - 'a';
    }

    public TrieNode getChild(char c) {
        return children[charToIndex(c)];
    }

    public TrieNode[] getChildren() {
        return children;
    }

    public void setChild(char c, TrieNode node) {
        children[charToIndex(c)] = node;
    }

    public boolean hasChild(char c) {
        return children[charToIndex(c)] != null;
    }

    public boolean isEndOfWord() {
        return endOfWord;
    }

    public void setEndOfWord(boolean flag) {
        endOfWord = flag;
    }
} 
