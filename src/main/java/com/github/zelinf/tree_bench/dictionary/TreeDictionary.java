package com.github.zelinf.tree_bench.dictionary;

import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.map.HashedMap;

import java.util.List;
import java.util.Map;

/**
 * Dictionaries implemented with tree should extends
 * this class.
 */
public abstract class TreeDictionary implements Dictionary {
    /**
     * Get the height of the tree
     *
     * @return Height of the tree
     */
    public abstract int treeHeight();

    /**
     * Get all words with largest height in the tree
     *
     * @return deepest words
     */
    public abstract List<Word> deepestWord();

    /**
     * Get number of comparison used by far.
     * To make this method work as expected, implementations
     * of this abstract class should use the protected
     * {@link #countedCompare(Word, Word)} instead of
     * {@link Word#compareTo(Word)} for comparing
     *
     * @return number of comparisons
     * @see #countedCompare(Word, Word)
     */
    public final int numberOfComparison() {
        return compareCount;
    }

    /**
     * Compare two words and counts the operation
     *
     * @param w1 one word
     * @param w2 another word
     * @return negative if <code>w1 < w2</code>; 0 if <code>w1 == w2</code>
     * ; positive if <code>w1 > w2</code>
     */
    protected final int countedCompare(Word w1, Word w2) {
        ++compareCount;
        return w1.compareTo(w2);
    }

    private int compareCount = 0;

    private HashedMap<Word, Integer> topWords = new HashedMap<>();

    /**
     * Note to implementers: this field can only be changed
     * in constructors, as {@link #addWord(Word)} assume it
     * is a constant.
     */
    protected int maxNumTopWords = 30;

    @Override
    public final void addWord(Word word) {
        int freq = addThenLookup(word);
        if (topWords.size() == maxNumTopWords
                && !topWords.containsKey(word)) {
            // remove word with least freq
            int leastFreq = Integer.MAX_VALUE;
            Word wordWithLeastFreq = null;

            MapIterator<Word, Integer> wordIt = topWords.mapIterator();
            while (wordIt.hasNext()) {
                wordIt.next();
                if (wordIt.getValue() < leastFreq) {
                    leastFreq = wordIt.getValue();
                    wordWithLeastFreq = wordIt.getKey();
                }
            }
            if (wordWithLeastFreq != null)
                topWords.remove(wordWithLeastFreq);
        }

        topWords.put(word, freq);
    }

    protected abstract int addThenLookup(Word word);

    @Override
    public final Map<Word, Integer> topWords() {
        return topWords;
    }
}
