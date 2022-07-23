/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stages;

import java.io.IOException;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author Valentin
 */
public class ExceptionStage {

    public ExceptionStage(String text) throws IOException{
        HBox hBox = new HBox(new Label(text));
        hBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(hBox);
        Stage stage = new Stage();
        stage.setWidth(300);
        stage.setHeight(150);
        stage.setScene(scene);
        stage.setTitle("Ошибка");
        stage.setScene(scene);
        stage.show();
    }
    
}
