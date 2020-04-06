package com.brittanisavery.demos.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.Month;

public class RentalAgreementTest {

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

    @Test
    public void Should_ThrowException_When_RentalDaysIsNonPositiveNumber() {
        assertThrows(IllegalArgumentException.class, () -> {
            RentalAgreement agreement = new RentalAgreement("CHNS", LocalDate.of(2020, Month.APRIL, 20), 0, 20);
            agreement.getRentalDays();
        });
    }

    @Test
    public void Should_ThrowException_When_DiscountAmountIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            RentalAgreement agreement = new RentalAgreement("CHNS", LocalDate.of(2020, Month.APRIL, 20), 5, 101);
            agreement.getRentalDays();
        });
    }
}