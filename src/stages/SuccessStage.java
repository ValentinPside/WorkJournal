/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stages;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author Valentin
 */
public class SuccessStage {

    public SuccessStage(String text) {
        HBox hBox = new HBox(new Label(text));
        hBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(hBox);
        Stage stage = new Stage();
        stage.setWidth(300);
        stage.setHeight(150);
        stage.setScene(scene);
        stage.setTitle("ВВЧ демо");
        stage.setScene(scene);
        stage.show();
    }
    
}
