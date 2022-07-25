/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.resurces;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Valentin
 */
public class Writer {

    public Writer(String shift, String record) throws IOException {
        File file = new File("C:/Program Files/Shifts/" + shift + ".txt");
        if(!file.exists()){
            file.createNewFile();
        }
        FileWriter fw = new FileWriter("C:/Program Files/Shifts/" + shift + ".txt", true);
        BufferedWriter bf = new BufferedWriter(fw);
        bf.write(record);
        bf.close();
        fw.close();
    }
    
}
