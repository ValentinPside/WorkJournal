/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.resurces;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Valentin
 */
public class Reader {

    public Reader(String shift) throws IOException {
        File file = new File("C:/Program Files/Shifts/" + shift + ".txt");
        if(!file.exists()){
            file.createNewFile();
        }
        FileReader fw = new FileReader("C:/Program Files/Shifts/" + shift + ".txt");
        BufferedReader br = new BufferedReader(fw);
        String line = br.readLine();
        while(line != null){
            System.out.println(line);
            line = br.readLine();
        }
        br.close();
        fw.close();
    } 
}
