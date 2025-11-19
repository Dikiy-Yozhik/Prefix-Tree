package tests;

import trie.Trie;
import java.util.List;

public class getall_test {
    public static void main(String[] args) {
        System.out.println("=== Testing GetAllWords Method ===");
        
        Trie trie = new Trie();
        
        // Тест 1: Пустое дерево
        System.out.println("\n1. Empty tree:");
        List<String> words = trie.getAllWords();
        System.out.println("All words: " + words);
        System.out.println("Size: " + words.size());
        
        // Тест 2: Несколько слов
        System.out.println("\n2. Multiple words:");
        trie.insert("cat");
        trie.insert("car");
        trie.insert("card");
        trie.insert("apple");
        trie.insert("application");
        
        words = trie.getAllWords();
        System.out.println("All words: " + words);
        System.out.println("Size: " + words.size());
        
        // Тест 3: После удаления
        System.out.println("\n3. After removal:");
        trie.remove("car");
        words = trie.getAllWords();
        System.out.println("All words after removing 'car': " + words);
        
        // Тест 4: Проверка порядка (алфавитный)
        System.out.println("\n4. Alphabetical order:");
        Trie trie2 = new Trie();
        trie2.insert("zoo");
        trie2.insert("apple");
        trie2.insert("banana");
        words = trie2.getAllWords();
        System.out.println("Words in order: " + words);
        
        System.out.println("\n✓ GetAllWords tests completed!");
    }
}
