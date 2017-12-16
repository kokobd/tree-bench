package com.github.zelinf.tree_bench.view;

import com.github.zelinf.tree_bench.model.DictionariesModel;
import com.github.zelinf.tree_bench.view.util.FXMLUtils;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.scene.layout.AnchorPane;

public class StatisticsPane extends AnchorPane {

    private StatisticsPaneController controller;

    public StatisticsPane() {
        controller = FXMLUtils.loadFXML(this);
    }

    public DictionariesModel getDictionariesModel() {
        return controller.getDictionariesModel();
    }

    public ReadOnlyObjectProperty<DictionariesModel> dictionariesModelProperty() {
        return controller.dictionariesModelProperty();
    }
}
