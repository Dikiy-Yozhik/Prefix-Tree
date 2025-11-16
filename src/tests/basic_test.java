package tests;

import java.util.List;
import trie.Trie;

public class basic_test {
    public static void main(String[] args) {
        testInsertAndContains();
        testStartsWith();
        testGetByPrefix();
        testEdgeCases();
        System.out.println("All tests passed! ✅");
    }
    
    private static void testInsertAndContains() {
        Trie trie = new Trie();
        
        trie.insert("cat");
        trie.insert("car");
        trie.insert("card"); 
        
        assert trie.contains("cat") : "Should contain 'cat'";
        assert trie.contains("car") : "Should contain 'car'";
        assert trie.contains("card") : "Should contain 'card'";
        assert !trie.contains("ca") : "Should not contain 'ca'";
        assert !trie.contains("dog") : "Should not contain 'dog'";
        
        System.out.println("✓ Insert and contains tests passed");
    }
    
    private static void testStartsWith() {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("application");
        
        assert trie.startsWith("app") : "Should have words with prefix 'app'";
        assert trie.startsWith("apple") : "Should have words with prefix 'apple'";
        assert !trie.startsWith("ban") : "Should not have words with prefix 'ban'";
        
        System.out.println("✓ StartsWith tests passed");
    }
    
    private static void testGetByPrefix() {
        Trie trie = new Trie();
        trie.insert("cat");
        trie.insert("car");
        trie.insert("card");
        trie.insert("cart");
        
        List<String> words = trie.getByPrefix("ca");
        assert words.size() == 4 : "Should find 4 words with prefix 'ca'";
        assert words.contains("cat") : "Should contain 'cat'";
        assert words.contains("car") : "Should contain 'car'";
        assert words.contains("card") : "Should contain 'card'";
        assert words.contains("cart") : "Should contain 'cart'";
        
        System.out.println("✓ GetByPrefix tests passed");
    }
    
    private static void testEdgeCases() {
        Trie trie = new Trie();
        
        // Пустые строки
        assert !trie.contains("") : "Empty string should not be found";
        assert !trie.startsWith("") : "Empty prefix should not be found";
        
        // Null
        assert !trie.contains(null) : "Null should not be found";
        assert !trie.startsWith(null) : "Null prefix should not be found";
        
        // Невалидные символы
        try {
            trie.insert("cat1");
            assert false : "Should throw exception for invalid characters";
        } catch (IllegalArgumentException e) {
            // Expected
        }
        
        System.out.println("✓ Edge cases tests passed");
    }
}