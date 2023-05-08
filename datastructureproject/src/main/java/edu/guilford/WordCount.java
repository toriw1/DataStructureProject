package edu.guilford;

public class WordCount implements Comparable<WordCount> {

    // attributes
    private String word;
    private int count;

    // constructor
    public WordCount(String word, int count) {
        this.word = word;
        this.count = count;
    }

    // getters and setters
    public String getWord() {
        return word;
    }

    public int getCount() {
        return count;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setCount(int count) {
        this.count = count;
    }

    // methods
    // override the compareTo method
    @Override
    public int compareTo(WordCount o) {
        return Integer.compare(o.count, this.count);
    }
}
