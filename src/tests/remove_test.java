package tests;

import trie.Trie;
import java.util.List;

public class remove_test {
    public static void main(String[] args) {
        System.out.println("=== Testing Remove Method ===");
        
        Trie trie = new Trie();
        
        // Тест 1: Простое удаление
        System.out.println("\n1. Simple removal:");
        trie.insert("cat");
        System.out.println("Before: contains 'cat' = " + trie.contains("cat"));
        boolean removed = trie.remove("cat");
        System.out.println("Removed 'cat' = " + removed);
        System.out.println("After: contains 'cat' = " + trie.contains("cat"));
        
        // Тест 2: Удаление из цепочки слов
        System.out.println("\n2. Removal from word chain:");
        trie.insert("car");
        trie.insert("card");
        System.out.println("Before: contains 'car' = " + trie.contains("car"));
        System.out.println("Before: contains 'card' = " + trie.contains("card"));
        
        removed = trie.remove("car");
        System.out.println("Removed 'car' = " + removed);
        System.out.println("After: contains 'car' = " + trie.contains("car"));
        System.out.println("After: contains 'card' = " + trie.contains("card"));
        
        // Тест 3: Удаление несуществующего слова
        System.out.println("\n3. Removing non-existent word:");
        removed = trie.remove("dog");
        System.out.println("Removed 'dog' = " + removed);
        
        // Тест 4: Удаление префикса
        System.out.println("\n4. Removing prefix:");
        trie.insert("app");
        trie.insert("apple");
        System.out.println("Before: contains 'app' = " + trie.contains("app"));
        System.out.println("Before: contains 'apple' = " + trie.contains("apple"));
        
        removed = trie.remove("app");
        System.out.println("Removed 'app' = " + removed);
        System.out.println("After: contains 'app' = " + trie.contains("app"));
        System.out.println("After: contains 'apple' = " + trie.contains("apple"));
        
        // Тест 5: Проверка getByPrefix после удаления
        System.out.println("\n5. getByPrefix after removal:");
        List<String> words = trie.getByPrefix("car");
        System.out.println("Words with prefix 'car': " + words);
        
        System.out.println("\n✓ Remove tests completed!");
    }
}
