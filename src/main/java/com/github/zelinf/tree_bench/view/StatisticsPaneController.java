package com.github.zelinf.tree_bench.view;

import com.github.zelinf.tree_bench.dictionary.Word;
import com.github.zelinf.tree_bench.model.DictionariesModel;
import com.github.zelinf.tree_bench.model.DictionaryStat;
import com.github.zelinf.tree_bench.model.WordFrequency;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.time.Duration;

public class StatisticsPaneController {

    @FXML
    private void initialize() {
        dictionariesModel.set(new DictionariesModel());
        setUpStatTable();
        setUpAllWordsTable();
        setUpDeepestWordsTable();
    }

    @FXML
    private TableView<DictionaryStat> statTable;

    @FXML
    private TableColumn<DictionaryStat, String> dictNameColumn;

    @FXML
    private TableColumn<DictionaryStat, Duration> timeConsumedColumn;

    @FXML
    private TableColumn<DictionaryStat, Number> comparisonCountColumn;

    @FXML
    private TableColumn<DictionaryStat, Number> treeHeightColumn;

    @FXML
    private TableColumn<DictionaryStat, Number> totalWordsColumn;

    private void setUpStatTable() {
        dictNameColumn.setCellValueFactory(param -> param.getValue().dictionaryNameProperty());
        timeConsumedColumn.setCellValueFactory(param -> param.getValue().getStatistics().timeElapsedProperty());
        timeConsumedColumn.setCellFactory(new Callback<TableColumn<DictionaryStat, Duration>, TableCell<DictionaryStat, Duration>>() {
            @Override
            public TableCell<DictionaryStat, Duration> call(TableColumn<DictionaryStat, Duration> param) {
                return new TableCell<DictionaryStat, Duration>() {
                    @Override
                    protected void updateItem(Duration item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setText(null);
                        } else {
                            setText(Integer.valueOf(item.getNano() / 1000_000).toString());
                        }
                    }
                };
            }
        });
        comparisonCountColumn.setCellValueFactory(param -> param.getValue().getStatistics().numberOfCompProperty());
        treeHeightColumn.setCellValueFactory(param -> param.getValue().getStatistics().heightOfTreeProperty());
        totalWordsColumn.setCellValueFactory(param -> param.getValue().getStatistics().totalWordsProperty());

        statTable.setItems(getDictionariesModel().getDictionaryStats());
    }

    @FXML
    private WordFreqList allWordsTable;

    private void setUpAllWordsTable() {
        allWordsTable.titleProperty().set("All Words");
        allWordsTable.wordsProperty().bind(getDictionariesModel().getDictionaryStats().get(0).getStatistics().allWordsProperty());
    }

    @FXML
    private WordFreqList deepestWordsTable;

    private void setUpDeepestWordsTable() {
        deepestWordsTable.titleProperty().set("Deepest Words");
        deepestWordsTable.wordsProperty().bind(getDictionariesModel().getDictionaryStats().get(1).getStatistics().deepestWordsProperty());
    }


    private ReadOnlyObjectWrapper<DictionariesModel> dictionariesModel = new ReadOnlyObjectWrapper<>();

    public DictionariesModel getDictionariesModel() {
        return dictionariesModel.get();
    }

    public ReadOnlyObjectProperty<DictionariesModel> dictionariesModelProperty() {
        return dictionariesModel.getReadOnlyProperty();
    }

    public void setDictionariesModel(DictionariesModel dictionariesModel) {
        this.dictionariesModel.set(dictionariesModel);
    }

}
