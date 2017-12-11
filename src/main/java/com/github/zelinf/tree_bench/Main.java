package com.github.zelinf.tree_bench;

import com.github.zelinf.tree_bench.view.TopWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("My Application");

        TopWindow topWindow = new TopWindow();
        Scene scene = new Scene(topWindow);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
