/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import models.Shift;
import models.resurces.Reader;
import stages.ExceptionStage;

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
    
    @FXML
    private DatePicker startDate;
    
    @FXML
    private DatePicker endDate;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list1 = FXCollections.observableArrayList("А", "Б", "В", "Г");
        shiftName.setItems(list1);
    }    
    
    @FXML
    private void createChart(javafx.event.ActionEvent event) throws Exception{
        if(checkShiftDate()){
            String shift = shiftName.getValue();
            Shift shiftData = new Shift(shift);
            new Reader(shiftData);
            XYChart.Series series1 = new XYChart.Series();
            series1.setName("Смена " + shift);
            ArrayList<LocalDate> date = shiftData.getDate();
            ArrayList<Integer> water = shiftData.getWater();
            for(int i = 0; i < date.size(); i++){
                series1.getData().add(new XYChart.Data(date.get(i).toString(), water.get(i)));
            }
            chart.getData().add(series1);
        }
    }
    
    private boolean checkShiftDate() throws IOException{
        if(shiftName.getValue() == null){
            new ExceptionStage("Смена не выбрана!");
            return false;
        }
        
        if(startDate.getValue() == null || endDate.getValue() == null){
            new ExceptionStage("Должны быть выбраны обе даты!");
            return false;
        }
        
        if(startDate.getValue().isAfter(endDate.getValue())){
            new ExceptionStage("Даты выбраны неверно!");
            return false;
        }
        return true;
    }
}
