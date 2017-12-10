package com.github.zelinf.tree_bench.dictionary;

import java.util.Map;

/**
 * A dictionary.
 * Implementations of this class should have
 * a default constructor that creates an empty
 * dictionary.
 */
public interface Dictionary {
    /**
     * Insert a word into this dictionary.
     * If the word already exists, it won't be inserted
     * again.
     *
     * @param word the word to be inserted
     */
    void addWord(Word word);

    /**
     * Get words with top 30 frequency.
     *
     * @return The number of times each word appears.
     */
    Map<Word, Integer> topWords();

    /**
     * Get the total number of words in the dictionary
     *
     * @return Total number of words
     */
    int totalWords();
}
