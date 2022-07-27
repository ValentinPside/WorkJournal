/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import models.Shift;
import models.resurces.Reader;
import stages.ExceptionStage;
import stages.SuccessStage;

/**
 * FXML Controller class
 *
 * @author Valentin
 */
public class ReportController implements Initializable {

    @FXML
    private ComboBox<String> shiftName;
    
    @FXML
    private DatePicker date1;
    
    @FXML
    private DatePicker date2;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list1 = FXCollections.observableArrayList("Все смены", "А", "Б", "В", "Г");
        shiftName.setItems(list1);
    }    
    
    @FXML
    private void createReport(javafx.event.ActionEvent event) throws IOException{
        if(checkShiftDate(shiftName, date1, date2)){
            String currentShiftName = shiftName.getValue();
            LocalDate localDate1 = date1.getValue();
            String reportDay1 = String.valueOf(localDate1);
            LocalDate localDate2 = date2.getValue();
            String reportDay2 = String.valueOf(localDate2);
            if(currentShiftName.equals("Все смены")){
                Shift a = new Shift("А");
                new Reader(a);
                Shift b = new Shift("Б");
                new Reader(b);
                Shift c = new Shift("В");
                new Reader(c);
                Shift d = new Shift("Г");
                new Reader(d);
                Shift[] shifts = new Shift[]{a, b ,c ,d};
                reportWriter(shifts, reportDay1, reportDay2);
                new SuccessStage("Отчёт успешно составлен!");
            }
            else{
                Shift shift = new Shift(currentShiftName);
                new Reader(shift);
                shiftReportWriter(shift, reportDay1, reportDay2);
                new SuccessStage("Отчёт успешно составлен!");
            }
        }
    }
    
    private boolean checkShiftDate(ComboBox<String> shiftName, DatePicker date1, DatePicker date2) throws IOException{
        
        if(shiftName.getValue() == null){
            new ExceptionStage("Смена не выбрана!");
            return false;
        }
        
        if(date1.getValue() == null || date2.getValue() == null){
            new ExceptionStage("Даты не выбраны!");
            return false;
        }
        
        if(date2.getValue().isAfter(date1.getValue())){
            new ExceptionStage("Даты выбраны неверно!");
            return false;
        }
        
        return true;
    }
    
    private boolean reportWriter(Shift[] shifts, String reportDay1, String reportDay2) throws IOException {
        File file = new File("C:/Program Files/Shifts/" + "Отчёт ВВЧ с " + reportDay1 + "по " + reportDay2 + ".txt");
        if(!file.exists()){
            file.createNewFile();
        }
        FileWriter fw = new FileWriter("C:/Program Files/Shifts/" + "Отчёт ВВЧ с " + reportDay1 + "по " + reportDay2 + ".txt", true);
        BufferedWriter bf = new BufferedWriter(fw);
        int waterValue = 0;
        int hoursValue = 0;
        int expenditureValue = 0;
        for(Shift i : shifts){
            ArrayList<String> date = i.getDate();
            int index1 = date.indexOf(reportDay1);
            int index2 = date.indexOf(reportDay2);
            if(!date.contains(reportDay1) || !date.contains(reportDay2)){
            new ExceptionStage("Выбран несуществующий диапазон дат!");
            return false;
        }
            ArrayList<Integer> water = i.getWater();
            ArrayList<Integer> hours = i.getHours();
            ArrayList<Integer> expenditure = i.getExpenditure();
            for(int a = index1; a <= index2; a++){
                waterValue = waterValue + water.get(a);
                hoursValue = hoursValue + hours.get(a);
                expenditureValue = expenditureValue + expenditure.get(a);
            }
        }
        bf.write("Отчёт работы ВВЧ с " + reportDay1 + "по " + reportDay2 + "\n" +
                "Наработка по кубометрам составила " + waterValue + " метров кубических;\n" + 
                "Наработка по часам составила " + hoursValue + " часов;\n" +
                "Выдача составила порядка " + expenditureValue + " метров кубических;\n");
        bf.close();
        fw.close();
        return true;
    }
    
    private boolean shiftReportWriter(Shift shift, String reportDay1, String reportDay2) throws IOException {
        File file = new File("C:/Program Files/Shifts/" + "Отчёт ВВЧ с " + reportDay1 + "по " + reportDay2 + ".txt");
        if(!file.exists()){
            file.createNewFile();
        }
        FileWriter fw = new FileWriter("C:/Program Files/Shifts/" + "Отчёт ВВЧ с " + reportDay1 + "по " + reportDay2 + ".txt", true);
        BufferedWriter bf = new BufferedWriter(fw);
        int waterValue = 0;
        int hoursValue = 0;
        int expenditureValue = 0;
        ArrayList<String> date = shift.getDate();
        if(!date.contains(reportDay1) || !date.contains(reportDay2)){
            new ExceptionStage("Выбран несуществующий диапазон дат!");
            return false;
        }
        int index1 = date.indexOf(reportDay1);
        int index2 = date.indexOf(reportDay2);
        ArrayList<Integer> water = shift.getWater();
        ArrayList<Integer> hours = shift.getHours();
        ArrayList<Integer> expenditure = shift.getExpenditure();
        for(int a = index1; a <= index2; a++){
            waterValue = waterValue + water.get(a);
            hoursValue = hoursValue + hours.get(a);
            expenditureValue = expenditureValue + expenditure.get(a);
        }
        
        bf.write("Отчёт работы ВВЧ с " + reportDay1 + "по " + reportDay2 + "\n" +
                "Наработка по кубометрам составила " + waterValue + " метров кубических;\n" + 
                "Наработка по часам составила " + hoursValue + " часов;\n" +
                "Выдача составила порядка " + expenditureValue + " метров кубических;\n");
        bf.close();
        fw.close();
        return true;
    }
}
