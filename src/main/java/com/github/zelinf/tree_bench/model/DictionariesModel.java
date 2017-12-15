package com.github.zelinf.tree_bench.model;

import com.github.zelinf.tree_bench.dictionary.TreeDictionary;
import com.github.zelinf.tree_bench.dictionary.TreeDictionaryFactory;
import com.github.zelinf.tree_bench.dictionary.Word;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableList;
import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Calculates statistics and benchmarking several
 * different dictionary implementations.
 */
public class DictionariesModel {

    private List<TreeDictionary<Word, Integer>> dictionaries = new SimpleListProperty<>();

    public DictionariesModel() {
        initDictionaries();
    }

    /**
     * Init or clear {@link #dictionaries}
     */
    private void initDictionaries() {
        dictionaries = TreeDictionaryFactory.allDictionaries();
        // TODO
    }

    public void addFile(Path path) {
        if (!Files.isRegularFile(path)) {
            throw new IllegalArgumentException("Can only add text files");
        }

        List<Word> words;
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            words = Collections.unmodifiableList(Word.readWords(reader));
        } catch (IOException e) {
            e.printStackTrace();
            throw new AddFileException("Failed to add file due to IO error.", e);
        }

        // TODO
    }

    private static Statistics addWordsToDictionary(List<Word> words, TreeDictionary<Word, Integer> dictionary) {
        Statistics statistics = new Statistics();

        long begin = System.nanoTime();
        for (Word word : words) {
            dictionary.compute(word, (w, count) -> count == null ? 1 : count + 1);
        }
        long end = System.nanoTime();
        Duration timeElapsed = Duration.of(end - begin, ChronoUnit.NANOS);

        statistics.setTimeElapsed(timeElapsed);

        statistics.setDeepestWords(
                dictionary.deepestEntries().stream()
                        .map(Map.Entry::getKey)
                        .collect(Collectors.toList()));
        statistics.setHeightOfTree(dictionary.height());
        Comparator<? super Word> comparator = dictionary.getComparator();
        if (comparator instanceof CountedComparator) {
            statistics.setNumberOfComp(((CountedComparator) comparator)
                    .getNumberOfComparison());
        } else {
            statistics.setNumberOfComp(0);
        }
        statistics.setTotalWords(dictionary.size());

        return statistics;
    }

    private List<Pair<Word, Integer>> findTopWords(TreeDictionary<Word, Integer> dictionary) {
        for (Map.Entry<Word, Integer> entry : dictionary) {

        }
        return null; // TODO
    }

    public static class AddFileException extends RuntimeException {
        public AddFileException(String message) {
            super(message);
        }

        public AddFileException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public void clear() {
        initDictionaries();
    }
}
