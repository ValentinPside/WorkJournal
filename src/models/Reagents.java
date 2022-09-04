/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;

/**
 *
 * @author user
 */
public class Reagents {
    
    private String shiftName;
    private ArrayList<Integer> bisulfite;
    private ArrayList<Integer> sodium;

    public Reagents(String shiftName, ArrayList<Integer> bisulfite, ArrayList<Integer> sodium) {
        setShiftName(shiftName);
        setBisulfite(bisulfite);
        setSodium(sodium);
    }

    public String getShiftName() {
        return shiftName;
    }

    public void setShiftName(String shiftName) {
        this.shiftName = shiftName;
    }

    public ArrayList<Integer> getBisulfite() {
        return bisulfite;
    }

    public void setBisulfite(ArrayList<Integer> bisulfite) {
        this.bisulfite = bisulfite;
    }

    public ArrayList<Integer> getSodium() {
        return sodium;
    }

    public void setSodium(ArrayList<Integer> sodium) {
        this.sodium = sodium;
    }
    
}
