package com.brittanisavery.demos.models;

import java.text.NumberFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;
import java.util.stream.Stream;

public class RentalAgreement {
    private Tool tool;
    private int rentalDays;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private int chargeDays;
    private int discountPercent;
    private double preDiscountCharge;
    private double discountAmount;
    private double finalCharge;

    protected final static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yy");
    protected final static NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();

    // #region Constructors

    public RentalAgreement() {

    }

    public RentalAgreement(String toolCode, LocalDate checkoutDate, int rentalDays, int discountPercent)
            throws Exception {
        this.tool = new Tool(toolCode);
        this.checkoutDate = checkoutDate;
        this.rentalDays = rentalDays;
        this.discountPercent = discountPercent;
    }

    public RentalAgreement(String toolCode, String checkoutDate, int rentalDays, int discountPercent) throws Exception {
        this(toolCode, LocalDate.parse(checkoutDate, dateFormatter), rentalDays, discountPercent);
    }

    // #endregion

    // #region Logic Methods

    public void checkout() {

        if (this.rentalDays < 1)
            throw new IllegalArgumentException(
                    "A tool should be rented out for one or more days. Please enter the number of days you will need the tool.");

        if (this.discountPercent < 0 || this.discountPercent > 100)
            throw new IllegalArgumentException(
                    "A valid discount amount is 0-100%. Please double-check for typos and try again.");

        calculateChargeDays();
    }

    protected void calculateChargeDays() {
        this.dueDate = this.checkoutDate.plusDays(this.rentalDays);
        LocalDate chargeStart = this.checkoutDate.plusDays(1);
        this.chargeDays = Math.toIntExact(Stream.iterate(chargeStart, day -> day.plusDays(1))
                .limit(this.rentalDays)
                .filter(day -> !isWeekend(day) || this.tool.hasWeekendCharge())
                .filter(day -> !isHoliday(day) || this.tool.hasHolidayCharge())
                .count());

    }

    protected boolean isHoliday(LocalDate date) {
        return isJuly4th(date) || isLaborDay(date);
    }

    private boolean isWeekend(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
    }

    private boolean isJuly4th(LocalDate date) {
        LocalDate holiday = date.withMonth(Month.JULY.getValue()).withDayOfMonth(4);

        switch (holiday.getDayOfWeek()) {
            case SATURDAY:
                holiday = holiday.minusDays(1);
                break;
            case SUNDAY:
                holiday = holiday.plusDays(1);
                break;
            default:
        }

        return date.equals(holiday);
    }

    private boolean isLaborDay(LocalDate date) {
        LocalDate sept1st = date.withMonth(Month.SEPTEMBER.getValue()).withDayOfMonth(1);

        LocalDate holiday = Stream.iterate(sept1st, day -> day.plusDays(1))
                .limit(7)
                .filter(day -> day.getDayOfWeek() == DayOfWeek.MONDAY)
                .findFirst()
                .get();

        return date.equals(holiday);
    }

    // #endregion

    // #region Getters and Setters

    public Tool getTool() {
        return this.tool;
    }

    public void setTool(final Tool tool) {
        this.tool = tool;
    }

    public int getRentalDays() {
        return this.rentalDays;
    }

    public void setRentalDays(final int rentalDays) {
        this.rentalDays = rentalDays;
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

    // #endregion

    @Override
    public String toString() {

        final StringJoiner result = new StringJoiner(System.lineSeparator());

        result.add(new StringBuilder("Tool code").append(tool.getCode()));
        result.add(new StringBuilder("Tool type: ").append(tool.getType()));
        result.add(new StringBuilder("Rental days: ").append(rentalDays));
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
