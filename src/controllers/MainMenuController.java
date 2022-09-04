/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import stages.ChartsStage;
import stages.ChemistryStage;
import stages.MainPageStage;
import stages.ReportStage;

/**
 * FXML Controller class
 *
 * @author Valentin
 */
public class MainMenuController implements Initializable {

    @FXML
    private void openChartsPanel(javafx.event.ActionEvent event) throws Exception{
        new ChartsStage();
    }
    
    @FXML
    private void openShiftRecord(javafx.event.ActionEvent event) throws Exception{
        new MainPageStage();
    }
    
    @FXML
    private void openChemisrtyRecord(javafx.event.ActionEvent event) throws Exception{
        new ChemistryStage();
    }
    
    @FXML
    private void openMaintenanceRecord(javafx.event.ActionEvent event) throws Exception{
        
    }
    
    @FXML
    private void openReportPanel(javafx.event.ActionEvent event) throws Exception{
        new ReportStage();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
