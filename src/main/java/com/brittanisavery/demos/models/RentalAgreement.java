package com.brittanisavery.demos.models;

import java.time.LocalDate;

public class RentalAgreement {
    private Tool tool;
    private int length;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private int chargeDays;
    private int discountPercent;
    private double preDiscountCharge;
    private double discountAmount;
    private double finalCharge;

    public Tool getTool() {
        return this.tool;
    }

    public void setTool(Tool tool) {
        this.tool = tool;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public LocalDate getCheckoutDate() {
        return this.checkoutDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public LocalDate getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public int getChargeDays() {
        return this.chargeDays;
    }

    public void setChargeDays(int chargeDays) {
        this.chargeDays = chargeDays;
    }

    public int getDiscountPercent() {
        return this.discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public double getPreDiscountCharge() {
        return this.preDiscountCharge;
    }

    public void setPreDiscountCharge(double preDiscountCharge) {
        this.preDiscountCharge = preDiscountCharge;
    }

    public double getDiscountAmount() {
        return this.discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public double getFinalCharge() {
        return this.finalCharge;
    }

    public void setFinalCharge(double finalCharge) {
        this.finalCharge = finalCharge;
    }

    @Override
    public String toString() {
        return "{" + " tool='" + getTool() + "'" + ", length='" + getLength() + "'" + ", checkoutDate='"
                + getCheckoutDate() + "'" + ", dueDate='" + getDueDate() + "'" + ", chargeDays='" + getChargeDays()
                + "'" + ", discountPercent='" + getDiscountPercent() + "'" + ", preDiscountCharge='"
                + getPreDiscountCharge() + "'" + ", discountAmount='" + getDiscountAmount() + "'" + ", finalCharge='"
                + getFinalCharge() + "'" + "}";
    }

}