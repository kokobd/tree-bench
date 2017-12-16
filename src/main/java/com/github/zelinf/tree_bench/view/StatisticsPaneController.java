package com.github.zelinf.tree_bench.view;

import com.github.zelinf.tree_bench.model.DictionariesModel;
import javafx.beans.Observable;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class StatisticsPaneController {

    @FXML
    private TextField numberOfWords;

    @FXML
    private BarChart<String, Long> timeChart;

    @FXML
    private void initialize() {
        dictionariesModel.set(new DictionariesModel());
        getDictionariesModel().getDictionaryStats().get(0).getStatistics().totalWordsProperty()
                .addListener((observable, oldValue, newValue) -> numberOfWords.setText(newValue.toString()));

        setUpTimeChart();
    }

    private void setUpTimeChart() {
        timeChart.getXAxis().setLabel("Trees");
        timeChart.getYAxis().setLabel("Time(ms)");

        XYChart.Series<String, Long> dataSeries = new XYChart.Series<>();
        timeChart.getData().add(dataSeries);

        dictionariesModel.getValue().timeProperties().get(0).addListener((observable, oldValue, newValue) -> {
            ObservableList<XYChart.Data<String, Long>> chartData = FXCollections.observableArrayList();
            List<ReadOnlyStringProperty> nameProperties = dictionariesModel.get().nameProperties();
            List<ObjectProperty<Duration>> timeProperties = dictionariesModel.get().timeProperties();
            for (int i = 0; i < nameProperties.size(); i++) {
                chartData.add(new XYChart.Data<>(nameProperties.get(i).get(),
                        timeProperties.get(i).get().get(ChronoUnit.NANOS) / 1000000));
            }
            dataSeries.setData(chartData);
        });
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
