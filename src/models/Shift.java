/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;

/**
 *
 * @author Valentin
 */
public class Shift {
    private String shiftName;
    private Date shiftDate;
    private int waterValue;
    private int hoursValue;

    public Shift(String shiftName, Date shiftDate, int waterValue, int hoursValue) {
        setShiftName(shiftName);
        setShiftDate(shiftDate);
        setWaterValue(waterValue);
        setHoursValue(hoursValue);
    }

    public String getShiftName() {
        return shiftName;
    }

    public void setShiftName(String shiftName){
        this.shiftName = shiftName;
    }

    public Date getShiftDate() {
        return shiftDate;
    }

    public void setShiftDate(Date shiftDate) {
        this.shiftDate = shiftDate;
    }

    public int getWaterValue() {
        return waterValue;
    }

    public void setWaterValue(int waterValue) {
        this.waterValue = waterValue;
    }

    public int getHoursValue() {
        return hoursValue;
    }

    public void setHoursValue(int hoursValue) {
        this.hoursValue = hoursValue;
    }
}
