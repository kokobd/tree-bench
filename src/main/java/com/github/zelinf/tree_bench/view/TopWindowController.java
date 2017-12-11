package com.github.zelinf.tree_bench.view;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;

import java.nio.file.Path;

public class TopWindowController {

    @FXML
    private FileAdder fileAdder;

    @FXML
    private StatisticsPane statisticsPane;

    @FXML
    public void initialize() {
        fileAdder.pathsProperty().addListener(new ListChangeListener<Path>() {
            @Override
            public void onChanged(Change<? extends Path> c) {
                // TODO call methods on model.
            }
        });
    }
}
