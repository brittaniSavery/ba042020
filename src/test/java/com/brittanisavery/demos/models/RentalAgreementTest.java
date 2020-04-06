package com.brittanisavery.demos.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

public class RentalAgreementTest {

    final private LocalDate APRIL_20TH = LocalDate.of(2020, Month.APRIL, 20);
    final private LocalDate APRIL_2ND = LocalDate.of(2020, Month.APRIL, 1);
    final private LocalDate JULY_1ST = LocalDate.of(2020, Month.JULY, 1);
    final private LocalDate JULY_4TH_FRIDAY = LocalDate.of(2020, Month.JULY, 3);
    final private LocalDate JULY_4TH_MONDAY = LocalDate.of(2021, Month.JULY, 5);
    final private LocalDate JULY_4TH_REGULAR = LocalDate.of(2018, Month.JULY, 4);
    final private LocalDate LABOR_DAY_EARLY = LocalDate.of(2024, Month.SEPTEMBER, 2);
    final private LocalDate LABOR_DAY_LATE = LocalDate.of(2020, Month.SEPTEMBER, 7);

    final private String LADDER = "LADW";
    final private String CHAINSAW = "CHNS";
    final private String JACKHAMMER_R = "JAKR";
    final private String JACKHAMMER_D = "JAKD";

    // #region Constructor Tests
    @Test
    public void Should_ReturnRentalAgreement_When_AllValuesValidWithLocalDate() throws Exception {
        RentalAgreement agreement = new RentalAgreement(CHAINSAW, APRIL_20TH, 5, 20);
        assertEquals(agreement.getTool().getType(), "Chainsaw");
        assertTrue(agreement.getCheckoutDate() instanceof LocalDate);
        assertEquals(agreement.getRentalDays(), 5);
        assertEquals(agreement.getDiscountPercent(), 20);
    }

    @Test
    public void Should_ReturnRentalAgreement_When_AllValuesValidWithStringDate() throws Exception {
        RentalAgreement agreement = new RentalAgreement(CHAINSAW, "04/20/20", 5, 20);
        assertEquals(agreement.getTool().getType(), "Chainsaw");
        assertTrue(agreement.getCheckoutDate() instanceof LocalDate);
        assertEquals(agreement.getRentalDays(), 5);
        assertEquals(agreement.getDiscountPercent(), 20);
    }

    // #endregion

    // #region Calculate ChargeDays Tests
    @Test
    public void Should_Return5ChargeDays_When_ToolIsLadderAndRentalDaysIs5AndIncludesWeekends() throws Exception {
        RentalAgreement agreement = new RentalAgreement(LADDER, APRIL_2ND, 5, 20);
        agreement.calculateChargeDays();
        assertEquals(agreement.getChargeDays(), 5);
    }

    @Test
    public void Should_Return3ChargeDays_When_ToolIsChainsawAndRentalDaysIs5AndIncludesWeends() throws Exception {
        RentalAgreement agreement = new RentalAgreement(CHAINSAW, APRIL_2ND, 5, 20);
        agreement.calculateChargeDays();
        assertEquals(agreement.getChargeDays(), 3);
    }

    @Test
    public void Should_Return3ChargeDays_When_ToolIsJackhammerAndRentalDaysIs5AndIncludesWeends() throws Exception {
        RentalAgreement agreement = new RentalAgreement(JACKHAMMER_R, APRIL_2ND, 5, 20);
        agreement.calculateChargeDays();
        assertEquals(agreement.getChargeDays(), 3);
    }

    @Test
    public void Should_Return3ChargeDays_When_ToolIsLadderAndRentalDaysIs4AndHasHolidaysAndWeekends() throws Exception {
        RentalAgreement agreement = new RentalAgreement(LADDER, JULY_1ST, 4, 20);
        agreement.calculateChargeDays();
        assertEquals(agreement.getChargeDays(), 3);
    }

    @Test
    public void Should_Return3ChargeDays_When_ToolIsChainsawAndRentalDaysIs4AndHasHolidaysAndWeekends()
            throws Exception {
        RentalAgreement agreement = new RentalAgreement(CHAINSAW, JULY_1ST, 4, 20);
        agreement.calculateChargeDays();
        assertEquals(agreement.getChargeDays(), 2);
    }

