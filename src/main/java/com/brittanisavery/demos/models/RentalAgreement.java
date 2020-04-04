package com.brittanisavery.demos.models;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;

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

    protected final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("mm/dd/yy");
    protected final NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();

    public Tool getTool() {
        return this.tool;
    }

    public void setTool(final Tool tool) {
        this.tool = tool;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(final int length) {
        this.length = length;
    }

    public LocalDate getCheckoutDate() {
        return this.checkoutDate;
    }

    public void setCheckoutDate(final LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public LocalDate getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(final LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public int getChargeDays() {
        return this.chargeDays;
    }

    public void setChargeDays(final int chargeDays) {
        this.chargeDays = chargeDays;
    }

    public int getDiscountPercent() {
        return this.discountPercent;
    }

    public void setDiscountPercent(final int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public double getPreDiscountCharge() {
        return this.preDiscountCharge;
    }

    public void setPreDiscountCharge(final double preDiscountCharge) {
        this.preDiscountCharge = preDiscountCharge;
    }

    public double getDiscountAmount() {
        return this.discountAmount;
    }

    public void setDiscountAmount(final double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public double getFinalCharge() {
        return this.finalCharge;
    }

    public void setFinalCharge(final double finalCharge) {
        this.finalCharge = finalCharge;
    }

    @Override
    public String toString() {

        final StringJoiner result = new StringJoiner(System.lineSeparator());

        result.add(new StringBuilder("Tool code").append(tool.getCode()));
        result.add(new StringBuilder("Tool type: ").append(tool.getType()));
        result.add(new StringBuilder("Rental days: ").append(length));
        result.add(new StringBuilder("Check out date: ").append(checkoutDate.format(dateFormatter)));
        result.add(new StringBuilder("Due date: ").append(dueDate.format(dateFormatter)));
        result.add(new StringBuilder("Daily rental charge: ").append(currencyFormatter.format(tool.getCharge())));
        result.add(new StringBuilder("Charge Days: ").append(chargeDays));
        result.add(new StringBuilder("Pre-discount charge: ").append(currencyFormatter.format(preDiscountCharge)));
        result.add(new StringBuilder("Discount percent: ").append(discountPercent).append("%"));
        result.add(new StringBuilder("Discount amount: ").append(currencyFormatter.format(discountAmount)));
        result.add(new StringBuilder("Final charge: ").append(currencyFormatter.format(finalCharge)));

        return result.toString();
    }
}