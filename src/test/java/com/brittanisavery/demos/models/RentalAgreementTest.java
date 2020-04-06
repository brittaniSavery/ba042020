package com.brittanisavery.demos.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.Month;

public class RentalAgreementTest {

    private LocalDate july4thFriday = LocalDate.of(2020, Month.JULY, 3);
    private LocalDate july4thMonday = LocalDate.of(2021, Month.JULY, 5);
    private LocalDate july4thRegular = LocalDate.of(2018, Month.JULY, 4);
    private LocalDate regularDate = LocalDate.of(2020, Month.APRIL, 1);
    private LocalDate laborDayEarly = LocalDate.of(2024, Month.SEPTEMBER, 2);
    private LocalDate laborDayLate = LocalDate.of(2020, Month.SEPTEMBER, 7);

    // #region Constructor Tests
    @Test
    public void Should_ReturnRentalAgreement_When_AllValuesValidWithLocalDate() throws Exception {
        RentalAgreement agreement = new RentalAgreement("CHNS", LocalDate.of(2020, Month.APRIL, 20), 5, 20);
        assertEquals(agreement.getTool().getType(), "Chainsaw");
        assertTrue(agreement.getCheckoutDate() instanceof LocalDate);
        assertEquals(agreement.getRentalDays(), 5);
        assertEquals(agreement.getDiscountPercent(), 20);
    }

    @Test
    public void Should_ReturnRentalAgreement_When_AllValuesValidWithStringDate() throws Exception {
        RentalAgreement agreement = new RentalAgreement("CHNS", "04/20/20", 5, 20);
        assertEquals(agreement.getTool().getType(), "Chainsaw");
        assertTrue(agreement.getCheckoutDate() instanceof LocalDate);
        assertEquals(agreement.getRentalDays(), 5);
        assertEquals(agreement.getDiscountPercent(), 20);
    }

    // #endregion

    // #region Calculate Charge Days Tests
    @Test
    public void Should_Return5ChargeDays_When_ToolIsLadderAndRentalDaysIs5AndIncludesWeekends() throws Exception {
        RentalAgreement agreement = new RentalAgreement("LADW", "04/02/20", 5, 20);
        agreement.calculateChargeDays();
        assertEquals(agreement.getChargeDays(), 5);
    }

    @Test
    public void Should_Return3ChargeDays_When_ToolIsChainsawAndRentalDaysIs5AndIncludesWeends() throws Exception {
        RentalAgreement agreement = new RentalAgreement("CHNS", "04/02/20", 5, 20);
        agreement.calculateChargeDays();
        assertEquals(agreement.getChargeDays(), 3);
    }

    @Test
    public void Should_Return3ChargeDays_When_ToolIsJackhammerAndRentalDaysIs5AndIncludesWeends() throws Exception {
        RentalAgreement agreement = new RentalAgreement("JAKR", "04/02/20", 5, 20);
        agreement.calculateChargeDays();
        assertEquals(agreement.getChargeDays(), 3);
    }
    // #endregion

    // #region IsHoilday Tests
    @Test
    public void Should_ReturnDateIsHoliday_When_DateIsFridayJuly4th() {
        RentalAgreement agreement = new RentalAgreement();
        assertTrue(agreement.isHoliday(july4thFriday));
    }

    @Test
    public void Should_ReturnDateIsHoliday_When_DateIsMondayJuly4th() {
        RentalAgreement agreement = new RentalAgreement();
        assertTrue(agreement.isHoliday(july4thMonday));
    }

    @Test
    public void Should_ReturnDateIsHoliday_When_DateIsRegularJuly4th() {
        RentalAgreement agreement = new RentalAgreement();
        assertTrue(agreement.isHoliday(july4thRegular));
    }

    @Test
    public void Should_ReturnDateIsHoliday_When_DateIsEarlyLaborDay() {
        RentalAgreement agreement = new RentalAgreement();
        assertTrue(agreement.isHoliday(laborDayEarly));
    }

    @Test
    public void Should_ReturnDateIsHoliday_When_DateIsLateLaborDay() {
        RentalAgreement agreement = new RentalAgreement();
        assertTrue(agreement.isHoliday(laborDayLate));
    }

    @Test
    public void Should_ReturnDateIsNotHoliday_When_DateIsRegularDay() {
        RentalAgreement agreement = new RentalAgreement();
        assertFalse(agreement.isHoliday(regularDate));
    }
    // #endregion

    // #region Checkout Tests
    @Test
    public void Should_ThrowException_When_RentalDaysIsNonPositiveNumber() {
        assertThrows(IllegalArgumentException.class, () -> {
            RentalAgreement agreement = new RentalAgreement("CHNS", regularDate, 0, 20);
            agreement.checkout();
        });
    }

    // #region Final Tests from Spec
    @Test
    public void Should_ThrowException_When_DiscountAmountIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            RentalAgreement agreement = new RentalAgreement("JAKR",
                    LocalDate.parse("09/03/15", RentalAgreement.dateFormatter), 5, 101);
            agreement.checkout();
        });
    }

    // #endregion

    // #endregion

}