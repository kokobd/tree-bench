package com.github.zelinf.tree_bench.model;

import com.github.zelinf.tree_bench.dictionary.TreeDictionary;
import com.github.zelinf.tree_bench.dictionary.TreeDictionaryFactory;
import com.github.zelinf.tree_bench.dictionary.Word;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
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

    private ReadOnlyListWrapper<DictionaryStat> dictionaryStats = new ReadOnlyListWrapper<>();

    public DictionariesModel() {
        initDictionaries();
    }

    private void initDictionaries() {
        List<TreeDictionary<Word, Integer>> dictionaries =
                TreeDictionaryFactory.allDictionaries();
        dictionaryStats.set(FXCollections.observableArrayList());
        for (TreeDictionary<Word, Integer> dictionary : dictionaries) {
            DictionaryStat dictionaryStat = new DictionaryStat(dictionary);
            dictionaryStats.add(dictionaryStat);
        }
    }

    public void addFiles(Collection<? extends Path> paths) {
        List<Word> words = new ArrayList<>();
        for (Path path : paths) {
            if (Files.isRegularFile(path)) {
                try (BufferedReader reader = Files.newBufferedReader(path)) {
                    words.addAll(Word.readWords(reader));
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new AddFileException("Failed to add file due to IO error.", e);
                }
            }
        }

        for (DictionaryStat stat : dictionaryStats) {
            stat.addWords(words);
        }
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
        for (DictionaryStat stat : dictionaryStats) {
            stat.clear();
        }
    }

    private List<ReadOnlyStringProperty> treeNameProperties;

    public List<ReadOnlyStringProperty> nameProperties() {
        if (treeNameProperties == null) {
            treeNameProperties = new ArrayList<>();
            for (DictionaryStat stat : getDictionaryStats()) {
                treeNameProperties.add(stat.dictionaryNameProperty());
            }
        }
        return treeNameProperties;
    }

    private List<ObjectProperty<Duration>> timeProperties;

    public List<ObjectProperty<Duration>> timeProperties() {
        if (timeProperties == null) {
            timeProperties = new ArrayList<>();
            for (DictionaryStat stat : getDictionaryStats()) {
                timeProperties.add(stat.getStatistics().timeElapsedProperty());
            }
        }
        return timeProperties;
    }

    public ObservableList<DictionaryStat> getDictionaryStats() {
        return dictionaryStatsProperty().getValue();
    }

    public ReadOnlyListProperty<DictionaryStat> dictionaryStatsProperty() {
        return dictionaryStats.getReadOnlyProperty();
    }
}
