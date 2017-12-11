package com.github.zelinf.tree_bench.view;

import com.github.zelinf.tree_bench.view.util.FXMLUtils;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.collections.ObservableList;
import javafx.scene.layout.AnchorPane;

import java.nio.file.Path;

public class FileAdder extends AnchorPane {

    private FileAdderController controller;

    public FileAdder() {
        controller = FXMLUtils.loadFXML(this);
    }

    public ObservableList<Path> getPaths() {
        return pathsProperty().get();
    }

    @SuppressWarnings("WeakerAccess")
    public ReadOnlyListProperty<Path> pathsProperty() {
        return controller.pathsProperty();
    }
}
