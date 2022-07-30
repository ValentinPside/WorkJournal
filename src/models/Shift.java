/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Valentin
 */
public class Shift {
    
    private String shiftName;
    private ArrayList<LocalDate> date = new ArrayList<>();
    private ArrayList<Integer> water = new ArrayList<>();
    private ArrayList<Integer> hours = new ArrayList<>();
    private ArrayList<Integer> expenditure = new ArrayList<>();
    
    public Shift(String shiftName) throws IOException {
        setShiftName(shiftName);
    }

    public ArrayList<LocalDate> getDate() {
        return date;
    }

    public void setDate(ArrayList<LocalDate> date) {
        this.date = date;
    }

    public ArrayList<Integer> getWater() {
        return water;
    }

    public void setWater(ArrayList<Integer> water) {
        this.water = water;
    }

    public ArrayList<Integer> getHours() {
        return hours;
    }

    public void setHours(ArrayList<Integer> hours) {
        this.hours = hours;
    }

    public ArrayList<Integer> getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(ArrayList<Integer> expenditure) {
        this.expenditure = expenditure;
    }

    public String getShiftName() {
        return shiftName;
    }

    public void setShiftName(String shiftName){
        this.shiftName = shiftName;
    }
}
