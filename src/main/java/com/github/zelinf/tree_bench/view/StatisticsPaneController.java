package com.github.zelinf.tree_bench.view;

import com.github.zelinf.tree_bench.model.DictionariesModel;
import com.github.zelinf.tree_bench.model.DictionaryStat;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.time.Duration;

public class StatisticsPaneController {

    @FXML
    private void initialize() {
        dictionariesModel.set(new DictionariesModel());
        setUpStatTable();
        setUpAllWordsTable();
        setUpDeepestWordsTable();

        topLabel.textProperty().bind(new StringBinding() {
            {
                super.bind(totalWordsProperty());
            }

            @Override
            protected String computeValue() {
                return "Total number of words: " + totalWordsProperty().get();
            }
        });
        totalWords.bind(getDictionariesModel().totalWordsProperty());
    }

    private IntegerProperty totalWords = new SimpleIntegerProperty();

    public int getTotalWords() {
        return totalWords.get();
    }

    public IntegerProperty totalWordsProperty() {
        return totalWords;
    }

    public void setTotalWords(int totalWords) {
        this.totalWords.set(totalWords);
    }

    @FXML
    private Label topLabel;

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

        statTable.setItems(getDictionariesModel().getDictionaryStats());
    }

    @FXML
    private WordFreqList allWordsTable;

    private void setUpAllWordsTable() {
        allWordsTable.titleProperty().set("All Words");
        allWordsTable.wordsProperty().bind(getDictionariesModel().getDictionaryStats().get(0).getStatistics().allWordsProperty());
    }

    @FXML
    private ComboBox<DictionaryStat> deepestWordsTreeSelector;

    @FXML
    private WordFreqList deepestWordsTable;

    private void setUpDeepestWordsTable() {
        deepestWordsTable.titleProperty().set("Deepest Words");
        deepestWordsTable.wordsProperty().bind(getDictionariesModel().getDictionaryStats().get(1).getStatistics().deepestWordsProperty());

        deepestWordsTreeSelector.setItems(getDictionariesModel().getDictionaryStats());
        deepestWordsTreeSelector.setConverter(new StringConverter<DictionaryStat>() {
            @Override
            public String toString(DictionaryStat stat) {
                return stat.getDictionaryName();
            }

            @Override
            public DictionaryStat fromString(String string) {
                return null;
            }
        });
        deepestWordsTreeSelector.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            deepestWordsTable.wordsProperty().unbind();
            deepestWordsTable.wordsProperty().bind(newValue.getStatistics().deepestWordsProperty());
        });
        deepestWordsTreeSelector.getSelectionModel().select(0);
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
