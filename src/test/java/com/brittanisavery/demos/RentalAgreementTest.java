package com.brittanisavery.demos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.Month;

import com.brittanisavery.demos.models.RentalAgreement;

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
}