package com.github.zelinf.tree_bench.view;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.nio.file.Path;

public class TopWindowController {

    @FXML
    private FileAdder fileAdder;

    @FXML
    private StatisticsPane statisticsPane;

    @FXML
    public void initialize() {
        fileAdder.pathsProperty().addListener((ListChangeListener<Path>) c -> {
            while (c.next()) {
                if (c.wasAdded()) {
                    statisticsPane.getDictionariesModel().addFiles(c.getAddedSubList());
                } else if (c.wasRemoved() && c.getList().isEmpty()) {
                    statisticsPane.getDictionariesModel().clear();
                } else {
                    throw new IllegalStateException("Impossible code path.");
                }
            }
        });
    }
}
