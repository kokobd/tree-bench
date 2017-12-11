package com.github.zelinf.tree_bench.view.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public final class FXMLUtils {

    public static <T extends Parent, C> C loadFXML(T view) {
        FXMLLoader loader = new FXMLLoader();
        loader.setRoot(view);
        loader.setClassLoader(view.getClass().getClassLoader());

        String fileName = view.getClass().getSimpleName() + ".fxml";
        C controller;
        try {
            loader.load(view.getClass().getResourceAsStream(fileName));
            controller = loader.getController();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return controller;
    }
}
