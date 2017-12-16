package com.github.zelinf.tree_bench.view;

import com.github.zelinf.tree_bench.dictionary.Word;
import com.github.zelinf.tree_bench.model.WordFrequency;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class WordFreqListController {

    @FXML
    private TableView<WordFrequency> table;

    @FXML
    private TableColumn<TableView<WordFrequency>, Word> wordColumn;

    @FXML
    private TableColumn<TableView<WordFrequency>, Integer> freqColumn;

    @FXML
    private void initialize() {
        wordColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.6));
        freqColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.4));
    }
}
