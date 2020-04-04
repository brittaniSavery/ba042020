package com.brittanisavery.demos.models;

public class Tool {
    private String code;
    private String type;
    private String brand;
    private double charge;
    private boolean busCharge;
    private boolean weekendCharge;
    private boolean holidayCharge;

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getCharge() {
        return this.charge;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }

    public boolean hasBusCharge() {
        return this.busCharge;
    }

    public void setBusCharge(boolean busCharge) {
        this.busCharge = busCharge;
    }

    public boolean hasWeekendCharge() {
        return this.weekendCharge;
    }

    public void setWeekendCharge(boolean weekendCharge) {
        this.weekendCharge = weekendCharge;
    }

    public boolean hasHolidayCharge() {
        return this.holidayCharge;
    }

    public void setHolidayCharge(boolean holidayCharge) {
        this.holidayCharge = holidayCharge;
    }
}