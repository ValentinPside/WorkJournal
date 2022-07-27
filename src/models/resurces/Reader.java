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
import models.Shift;

/**
 *
 * @author Valentin
 */
public class Reader {

    public Reader(Shift shift) throws IOException {
        File file = new File("C:/Program Files/Shifts/" + shift.getShiftName() + ".txt");
        if(!file.exists()){
            file.createNewFile();
        }
        FileReader fw = new FileReader("C:/Program Files/Shifts/" + shift.getShiftName() + ".txt");
        BufferedReader br = new BufferedReader(fw);
        
        String line = br.readLine();
        while(line != null){
            if(!line.contains("Дата")){
                String[] values = line.split("/");
                shift.getDate().add(values[0]);
                shift.getWater().add(Integer.parseInt(values[1]));
                shift.getHours().add(Integer.parseInt(values[2]));
                shift.getExpenditure().add(Integer.parseInt(values[3]));
            }
            line = br.readLine();
        }
        br.close();
        fw.close();
    } 
}
