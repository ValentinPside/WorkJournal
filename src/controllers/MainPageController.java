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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import models.resurces.Reader;
import models.resurces.Writer;
import stages.ExceptionStage;

/**
 *
 * @author Valentin
 */
public class MainPageController implements Initializable {
    @FXML
    private ComboBox<String> shiftName;
    
    @FXML
    private DatePicker todayDate;
    
    @FXML
    private TextField waterValue;
    
    @FXML
    private TextField hoursValue;

    @FXML
    private Button saveBut;
    
    @FXML
    private void saveShiftDate(javafx.event.ActionEvent event) throws IOException{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String today = dateFormat.format(date);
        String shift = shiftName.getValue();
        if(checkShiftDate(shift, todayDate, today, waterValue, hoursValue)){
            LocalDate localDate = todayDate.getValue();
            String shiftDay = String.valueOf(localDate);
            Integer currentWaterValue = Integer.parseInt(waterValue.getText().replaceAll("[^\\d]", ""));
            Integer currentHoursValue = Integer.parseInt(hoursValue.getText().replaceAll("[^\\d]", ""));
            saveShiftValues(shift, shiftDay, currentWaterValue, currentHoursValue);
            new Reader("Г");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList("А", "Б", "В", "Г");
        shiftName.setItems(list);
    }
    
    private boolean saveShiftValues(String shift, String shiftDay, Integer currentWaterValue, Integer currentHoursValue) throws IOException{
        String record = shift + "/" + shiftDay + "/" + currentWaterValue + "/" + currentHoursValue + "\n";
        Writer writer = new Writer(shift, record);
        return true;
    }
    
    private boolean checkShiftDate(String shift, DatePicker todayDate, String today, TextField waterValue, TextField hoursValue) throws IOException{
        if(shift.isEmpty()){
            new ExceptionStage("Смена не выбрана!");
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
        
        if(waterValue.getText().replaceAll("[^\\d]", "").isEmpty()){
            new ExceptionStage("Наработка по кубометрам должна быть указана!");
            return false;
        }
        
        Integer currentWaterValue = Integer.parseInt(waterValue.getText().replaceAll("[^\\d]", ""));
        
        if(currentWaterValue < 0){
            new ExceptionStage("Наработка по кубометрам не может быть отрицательной!");
            return false;
        }
        
        if(hoursValue.getText().replaceAll("[^\\d]", "").isEmpty()){
            new ExceptionStage("Наработка по часам должна быть указана!");
            return false;
        }
        
        Integer currentHoursValue = Integer.parseInt(hoursValue.getText().replaceAll("[^\\d]", ""));
        
        if(currentHoursValue < 0){
            new ExceptionStage("Наработка по часам не может быть отрицательной!");
            return false;
        }
        return true;
    }
}
