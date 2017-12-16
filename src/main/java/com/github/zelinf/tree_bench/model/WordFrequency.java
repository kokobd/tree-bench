package com.github.zelinf.tree_bench.model;

import com.github.zelinf.tree_bench.dictionary.Word;

public class WordFrequency {

    private Word word;
    private int frequency;

    public WordFrequency() {
    }

    public WordFrequency(Word word, int frequency) {
        this.word = word;
        this.frequency = frequency;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WordFrequency that = (WordFrequency) o;

        //noinspection SimplifiableIfStatement
        if (getFrequency() != that.getFrequency()) return false;
        return getWord() != null ? getWord().equals(that.getWord()) : that.getWord() == null;
    }

    @Override
    public int hashCode() {
        int result = getWord() != null ? getWord().hashCode() : 0;
        result = 31 * result + getFrequency();
        return result;
    }
}
