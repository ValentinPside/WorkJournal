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
 * FXML Controller class
 *
 * @author user
 */
public class ChemisrtyController implements Initializable {
    
    @FXML
    private ComboBox<String> shift;
    
    @FXML
    private ComboBox<String> shiftType;
    
    @FXML
    private ComboBox<String> chemistry;
    
    @FXML
    private DatePicker date;
    
    @FXML
    private TextField value;

    @FXML
    private void createReport(javafx.event.ActionEvent event) throws Exception{
        int bisulfite = getBisulfite();
        int sodium = getSodium();
        String text = "Запасы бисульфита составляют " + bisulfite + "кг\n" +
                "Запасы едкого натра составляют " + sodium + "кг";
        new ChemistryReportStage(text);
    }
    
    @FXML
    private void save(javafx.event.ActionEvent event) throws Exception{
        if(check(shift, shiftType, chemistry, date, value)){
            String shiftName = shift.getValue();
            String shiftVariant = shiftType.getValue();
            String chemistryType = chemistry.getValue();
            LocalDate localDate = date.getValue();
            Integer chemistryValue = Integer.parseInt(value.getText().replaceAll("[^\\d]", ""));
            int bisulfite = getBisulfite();
            int sodium = getSodium();
            writer(shiftName, shiftVariant, localDate,
            chemistryType, chemistryValue);
            updateChemistry(bisulfite, sodium, chemistryType, chemistryValue);
            new SuccessStage("Запись успешно сохранена!");
        }
    }
    
    private int getBisulfite() throws IOException {
        File file = new File("C:/Program Files/Shifts/Chemistry.txt");
        if(!file.exists()){
            new ExceptionStage("Файл хранения текущих запасов\n химических реагентов не найден");
        }
        FileReader fr = new FileReader("C:/Program Files/Shifts/Chemistry.txt");
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        line = br.readLine();
        String[] values = line.split("/");
        int bisulfite = Integer.parseInt(values[1]);
        br.close();
        fr.close();
        return bisulfite;
    }
    private int getSodium() throws IOException {
        File file = new File("C:/Program Files/Shifts/Chemistry.txt");
        if(!file.exists()){
            new ExceptionStage("Файл хранения текущих запасов\n химических реагентов не найден");
        }
        FileReader fr = new FileReader("C:/Program Files/Shifts/Chemistry.txt");
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        line = br.readLine();
        line = br.readLine();
        String[] values = line.split("/");
        int sodium = Integer.parseInt(values[1]);
        line = br.readLine();
        br.close();
        fr.close();
        return sodium;
    }
    
    private void writer(String shiftName, String shiftVariant, LocalDate localDate,
            String chemistryType, Integer chemistryValue) throws IOException {
        String variant = "";
        if(shiftVariant.equals("Дневная смена")){
           variant = "d";
        } else{
            variant = "n";
        }
        String type = "";
        if(chemistryType.equals("Бисульфит")){
           type = "1";
        } else{
            type = "2";
        }
        FileWriter fw = new FileWriter("C:/Program Files/Shifts/" + shiftName + "Х.txt", true);
        BufferedWriter bf = new BufferedWriter(fw);
        String record = variant + "/" + localDate + "/" + type + "/" + chemistryValue + "\n";
        bf.write(record);
        bf.close();
        fw.close();
    }
    
    private void updateChemistry(int bisulfite, int sodium, String chemistryType, 
            Integer chemistryValue) throws IOException{
        if(chemistryType.equals("Бисульфит")){
           bisulfite = bisulfite - chemistryValue;
        } else{
            sodium = sodium - chemistryValue;
        }
        FileWriter fw = new FileWriter("C:/Program Files/Shifts/Chemistry.txt", false);
        String record = "1/" + bisulfite + "\n" + "2/" + sodium + "\n";
        fw.write(record);
        fw.close();
    }
    
    private boolean check(ComboBox<String> shift, ComboBox<String> shiftType,
            ComboBox<String> chemistry, DatePicker date, TextField value) throws IOException{
        if(shift.getValue() == null){
            new ExceptionStage("Смена не выбрана!");
            return false;
        }
        
        if(shiftType.getValue() == null){
            new ExceptionStage("Тип смены не выбрана!");
            return false;
        }
        
        if(chemistry.getValue() == null){
            new ExceptionStage("Реагент не выбран!");
            return false;
        }
        
        if(date.getValue() == null){
            new ExceptionStage("Дата не указана!");
            return false;
        }
        
        if(((value.getText().replaceAll("[^\\d]", ""))).isEmpty()){
            new ExceptionStage("Количество реагента не указано!");
            return false;
        }
        
        return true;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list1 = FXCollections.observableArrayList("А", "Б", "В", "Г");
        shift.setItems(list1);
        
        ObservableList<String> list2 = FXCollections.observableArrayList("Дневная смена", "Ночная смена");
        shiftType.setItems(list2);
        
        ObservableList<String> list3 = FXCollections.observableArrayList("Бисульфит", "Едкий натр");
        chemistry.setItems(list3);
    }    
    
}
