package tests;

import trie.Trie;

public class printtree_test {
    public static void main(String[] args) {
        System.out.println("=== Testing PrintTree Method ===");
        
        Trie trie = new Trie();
        
        // Тест 1: Простое дерево
        System.out.println("\n1. Simple tree:");
        trie.insert("cat");
        trie.insert("car");
        trie.insert("card");
        trie.printTree();
        
        // Тест 2: Дерево с разными ветками
        System.out.println("\n2. Tree with multiple branches:");
        Trie trie2 = new Trie();
        trie2.insert("apple");
        trie2.insert("application");
        trie2.insert("banana");
        trie2.insert("band");
        trie2.printTree();
        
        // Тест 3: Пустое дерево
        System.out.println("\n3. Empty tree:");
        Trie trie3 = new Trie();
        trie3.printTree();
        
        // Тест 4: После удаления
        System.out.println("\n4. After removal:");
        trie2.remove("application");
        trie2.printTree();
        
        System.out.println("\n✓ PrintTree tests completed!");
    }
}
