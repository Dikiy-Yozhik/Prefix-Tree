package trie;

public class WordFrequency implements Comparable<WordFrequency> {
    private final String word;
    private final int frequency;

    public WordFrequency(String word, int frequency) {
        this.word = word;
        this.frequency = frequency;
    }

    public String getWord() {
        return word;
    }

    public int getFrequency() {
        return frequency;
    }

    @Override
    public int compareTo(WordFrequency other) {
        return Integer.compare(other.frequency, this.frequency);
    }

    @Override
    public String toString() {
        return word + " (" + frequency + ")";
    }
}