    @Test
    public void Should_Return3ChargeDays_When_ToolIsJackhammerAndRentalDaysIs4AndHasHolidaysAndWeekends()
            throws Exception {
        RentalAgreement agreement = new RentalAgreement(JACKHAMMER_R, JULY_1ST, 4, 20);
        agreement.calculateChargeDays();
        assertEquals(agreement.getChargeDays(), 1);
    }
    // #endregion

    // #region Calculate DiscountAmount Tests
    @Test
    public void Should_Return2Dollars99Cents_When_ToolIsLadderAndChargeDaysIs6AndDiscountIs25() throws Exception {
        RentalAgreement agreement = new RentalAgreement(LADDER, APRIL_20TH, 6, 25);
        agreement.setChargeDays(6);
        agreement.calculateDiscountAmount();
        assertEquals(agreement.getDiscountAmount(), new BigDecimal("2.99"));
    }

    @Test
    public void Should_Return2Dollars24Cents_When_ToolIsChainsawAndChargeDaysIs6AndDiscountIs25() throws Exception {
        RentalAgreement agreement = new RentalAgreement(CHAINSAW, APRIL_20TH, 6, 25);
        agreement.setChargeDays(6);
        agreement.calculateDiscountAmount();
        assertEquals(agreement.getDiscountAmount(), new BigDecimal("2.24"));
    }

    @Test
    public void Should_Return4Dollars49Cents_When_ToolIsJackhammerAndChargeDaysIs6AndDiscountIs25() throws Exception {
        RentalAgreement agreement = new RentalAgreement(JACKHAMMER_D, APRIL_20TH, 6, 25);
        agreement.setChargeDays(6);
        agreement.calculateDiscountAmount();
        assertEquals(agreement.getDiscountAmount(), new BigDecimal("4.49"));
    }
    // #endregion

    // #region IsHoilday Tests
    @Test
    public void Should_ReturnDateIsHoliday_When_DateIsFridayJuly4th() {
        RentalAgreement agreement = new RentalAgreement();
        assertTrue(agreement.isHoliday(JULY_4TH_FRIDAY));
    }

    @Test
    public void Should_ReturnDateIsHoliday_When_DateIsMondayJuly4th() {
        RentalAgreement agreement = new RentalAgreement();
        assertTrue(agreement.isHoliday(JULY_4TH_MONDAY));
    }

    @Test
    public void Should_ReturnDateIsHoliday_When_DateIsRegularJuly4th() {
        RentalAgreement agreement = new RentalAgreement();
        assertTrue(agreement.isHoliday(JULY_4TH_REGULAR));
    }

    @Test
    public void Should_ReturnDateIsHoliday_When_DateIsEarlyLaborDay() {
        RentalAgreement agreement = new RentalAgreement();
        assertTrue(agreement.isHoliday(LABOR_DAY_EARLY));
    }

    @Test
    public void Should_ReturnDateIsHoliday_When_DateIsLateLaborDay() {
        RentalAgreement agreement = new RentalAgreement();
        assertTrue(agreement.isHoliday(LABOR_DAY_LATE));
    }

    @Test
    public void Should_ReturnDateIsNotHoliday_When_DateIsRegularDay() {
        RentalAgreement agreement = new RentalAgreement();
        assertFalse(agreement.isHoliday(APRIL_20TH));
    }
    // #endregion

    // #region Checkout Tests
    @Test
    public void Should_ThrowException_When_RentalDaysIsNonPositiveNumber() {
        assertThrows(IllegalArgumentException.class, () -> {
            RentalAgreement agreement = new RentalAgreement(CHAINSAW, APRIL_20TH, 0, 20);
            agreement.checkout();
        });
    }

    // #region Final Tests from Spec
    @Test
    public void Should_ThrowException_When_DiscountAmountIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            RentalAgreement agreement = new RentalAgreement("JAKR", "09/03/15", 5, 101);
            agreement.checkout();
        });
    }

    // #endregion

    // #endregion

}