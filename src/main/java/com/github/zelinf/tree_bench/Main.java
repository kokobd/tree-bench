package com.github.zelinf.tree_bench;

import com.github.zelinf.tree_bench.view.TopWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("TreeBench");

        TopWindow topWindow = new TopWindow();
        Scene scene = new Scene(topWindow);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
