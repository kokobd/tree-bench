package com.github.zelinf.tree_bench.view;

import com.github.zelinf.tree_bench.model.WordFrequency;
import com.github.zelinf.tree_bench.view.util.FXMLUtils;
import javafx.beans.property.ListProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.layout.AnchorPane;

public class WordFreqList extends AnchorPane {

    private WordFreqListController controller;

    public WordFreqList() {
        controller = FXMLUtils.loadFXML(this);
    }

    public ObservableList<WordFrequency> getWords() {
        return controller.getWords();
    }

    public ListProperty<WordFrequency> wordsProperty() {
        return controller.wordsProperty();
    }

    public void setWords(ObservableList<WordFrequency> words) {
        controller.setWords(words);
    }

    public StringProperty titleProperty() {
        return controller.titleProperty();
    }

    public String getTitle() {
        return titleProperty().get();
    }

    public void setTitle(String title) {
        titleProperty().set(title);
    }
}
