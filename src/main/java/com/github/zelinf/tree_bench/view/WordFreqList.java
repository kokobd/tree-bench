package com.github.zelinf.tree_bench.view;

import com.github.zelinf.tree_bench.view.util.FXMLUtils;
import javafx.scene.layout.AnchorPane;

public class WordFreqList extends AnchorPane {

    private WordFreqListController controller;

    public WordFreqList() {
        controller = FXMLUtils.loadFXML(this);
    }
}
