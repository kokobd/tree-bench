package com.github.zelinf.tree_bench.view.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public final class FXMLUtils {

    public static <T extends Parent> void loadFXML(T controller) {
        FXMLLoader loader = new FXMLLoader();
        loader.setRoot(controller);
        loader.setController(controller);

        String fileName = controller.getClass().getSimpleName() + ".fxml";
        try {
            loader.load(controller.getClass().getResourceAsStream(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
