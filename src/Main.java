import trie.Trie;
import trie.WordFrequency;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Trie trie = new Trie();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("🌳 Welcome to Interactive Trie Console!");
        System.out.println("=======================================");
        
        boolean running = true;
        while (running) {
            printMenu();
            int choice = getMenuChoice();
            clearScreen();
            running = processChoice(choice);
        }
        
        System.out.println("👋 Thank you for using Trie Console!");
        scanner.close();
    }
    
    private static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }
    
    private static void printMenu() {
        System.out.println("\n=== Trie Interactive Console ===");
        System.out.println("1. 📝 Insert word");
        System.out.println("2. 🔍 Check if word exists (increases frequency)");
        System.out.println("3. 📖 Find words by prefix");
        System.out.println("4. 🎯 Smart autocomplete (with frequencies)");
        System.out.println("5. 🗑️ Remove word");
        System.out.println("6. 📋 Show all words");
        System.out.println("7. 📊 Show word frequencies");
        System.out.println("8. 🌲 Show tree structure");
        System.out.println("9. 🧹 Clear all data");
        System.out.println("0. ❌ Exit");
        System.out.print("Choose: ");
    }
    
    private static int getMenuChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    private static boolean processChoice(int choice) {
        switch (choice) {
            case 1 -> insertWord();
            case 2 -> checkWord();
            case 3 -> findWordsByPrefix();
            case 4 -> smartAutocomplete();
            case 5 -> removeWord();
            case 6 -> showAllWords();
            case 7 -> showWordFrequencies();
            case 8 -> showTreeStructure();
            case 9 -> clearData();
            case 0 -> { return false; }
            default -> System.out.println("❌ Invalid choice! Please try again.");
        }
        return true;
    }
    
    private static void insertWord() {
        System.out.print("Enter word to insert: ");
        String word = scanner.nextLine().toLowerCase().trim();
        
        try {
            trie.insert(word);
            System.out.println("✅ Word '" + word + "' inserted successfully!");
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private static void checkWord() {
        System.out.print("Enter word to check: ");
        String word = scanner.nextLine().toLowerCase().trim();
        
        boolean exists = trie.contains(word);
        if (exists) {
            int frequency = trie.getWordFrequency(word);
            System.out.println("✅ Word '" + word + "' exists! Frequency: " + frequency);
        } else {
            System.out.println("❌ Word '" + word + "' not found!");
        }
    }
    
    private static void findWordsByPrefix() {
        System.out.print("Enter prefix: ");
        String prefix = scanner.nextLine().toLowerCase().trim();
        
        List<String> words = trie.getByPrefix(prefix);
        if (words.isEmpty()) {
            System.out.println("❌ No words found with prefix '" + prefix + "'");
        } else {
            System.out.println("📖 Words with prefix '" + prefix + "':");
            for (int i = 0; i < words.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + words.get(i));
            }
        }
    }
    
    private static void smartAutocomplete() {
        System.out.print("Enter prefix for autocomplete: ");
        String prefix = scanner.nextLine().toLowerCase().trim();
        
        List<WordFrequency> suggestions = trie.getAutocompleteSuggestions(prefix);
        if (suggestions.isEmpty()) {
            System.out.println("❌ No suggestions for prefix '" + prefix + "'");
        } else {
            System.out.println("🎯 Autocomplete suggestions for '" + prefix + "' (sorted by popularity):");
            for (int i = 0; i < suggestions.size(); i++) {
                WordFrequency wf = suggestions.get(i);
                System.out.println("  " + (i + 1) + ". " + wf.getWord() + " (used " + wf.getFrequency() + " times)");
            }
            
            System.out.print("Select a word number to use it (or 0 to skip): ");
            try {
                int selection = Integer.parseInt(scanner.nextLine());
                if (selection > 0 && selection <= suggestions.size()) {
                    String selectedWord = suggestions.get(selection - 1).getWord();
                    trie.incrementFrequency(selectedWord);
                    System.out.println("✅ Selected '" + selectedWord + "' - frequency increased!");
                }
            } catch (NumberFormatException e) {
                System.out.println("⏭️ Selection skipped");
            }
        }
    }
    
    private static void removeWord() {
        System.out.print("Enter word to remove: ");
        String word = scanner.nextLine().toLowerCase().trim();
        
        if (!trie.contains(word)) {
            System.out.println("❌ Word '" + word + "' not found in the dictionary!");
            return;
        }
        
        boolean removed = trie.remove(word);
        if (removed) {
            System.out.println("✅ Word '" + word + "' removed successfully!");
        } else {
            System.out.println("✅ Word '" + word + "' removed from dictionary (structure preserved for other words)");
        }
    }
    
    private static void showAllWords() {
        List<String> allWords = trie.getAllWords();
        if (allWords.isEmpty()) {
            System.out.println("📭 Trie is empty!");
        } else {
            System.out.println("📋 All words in trie (" + allWords.size() + " words):");
            for (int i = 0; i < allWords.size(); i++) {
                String word = allWords.get(i);
                int frequency = trie.getWordFrequency(word);
                System.out.println("  " + (i + 1) + ". " + word + " (frequency: " + frequency + ")");
            }
        }
    }
    
    private static void showWordFrequencies() {
        List<String> allWords = trie.getAllWords();
        if (allWords.isEmpty()) {
            System.out.println("📭 Trie is empty!");
        } else {
            System.out.println("📊 Word frequencies:");
            allWords.sort((w1, w2) -> 
                Integer.compare(trie.getWordFrequency(w2), trie.getWordFrequency(w1)));
            
            for (String word : allWords) {
                int frequency = trie.getWordFrequency(word);
                System.out.println("  " + word + ": " + frequency + " uses ");
            }
        }
    }
    
    private static void showTreeStructure() {
        System.out.println("🌲 Trie Structure:");
        trie.printTree();
    }
    
    private static void clearData() {
        System.out.print("Are you sure you want to clear all data? (y/n): ");
        String confirmation = scanner.nextLine().toLowerCase();
        
        if (confirmation.equals("y") || confirmation.equals("yes")) {
            trie = new Trie();
            System.out.println("✅ All data cleared!");
        } else {
            System.out.println("❌ Clear operation cancelled.");
        }
    }
}