package com.brittanisavery.demos.models;

public class Tool {
    private Code code;
    private String type;
    private String brand;
    private double charge;
    private boolean busCharge;
    private boolean weekendCharge;
    private boolean holidayCharge;

    public Tool(String code) throws Exception {
        switch (Code.valueOf(code)) {
            case LADW:
                this.code = Code.LADW;
                this.type = "Ladder";
                this.brand = "Werner";
                this.charge = 1.99;
                this.busCharge = true;
                this.weekendCharge = true;
                this.holidayCharge = false;
                break;
            case CHNS:
                this.code = Code.CHNS;
                this.type = "Chainsaw";
                this.brand = "Stihl";
                this.charge = 1.49;
                this.busCharge = true;
                this.weekendCharge = false;
                this.holidayCharge = true;
                break;
            case JAKR:
            case JAKD:
                this.code = Code.valueOf(code);
                this.type = "Jackhammer";
                this.brand = this.code == Code.JAKR ? "Ridgid" : "DeWalt";
                this.charge = 2.99;
                this.busCharge = true;
                this.weekendCharge = false;
                this.holidayCharge = false;
                break;
            default:
                throw new IllegalArgumentException(
                        "Sorry, this tool could not be found. Double-check for typos and please try again.");
        }
    }

    public static enum Code {
        LADW, CHNS, JAKR, JAKD
    }

    public Code getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = Code.valueOf(code);
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