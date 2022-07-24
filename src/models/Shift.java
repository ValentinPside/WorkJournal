/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.HashMap;

/**
 *
 * @author Valentin
 */
public class Shift {
    private String shiftName;
    private HashMap records;
    

    public Shift(String shiftName) {
        setShiftName(shiftName);
    }

    public String getShiftName() {
        return shiftName;
    }

    public void setShiftName(String shiftName){
        this.shiftName = shiftName;
    }
}
