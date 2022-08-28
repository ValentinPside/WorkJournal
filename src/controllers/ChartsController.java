/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author Valentin
 */
public class ChartsController implements Initializable {

    @FXML
    private ComboBox<String> shiftName;
    
    @FXML
    private LineChart chart;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list1 = FXCollections.observableArrayList("А", "Б", "В", "Г");
        shiftName.setItems(list1);
    }    
    
    @FXML
    private void createChart(javafx.event.ActionEvent event) throws Exception{
        String shift = shiftName.getValue();
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Смена " + shift);
        
        series1.getData().add(new XYChart.Data("22.07.2022", 36));
        series1.getData().add(new XYChart.Data("24.07.2022", 41));
        series1.getData().add(new XYChart.Data("28.07.2022", 15));
        
        chart.getData().add(series1);
    }
}
