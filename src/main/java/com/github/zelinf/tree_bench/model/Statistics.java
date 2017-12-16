package com.github.zelinf.tree_bench.model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.Duration;

public class Statistics {

    private ListProperty<WordFrequency> allWords = new SimpleListProperty<>();
    private IntegerProperty totalWords = new SimpleIntegerProperty();
    private IntegerProperty numberOfComp = new SimpleIntegerProperty();
    private ObjectProperty<Duration> timeElapsed = new SimpleObjectProperty<>();
    private IntegerProperty heightOfTree = new SimpleIntegerProperty();
    private ListProperty<WordFrequency> deepestWords = new SimpleListProperty<>();

    public Statistics() {
        init();
    }

    public void clear() {
        init();
    }

    private void init() {
        allWords.set(FXCollections.observableArrayList());
        totalWords.set(0);
        numberOfComp.set(0);
        timeElapsed.set(Duration.ofMillis(0));
        heightOfTree.set(0);
        deepestWords.set(FXCollections.observableArrayList());
    }

    public ObservableList<WordFrequency> getAllWords() {
        return allWords.get();
    }

    public ListProperty<WordFrequency> allWordsProperty() {
        return allWords;
    }

    public void setAllWords(ObservableList<WordFrequency> allWords) {
        this.allWords.set(allWords);
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
