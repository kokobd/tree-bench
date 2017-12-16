package com.github.zelinf.tree_bench.view;

import com.github.zelinf.tree_bench.dictionary.Word;
import com.github.zelinf.tree_bench.model.WordFrequency;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;

public class WordFreqListController {

    @FXML
    private TitledPane titledPane;

    @FXML
    private TableView<WordFrequency> table;

    @FXML
    private TableColumn<WordFrequency, Word> wordColumn;

    @FXML
    private TableColumn<WordFrequency, Number> freqColumn;

    @FXML
    private void initialize() {
        wordColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.6));
        freqColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.4));

        wordColumn.setCellValueFactory(param -> param.getValue().wordProperty());
        freqColumn.setCellValueFactory(param -> param.getValue().frequencyProperty());

        table.itemsProperty().bind(words);
    }

    private ListProperty<WordFrequency> words = new SimpleListProperty<>();

    public ObservableList<WordFrequency> getWords() {
        return words.get();
    }

    public ListProperty<WordFrequency> wordsProperty() {
        return words;
    }

    public void setWords(ObservableList<WordFrequency> words) {
        this.words.set(words);
    }

    public StringProperty titleProperty() {
        return titledPane.textProperty();
    }

    public String getTitle() {
        return titleProperty().get();
    }

    public void setTitle(String newTitle) {
        titleProperty().set(newTitle);
    }
}
