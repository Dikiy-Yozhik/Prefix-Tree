package trie;

import java.util.ArrayList;
import java.util.List;

public class Trie {
    private final TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String word) {                 //вставка слова;
        validateWord(word);
        if (word.isEmpty()) return;

        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            if (!cur.hasChild(c)) {
                cur.setChild(c, new TrieNode());
            }
            cur = cur.getChild(c);
        }
        cur.setEndOfWord(true);
    } 

    public boolean contains(String word) {             //проверка наличия слова;
        if (word == null || word.isEmpty()) {
            return false;
        }

        TrieNode node = findNode(word);
        return node != null && node.isEndOfWord();
    }

    public boolean startsWith(String prefix) {          //проверка существования слов с данным префиксом;
        if (prefix == null || prefix.isEmpty()) {
            return false;
        }

        return findNode(prefix) != null;
    }

    public List<String> getByPrefix(String prefix) {     //получение всех слов по префиксу.
        List<String> results = new ArrayList<>();
        if (prefix == null || prefix.isEmpty()) {
            return results;
        }

        TrieNode prefixNode = findNode(prefix);
        if (prefixNode != null) {
            collectWords(prefixNode, prefix, results);
        }
        return results;
    }

    private void validateWord(String word) {
        if (word == null) {
            throw new IllegalArgumentException("Word cannot be null");
        }
        for (char c : word.toCharArray()) {
            if (!isValidChar(c)) {
                throw new IllegalArgumentException("Word contains invalid characters: " + word);
            }
        }
    }
    
    private boolean isValidChar(char c) {
        return c >= 'a' && c <= 'z';
    }

    private TrieNode findNode(String word) {
        validateWord(word);
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            if (!cur.hasChild(c)) {
                return null;
            }
            cur = cur.getChild(c);
        }
        return cur;
    }

    private void collectWords(TrieNode node, String currentWord, List<String> results) {
        if (node.isEndOfWord()) {
            results.add(currentWord);
        }

        for (char c = 'a'; c <= 'z'; c++) {
            if (node.hasChild(c)) {
                collectWords(node.getChild(c), currentWord + c, results);
            }
        }
    }
}
