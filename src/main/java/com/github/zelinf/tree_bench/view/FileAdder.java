package com.github.zelinf.tree_bench.view;

import com.github.zelinf.tree_bench.view.util.FXMLUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class FileAdder extends AnchorPane {

    public FileAdder() {
        FXMLUtils.loadFXML(this);
    }

    @FXML
    public void onAddFile(ActionEvent event) {
        System.out.println("Adding file");
    }
}
