package com.github.zelinf.tree_bench.model;

import com.github.zelinf.tree_bench.dictionary.Word;
import javafx.beans.property.*;
import javafx.collections.ObservableList;
import javafx.util.Pair;

import java.time.Duration;
import java.util.List;

public class Statistics {

    private ListProperty<WordFrequency> topWords = new SimpleListProperty<>();
    private IntegerProperty totalWords = new SimpleIntegerProperty();
    private IntegerProperty numberOfComp = new SimpleIntegerProperty();
    private ObjectProperty<Duration> timeElapsed = new SimpleObjectProperty<>();
    private IntegerProperty heightOfTree = new SimpleIntegerProperty();
    private ListProperty<WordFrequency> deepestWords = new SimpleListProperty<>();

    public Statistics() {
    }

    public ObservableList<WordFrequency> getTopWords() {
        return topWords.get();
    }

    public ListProperty<WordFrequency> topWordsProperty() {
        return topWords;
    }

    public void setTopWords(ObservableList<WordFrequency> topWords) {
        this.topWords.set(topWords);
    }

    public int getTotalWords() {
        return totalWords.get();
    }

    public IntegerProperty totalWordsProperty() {
        return totalWords;
    }

    public void setTotalWords(int totalWords) {
        this.totalWords.set(totalWords);
    }

    public int getNumberOfComp() {
        return numberOfComp.get();
    }

    public IntegerProperty numberOfCompProperty() {
        return numberOfComp;
    }

    public void setNumberOfComp(int numberOfComp) {
        this.numberOfComp.set(numberOfComp);
    }

    public Duration getTimeElapsed() {
        return timeElapsed.get();
    }

    public ObjectProperty<Duration> timeElapsedProperty() {
        return timeElapsed;
    }

    public void setTimeElapsed(Duration timeElapsed) {
        this.timeElapsed.set(timeElapsed);
    }

    public int getHeightOfTree() {
        return heightOfTree.get();
    }

    public IntegerProperty heightOfTreeProperty() {
        return heightOfTree;
    }

    public void setHeightOfTree(int heightOfTree) {
        this.heightOfTree.set(heightOfTree);
    }

    public ObservableList<WordFrequency> getDeepestWords() {
        return deepestWords.get();
    }

    public ListProperty<WordFrequency> deepestWordsProperty() {
        return deepestWords;
    }

    public void setDeepestWords(ObservableList<WordFrequency> deepestWords) {
        this.deepestWords.set(deepestWords);
    }
}
