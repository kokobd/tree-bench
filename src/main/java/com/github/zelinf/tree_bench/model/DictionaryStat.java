package com.github.zelinf.tree_bench.model;

import com.github.zelinf.tree_bench.dictionary.TreeDictionary;
import com.github.zelinf.tree_bench.dictionary.Word;
import javafx.beans.property.*;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class DictionaryStat {

    private TreeDictionary<Word, Integer> dictionary;

    private ReadOnlyStringWrapper dictionaryName = new ReadOnlyStringWrapper();
    private ReadOnlyObjectWrapper<Statistics> statistics = new ReadOnlyObjectWrapper<>();

    public DictionaryStat(TreeDictionary<Word, Integer> dictionary) {
        this.dictionary = dictionary;
    }

    public void addWords(List<Word> words) {
        long begin = System.nanoTime();
        for (Word word : words) {
            dictionary.compute(word, (w, count) -> count == null ? 1 : count + 1);
        }
        long end = System.nanoTime();
        Duration timeElapsed = Duration.of(end - begin, ChronoUnit.NANOS);
        statistics.getValue().setTimeElapsed(timeElapsed);

        statistics.getValue().getTopWords().setAll(findTopWords());
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
            ListProperty<WordFrequency> deepestWords = statistics.getValue().deepestWordsProperty();
            addEntriesToListFreq(deepestEntries, deepestWords);
        }
    }

    private List<WordFrequency> findTopWords() {
        List<WordFrequency> topWords = new ArrayList<>();
        addEntriesToListFreq(dictionary, topWords);

        topWords.sort(Comparator.comparingInt(WordFrequency::getFrequency));
        return topWords;
    }

    private static void addEntriesToListFreq(Iterable<Map.Entry<Word, Integer>> entries,
                                             List<WordFrequency> frequencies) {
        for (Map.Entry<Word, Integer> entry : entries) {
            WordFrequency wordFrequency = new WordFrequency();
            wordFrequency.setWord(entry.getKey());
            wordFrequency.setFrequency(entry.getValue());
            frequencies.add(wordFrequency);
        }
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
