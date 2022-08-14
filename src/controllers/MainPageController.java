/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import models.resurces.Writer;
import stages.ExceptionStage;
import stages.ReportStage;
import stages.SuccessStage;

/**
 *
 * @author Valentin
 */
public class MainPageController implements Initializable {
    @FXML
    private ComboBox<String> shiftName;
    
    @FXML
    private ComboBox<String> shiftVariant;
    
    @FXML
    private DatePicker todayDate;
    
    @FXML
    private TextField waterValue1;
    
    @FXML
    private TextField waterValue2;
    
    @FXML
    private TextField hoursValue1;
    
    @FXML
    private TextField hoursValue2;
    
    @FXML
    private TextField cisternValue1;
    
    @FXML
    private TextField cisternValue2;
    
    @FXML
    private void createReport(javafx.event.ActionEvent event) throws Exception{
        new ReportStage();
    }
    
    @FXML
    private void saveShiftDate(javafx.event.ActionEvent event) throws Exception{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String today = dateFormat.format(date);
        if(checkShiftDate(shiftName, shiftVariant, todayDate, today, waterValue1, waterValue2, hoursValue1, hoursValue2,
        cisternValue1, cisternValue2)){
            String currentshiftName = shiftName.getValue();
            String currentshiftVariant = shiftVariant.getValue();
            LocalDate localDate = todayDate.getValue();
            String shiftDay = String.valueOf(localDate);
            Integer currentWaterValue1 = Integer.parseInt(waterValue1.getText().replaceAll("[^\\d]", ""));
            Integer currentWaterValue2 = Integer.parseInt(waterValue2.getText().replaceAll("[^\\d]", ""));
            Integer currentHoursValue1 = Integer.parseInt(hoursValue1.getText().replaceAll("[^\\d]", ""));
            Integer currentHoursValue2 = Integer.parseInt(hoursValue2.getText().replaceAll("[^\\d]", ""));
            Integer currentCisternValue1 = Integer.parseInt(cisternValue1.getText().replaceAll("[^\\d]", ""));
            Integer currentCisternValue2 = Integer.parseInt(cisternValue2.getText().replaceAll("[^\\d]", ""));
            Integer waterValue = currentWaterValue2 - currentWaterValue1;
            Integer hourseValue = currentHoursValue2 - currentHoursValue1;
            Integer expenditure =  expenditure(currentCisternValue2, currentCisternValue1,
            currentWaterValue2, currentWaterValue1);
            String record = shiftDay + "/" + waterValue + "/" + hourseValue + "/" + expenditure + "/" + "\n";
            new Writer(currentshiftName, record);
            new SuccessStage("Запись успешно сохранена");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list1 = FXCollections.observableArrayList("А", "Б", "В", "Г");
        shiftName.setItems(list1);
        
        ObservableList<String> list2 = FXCollections.observableArrayList("Дневная смена", "Ночная смена");
        shiftVariant.setItems(list2);
    }
    
    private boolean checkShiftDate(ComboBox<String> shiftName, ComboBox<String> shiftVariant, DatePicker todayDate, 
            String today, TextField waterValue1, TextField waterValue2, TextField hoursValue1, TextField hoursValue2,
            TextField cisternValue1, TextField cisternValue2) throws IOException{
        
        if(shiftName.getValue() == null){
            new ExceptionStage("Смена не выбрана!");
            return false;
        }
        
        if(shiftVariant.getValue() == null){
            new ExceptionStage("Тип смены не выбран!");
            return false;
        }
        
        if(todayDate.getValue() == null){
            new ExceptionStage("Дата не выбрана!");
            return false;
        }
        
        LocalDate localDate = todayDate.getValue();
        String shiftDay = String.valueOf(localDate);
        
        if(!today.equals(shiftDay)){
            new ExceptionStage("Выбранная дата не совпадает с текущей!");
            return false;
        }
        
        if(waterValue1.getText().replaceAll("[^\\d]", "").isEmpty() 
                || waterValue2.getText().replaceAll("[^\\d]", "").isEmpty()){
            new ExceptionStage("Наработка по кубометрам на начало и конец\nдолжны быть указаны!");
            return false;
        }
        
        Integer currentWaterValue1 = Integer.parseInt(waterValue1.getText().replaceAll("[^\\d]", ""));
        Integer currentWaterValue2 = Integer.parseInt(waterValue2.getText().replaceAll("[^\\d]", ""));
        
        if(currentWaterValue2 < currentWaterValue1){
            new ExceptionStage("Наработка по кубометрам\nне может быть отрицательной!");
            return false;
        }
        
        if(hoursValue1.getText().replaceAll("[^\\d]", "").isEmpty() 
                || hoursValue2.getText().replaceAll("[^\\d]", "").isEmpty()){
            new ExceptionStage("Наработка по часам на начало и конец\nдолжны быть указаны!");
            return false;
        }
        
        Integer currentHoursValue1 = Integer.parseInt(hoursValue1.getText().replaceAll("[^\\d]", ""));
        Integer currentHoursValue2 = Integer.parseInt(hoursValue2.getText().replaceAll("[^\\d]", ""));
        
        if(currentHoursValue2 < currentHoursValue1){
            new ExceptionStage("Наработка по часам\nне может быть отрицательной!");
            return false;
        }
        
        if(cisternValue1.getText().replaceAll("[^\\d]", "").isEmpty() 
                || cisternValue2.getText().replaceAll("[^\\d]", "").isEmpty()){
            new ExceptionStage("Уровень в баке на начало и конец\nдолжны быть указаны!");
            return false;
        }
        
        Integer currentCisternValue1 = Integer.parseInt(cisternValue1.getText().replaceAll("[^\\d]", ""));
        Integer currentCisternValue2 = Integer.parseInt(cisternValue2.getText().replaceAll("[^\\d]", ""));
        
        if(currentCisternValue1 < 0 || currentCisternValue2 < 0){
            new ExceptionStage("Уровень в баке\nне может быть отрицательным!");
            return false;
        }
        
        return true;
    }
    
    private int expenditure(int currentCisternValue2, int currentCisternValue1,
            int currentWaterValue2, int currentWaterValue1){
        
        int expenditure = 0;
        
        if(currentCisternValue2 > currentCisternValue1){
            expenditure = (currentWaterValue2 - currentWaterValue1) - (currentCisternValue2 - currentCisternValue1);
        }
        
        if(currentCisternValue2 < currentCisternValue1){
            expenditure =  (currentCisternValue1 - currentCisternValue2) + (currentWaterValue2 - currentWaterValue1);
        }
        
        if(currentCisternValue2 == currentCisternValue1){
            expenditure =  currentWaterValue2 - currentWaterValue1;
        }
        
        if(expenditure < 0 ){
            expenditure = 0;
        }
        
        return expenditure;
    }
}