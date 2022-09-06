/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stages;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class MaintenanceStage {
    public MaintenanceStage() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/views/Maintenance.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Панель технического обслуживания");
        stage.setScene(scene);
        stage.show();
    }
}
