package com.github.zelinf.tree_bench.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class FileAdderController {

    @FXML
    private ListView<Path> fileListView;

    @FXML
    void onAdd(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose file(s) to add");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );
        Window rootWindow = ((Node) event.getSource()).getScene().getWindow();
        List<File> chooseFiles = fileChooser.showOpenMultipleDialog(rootWindow);
        if (chooseFiles != null) {
            fileListView.itemsProperty().getValue().addAll(
                    chooseFiles.stream()
                            .map(File::toPath)
                            .collect(Collectors.toList())
            );
        }
    }

    @FXML
    @SuppressWarnings("UnusedParameters")
    void onClear(ActionEvent ignored) {
        fileListView.itemsProperty().getValue().clear();
    }
}
