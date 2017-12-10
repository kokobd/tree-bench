package com.github.zelinf.tree_bench.dictionary.impl;

import com.github.zelinf.tree_bench.dictionary.TreeDictionary;
import com.github.zelinf.tree_bench.dictionary.Word;

import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

/**
 * A {@link TreeDictionary} implementation using {@link TreeMap}
 * in Java Standard Collection Framework.
 */
public class StandardTree extends TreeDictionary {

    private TreeMap<Word, Integer> dict;

    public StandardTree() {
        dict = new TreeMap<>(this::countedCompare);
    }

    public StandardTree(int maxNumTopWords) {
        this();
        this.maxNumTopWords = maxNumTopWords;
    }

    /**
     * Get height of the tree. Not accurate.
     *
     * @return height of the tree.
     */
    @Override
    public int treeHeight() {
        return (int) Math.ceil(Math.log(dict.size()) / Math.log(2));
    }

    @Override
    public List<Word> deepestWord() {
        return Collections.emptyList();
    }

    @Override
    protected int addThenLookup(Word word) {
        return dict.compute(word, (w, freq) -> freq != null ? freq + 1 : 1);
    }

    @Override
    public int totalWords() {
        return dict.size();
    }
}
