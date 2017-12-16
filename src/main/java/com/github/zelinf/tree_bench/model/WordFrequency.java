package com.github.zelinf.tree_bench.model;

import com.github.zelinf.tree_bench.dictionary.Word;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class WordFrequency {

    private ObjectProperty<Word> word = new SimpleObjectProperty<>();
    private IntegerProperty frequency = new SimpleIntegerProperty();

    public WordFrequency() {
    }

    public WordFrequency(Word word, int frequency) {
        this.word.set(word);
        this.frequency.set(frequency);
    }

    public Word getWord() {
        return word.get();
    }

    public ObjectProperty<Word> wordProperty() {
        return word;
    }

    public void setWord(Word word) {
        this.word.set(word);
    }

    public int getFrequency() {
        return frequency.get();
    }

    public IntegerProperty frequencyProperty() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency.set(frequency);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WordFrequency that = (WordFrequency) o;

        //noinspection SimplifiableIfStatement
        if (!word.equals(that.word)) return false;
        return frequency.equals(that.frequency);
    }

    @Override
    public int hashCode() {
        int result = word.hashCode();
        result = 31 * result + frequency.hashCode();
        return result;
    }
}
