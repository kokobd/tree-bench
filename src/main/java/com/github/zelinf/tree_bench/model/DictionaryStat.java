package com.github.zelinf.tree_bench.model;

import com.github.zelinf.tree_bench.dictionary.TreeDictionary;
import com.github.zelinf.tree_bench.dictionary.Word;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class DictionaryStat {

    private TreeDictionary<Word, Integer> dictionary;

    private ReadOnlyStringWrapper dictionaryName = new ReadOnlyStringWrapper();
    private ReadOnlyObjectWrapper<Statistics> statistics = new ReadOnlyObjectWrapper<>();

    public DictionaryStat(TreeDictionary<Word, Integer> dictionary) {
        this.dictionary = dictionary;
        statistics.set(new Statistics());
        dictionaryName.set(dictionary.getName());
    }

    public void addWords(List<Word> words) {
        long begin = System.nanoTime();
        for (Word word : words) {
            dictionary.compute(word, (w, count) -> count == null ? 1 : count + 1);
        }
        long end = System.nanoTime();
        Duration timeElapsed = Duration.of(end - begin, ChronoUnit.NANOS);
        statistics.get().setTimeElapsed(timeElapsed.plus(statistics.get().getTimeElapsed()));

        statistics.getValue().setAllWords(allWordsOf(dictionary));
        statistics.getValue().setTotalWords(dictionary.size());

        Comparator<? super Word> comparator = dictionary.getComparator();
        int numberOfComp = 0;
        if (comparator instanceof CountedComparator) {
            numberOfComp = ((CountedComparator) comparator).getNumberOfComparison();
        }
        statistics.getValue().setNumberOfComp(numberOfComp);

        statistics.getValue().setHeightOfTree(dictionary.height());

        {
            List<Map.Entry<Word, Integer>> deepestEntries = dictionary.deepestEntries();
            ObservableList<WordFrequency> deepestWords = FXCollections.observableArrayList();
            for (Map.Entry<Word, Integer> entry : deepestEntries) {
                deepestWords.add(new WordFrequency(entry.getKey(), entry.getValue()));
            }
            statistics.getValue().setDeepestWords(deepestWords);
        }
    }

    public void clear() {
        dictionary.clear();
        statistics.get().clear();
    }

    private ObservableList<WordFrequency> allWordsOf(TreeDictionary<Word, Integer> dictionary) {
        ObservableList<WordFrequency> allWords = FXCollections.observableArrayList();
        for (Map.Entry<Word, Integer> entry : dictionary) {
            allWords.add(new WordFrequency(entry.getKey(), entry.getValue()));
        }

        return allWords;
    }

    public String getDictionaryName() {
        return dictionaryName.get();
    }

    public ReadOnlyStringProperty dictionaryNameProperty() {
        return dictionaryName.getReadOnlyProperty();
    }

    public Statistics getStatistics() {
        return statistics.get();
    }

    public ReadOnlyObjectProperty<Statistics> statisticsProperty() {
        return statistics.getReadOnlyProperty();
    }
}
