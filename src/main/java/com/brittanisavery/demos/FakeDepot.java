package com.brittanisavery.demos;

import com.brittanisavery.demos.models.RentalAgreement;

public final class FakeDepot {

    public static void main(String[] args) {
        try {
            RentalAgreement agreement = new RentalAgreement("JAKR", "04/02/20", 5, 20);
            agreement.checkout();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
