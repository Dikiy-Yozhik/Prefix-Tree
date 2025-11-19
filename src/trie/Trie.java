package trie;

import java.util.ArrayList;
import java.util.List;

public class Trie {
    private final TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

// ====================== Базовые методы ============================    

    public void insert(String word) {                 
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

    public boolean contains(String word) {             
        if (word == null || word.isEmpty()) {
            return false;
        }

        TrieNode node = findNode(word);
        return node != null && node.isEndOfWord();
    }

    public boolean startsWith(String prefix) {          
        if (prefix == null || prefix.isEmpty()) {
            return false;
        }

        return findNode(prefix) != null;
    }

    public List<String> getByPrefix(String prefix) {     
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

// ==========================================================================

// ======================== Дополнительные методы ===========================

    public boolean remove(String word) {
        if (word == null || word.isEmpty()) {
            return false;
        }

        return remove(root, word, 0);
    }

    public List<String> getAllWords() {
        List<String> allWords = new ArrayList<>();
        collectWords(root, "", allWords);
        return allWords;
    }

    public void printTree() {
        System.out.println("Trie Structure:");
        System.out.println("└── root");
        printNode(root, "", true, ' ');
    }

// ======================== Вспомогательные методы ==========================

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

    /* Рекурсивный метод удаления
    current текущий узел
    word слово для удаления
    index текущий индекс в слове
    */
    private boolean remove(TrieNode cur, String word, int index) {
        if (index == word.length()) {
            if (!cur.isEndOfWord()) {
                return false;
            }

            cur.setEndOfWord(false);
            return !hasChildren(cur);
        }

        char c = word.charAt(index);
        TrieNode child = cur.getChild(c);

        if (child == null) return false;

        boolean canDel = remove(child, word, index + 1);
        if (canDel) {
            cur.setChild(c, null);
            return !hasChildren(cur) && !cur.isEndOfWord();
        }

        return false;
    }

    private boolean hasChildren(TrieNode node) {
        for (char c = 'a'; c <= 'z'; c++) {
            if (node.hasChild(c)) return true;
        }
        return false;
    }

    private void printNode(TrieNode node, String prefix, boolean isLast, char currentChar) {
        if (node == null) return;

        // Собираем детей которые существуют
        List<Character> existingChildren = new ArrayList<>();
        for (char c = 'a'; c <= 'z'; c++) {
            if (node.hasChild(c)) {
                existingChildren.add(c);
            }
        }
        
        // Выводим текущий узел с буквой и отметкой о слове
        if (prefix.length() > 0) { // Не выводим корневой узел
            String connector = isLast ? "└── " : "├── ";
            String wordMarker = node.isEndOfWord() ? "*" : "";
            System.out.println(prefix + connector + "'" + currentChar + "'" + wordMarker);
        }
        
        // Рекурсивно выводим детей
        String childPrefix = prefix + (isLast ? "    " : "│   ");
        for (int i = 0; i < existingChildren.size(); i++) {
            char childChar = existingChildren.get(i);
            boolean lastChild = (i == existingChildren.size() - 1);
            
            printNode(node.getChild(childChar), childPrefix, lastChild, childChar);
        }
    }
}
