/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import stages.ChemistryReportStage;
import stages.ExceptionStage;
import stages.SuccessStage;

/**
 *
 * @author user
 */
public class MaintenanceController implements Initializable{
    @FXML
    private ComboBox<String> shift;
    
    @FXML
    private ComboBox<String> type;
    
    @FXML
    private ComboBox<String> stage;
    
    @FXML
    private DatePicker date;
    
    @FXML
    private TextField acid;
    
    @FXML
    private TextField sodium;
    
    @FXML
    private TextField filterb;
    
    @FXML
    private TextField filters;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list1 = FXCollections.observableArrayList("А", "Б", "В", "Г");
        shift.setItems(list1);
        
        ObservableList<String> list2 = FXCollections.observableArrayList("Дневная", "Ночная");
        type.setItems(list2);
        
        ObservableList<String> list3 = FXCollections.observableArrayList("Первая", "Вторая");
        stage.setItems(list3);
    }
    
    @FXML
    private void createReport(javafx.event.ActionEvent event) throws Exception{
        double acidReserve = getAcid();
        double sodiumReserve = getSodium();
        int filterbReserve = getFilterB();
        int filtersReserve = getFilterS();
        String text = "Запасы лимонной кислоты составляют " + acidReserve + "кг\n" +
                "Запасы едкого натра составляют " + sodiumReserve + "кг\n" +
                "Запасы фильтров ЭФГ 63/508 составляют " + filterbReserve + "шт\n" +
                "Запасы фильтров ЭФГ 63/250 составляют " + filtersReserve + "шт";
        new ChemistryReportStage(text);
    }
    
    @FXML
    private void save(javafx.event.ActionEvent event) throws Exception{
        if(check(shift, type, stage, date, acid, sodium, filterb, filters)){
            String shiftName = shift.getValue();
            String shiftType = type.getValue();
            String stageNumber = stage.getValue();
            LocalDate localDate = date.getValue();
            String preAcid = acid.getText().replaceAll(",", ".");
            Double acidValue = Double.parseDouble(preAcid.replaceAll("[^.0-9]", ""));
            Double sodiumValue = Double.parseDouble(sodium.getText().replaceAll("[^\\d]", ""));
            Integer filterBValue = Integer.parseInt(filterb.getText().replaceAll("[^\\d]", ""));
            Integer filterSValue = Integer.parseInt(filters.getText().replaceAll("[^\\d]", ""));
            double acidReserve = getAcid();
            double bisulfiteReserve = getBisulfite();
            double sodiumReserve = getSodium();
            int filterbReserve = getFilterB();
            int filtersReserve = getFilterS();
            writer(shiftName, shiftType, stageNumber, localDate,
            acidValue, sodiumValue, filterBValue,filterSValue);
            updateMaintenance(acidReserve, bisulfiteReserve, sodiumReserve, filterbReserve, 
            filtersReserve, acidValue, sodiumValue, filterBValue,filterSValue);
            new SuccessStage("Запись успешно сохранена!");
        }
    }
    
    private double getAcid() throws IOException {
        File file = new File("C:/Program Files/Shifts/Maintenance.txt");
        if(!file.exists()){
            new ExceptionStage("Файл хранения текущих запасов\n расходников не найден");
        }
        FileReader fr = new FileReader("C:/Program Files/Shifts/Maintenance.txt");
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        line = br.readLine();
        String[] values = line.split("/");
        String val = values[1].replaceAll(",", ".");
        double acid = Double.parseDouble(val);
        br.close();
        fr.close();
        return acid;
    }
    
    private double getBisulfite() throws IOException {
        File file = new File("C:/Program Files/Shifts/Chemistry.txt");
        if(!file.exists()){
            new ExceptionStage("Файл хранения текущих запасов\n химических реагентов не найден");
        }
        FileReader fr = new FileReader("C:/Program Files/Shifts/Chemistry.txt");
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        line = br.readLine();
        String[] values = line.split("/");
        String val = values[1].replaceAll(",", ".");
        double bisulfite = Double.parseDouble(val);
        br.close();
        fr.close();
        return bisulfite;
    }
    
    private double getSodium() throws IOException {
        File file = new File("C:/Program Files/Shifts/Chemistry.txt");
        if(!file.exists()){
            new ExceptionStage("Файл хранения текущих запасов\n расходников не найден");
        }
        FileReader fr = new FileReader("C:/Program Files/Shifts/Chemistry.txt");
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        line = br.readLine();
        line = br.readLine();
        String[] values = line.split("/");
        String val = values[1].replaceAll(",", ".");
        double sodium = Double.parseDouble(val);
        line = br.readLine();
        br.close();
        fr.close();
        return sodium;
    }
    
    private int getFilterB() throws IOException {
        File file = new File("C:/Program Files/Shifts/Maintenance.txt");
        if(!file.exists()){
            new ExceptionStage("Файл хранения текущих запасов\n расходников не найден");
        }
        FileReader fr = new FileReader("C:/Program Files/Shifts/Maintenance.txt");
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        line = br.readLine();
        line = br.readLine();
        String[] values = line.split("/");
        int filterb = Integer.parseInt(values[1]);
        line = br.readLine();
        br.close();
        fr.close();
        return filterb;
    }
    
    private int getFilterS() throws IOException {
        File file = new File("C:/Program Files/Shifts/Maintenance.txt");
        if(!file.exists()){
            new ExceptionStage("Файл хранения текущих запасов\n расходников не найден");
        }
        FileReader fr = new FileReader("C:/Program Files/Shifts/Maintenance.txt");
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        line = br.readLine();
        line = br.readLine();
        line = br.readLine();
        String[] values = line.split("/");
        int filters = Integer.parseInt(values[1]);
        line = br.readLine();
        br.close();
        fr.close();
        return filters;
    }
    
    private void writer(String shiftName, String shiftType, String stageNumber, LocalDate localDate,
            Double acidValue, Double sodiumValue, Integer filterBValue,
            Integer filterSValue) throws IOException {
        String variant = "";
        if(shiftType.equals("Дневная")){
           variant = "d";
        } else{
            variant = "n";
        }
        String stage = "";
        if(stageNumber.equals("Первая")){
           stage = "f";
        } else{
            stage = "s";
        }
        String c1 = String.format("%.2f", acidValue);
        String c2 = String.format("%.2f", sodiumValue);
        FileWriter fw = new FileWriter("C:/Program Files/Shifts/" + shiftName + "Т.txt", true);
        BufferedWriter bf = new BufferedWriter(fw);
        String record = variant + "/" + localDate + "/" + stage + "/" + c1 + 
                 "/" + c2 + "/" + filterBValue + 
                         "/" + filterSValue +"\n";
        bf.write(record);
        bf.close();
        fw.close();
    }
    
    private void updateMaintenance(double acidReserve, double bisulfiteReserve, double sodiumReserve, 
            int filterbReserve, int filtersReserve, Double acidValue, Double sodiumValue, Integer filterBValue,
            Integer filterSValue) throws IOException{
        sodiumReserve = sodiumReserve - sodiumValue/1000;
        String c1 = String.format("%.2f", bisulfiteReserve);
        String c2 = String.format("%.2f", sodiumReserve);
        FileWriter fw1 = new FileWriter("C:/Program Files/Shifts/Chemistry.txt", false);
        String record = "1/" + c1 + "\n" + "2/" + c2 + "\n";
        fw1.write(record);
        fw1.close();
        FileWriter fw2 = new FileWriter("C:/Program Files/Shifts/Maintenance.txt", false);
        acidReserve = acidReserve - acidValue;
        String c3 = String.format("%.2f", acidReserve);
        filterbReserve = filterbReserve - filterBValue;
        filtersReserve = filtersReserve - filterSValue;
        record = "1/" + c3 + "\n" + "2/" + filterbReserve + "\n" + "3/" + filtersReserve + "\n";
        fw2.write(record);
        fw2.close();
    }
    
    private boolean check(ComboBox<String> shift, ComboBox<String> type, ComboBox<String> stage, DatePicker date,
            TextField acid, TextField sodium, TextField filterb, TextField filters) throws IOException{
        if(shift.getValue() == null){
            new ExceptionStage("Смена не выбрана!");
            return false;
        }
        
        if(type.getValue() == null){
            new ExceptionStage("Тип смены не выбран!");
            return false;
        }
        
        if(stage.getValue() == null){
            new ExceptionStage("Ступень не выбрана!");
            return false;
        }
        
        if(date.getValue() == null){
            new ExceptionStage("Дата не выбрана!");
            return false;
        }
        
        if(((acid.getText().replaceAll("[^\\d]", ""))).isEmpty()){
            new ExceptionStage("Количество лимонной кислоты не указано!");
            return false;
        }
        
        if(((sodium.getText().replaceAll("[^\\d]", ""))).isEmpty()){
            new ExceptionStage("Количество едкого натра не указано!");
            return false;
        }
        
        if(((filterb.getText().replaceAll("[^\\d]", ""))).isEmpty()){
            new ExceptionStage("Количество фильтров не указано!");
            return false;
        }
        
        if(((filters.getText().replaceAll("[^\\d]", ""))).isEmpty()){
            new ExceptionStage("Количество фильтров не указано!");
            return false;
        }
        return true;
    }
}
