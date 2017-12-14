package com.github.zelinf.tree_bench.model;

import com.github.zelinf.tree_bench.dictionary.Word;
import javafx.util.Pair;

import java.time.Duration;
import java.util.List;

public class Statistics {

    private List<Pair<Word, Integer>> topWords;
    private int totalWords;
    private int numberOfComp;
    private Duration timeElapsed;
    private int heightOfTree;
    private List<Word> deepestWords;

    public Statistics() {
    }

    public List<Pair<Word, Integer>> getTopWords() {
        return topWords;
    }

    public void setTopWords(List<Pair<Word, Integer>> topWords) {
        this.topWords = topWords;
    }

    public int getTotalWords() {
        return totalWords;
    }

    public void setTotalWords(int totalWords) {
        this.totalWords = totalWords;
    }

    public int getNumberOfComp() {
        return numberOfComp;
    }

    public void setNumberOfComp(int namOfComparision) {
        this.numberOfComp = namOfComparision;
    }

    public Duration getTimeElapsed() {
        return timeElapsed;
    }

    public void setTimeElapsed(Duration timeUsed) {
        this.timeElapsed = timeUsed;
    }

    public int getHeightOfTree() {
        return heightOfTree;
    }

    public void setHeightOfTree(int heightOfTree) {
        this.heightOfTree = heightOfTree;
    }

    public List<Word> getDeepestWords() {
        return deepestWords;
    }

    public void setDeepestWords(List<Word> deepestWords) {
        this.deepestWords = deepestWords;
    }
}
