package com.github.zelinf.tree_bench.view;

import com.github.zelinf.tree_bench.view.util.FXMLUtils;
import javafx.scene.layout.AnchorPane;

public class FileAdder extends AnchorPane {

    private FileAdderController controller;

    public FileAdder() {
        controller = FXMLUtils.loadFXML(this);
    }

}
