package com.github.zelinf.tree_bench.view;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MainController {

    @FXML
    private void onSayHello(ActionEvent event) {
        System.out.println("Hello from JavaFX 8");
        System.out.println(event);
    }

    @FXML
    private JFXButton raisedButton;

    @FXML
    private JFXButton disabledButton;

    @FXML
    private void initialize() {
        raisedButton.setOnAction(e -> disabledButton.setDisable(!disabledButton.isDisable()));
    }
}
