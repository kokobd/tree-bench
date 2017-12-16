package com.github.zelinf.tree_bench.model;

import com.github.zelinf.tree_bench.dictionary.TreeDictionary;
import com.github.zelinf.tree_bench.dictionary.TreeDictionaryFactory;
import com.github.zelinf.tree_bench.dictionary.Word;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
            dictionary.setComparator(new CountedComparator<>(Word::compareTo));
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

    public ObservableList<DictionaryStat> getDictionaryStats() {
        return dictionaryStatsProperty().getValue();
    }

    public ReadOnlyListProperty<DictionaryStat> dictionaryStatsProperty() {
        return dictionaryStats.getReadOnlyProperty();
    }
}
