package tests;

import trie.Trie;
import trie.WordFrequency;
import java.util.List;

public class frequency_test {
    public static void main(String[] args) {
        System.out.println("=== Testing Frequency Logic ===");
        
        Trie trie = new Trie();
        
        // Тест 1: Частота увеличивается при поиске
        System.out.println("\n1. Frequency increases on search:");
        trie.insert("apple");
        System.out.println("Initial frequency: " + trie.getWordFrequency("apple"));
        
        trie.contains("apple");
        System.out.println("After one search: " + trie.getWordFrequency("apple"));
        
        trie.contains("apple");
        trie.contains("apple");
        System.out.println("After three searches: " + trie.getWordFrequency("apple"));
        
        // Тест 2: Автодополнение сортирует по частоте
        System.out.println("\n2. Autocomplete sorts by frequency:");
        trie.insert("application");
        trie.insert("apply");
        
        // Увеличиваем частоты по-разному
        trie.contains("application"); // 1 раз
        trie.contains("apply");
        trie.contains("apply"); // 2 раза
        
        List<WordFrequency> suggestions = trie.getAutocompleteSuggestions("app");
        System.out.println("Autocomplete suggestions:");
        for (WordFrequency wf : suggestions) {
            System.out.println("  " + wf);
        }
        
        // Тест 3: Принудительное изменение частоты
        System.out.println("\n3. Manual frequency setting:");
        trie.setFrequency("apple", 10);
        System.out.println("Apple frequency after manual set: " + trie.getWordFrequency("apple"));
        
        // Тест 4: Поиск несуществующего слова не увеличивает частоту
        System.out.println("\n4. Non-existent word search:");
        boolean found = trie.contains("nonexistent");
        System.out.println("Found 'nonexistent': " + found);
        System.out.println("Frequency of 'nonexistent': " + trie.getWordFrequency("nonexistent"));
        
        System.out.println("\n✓ Frequency tests completed!");
    }
}
