package com.brittanisavery.demos;

import com.brittanisavery.demos.models.RentalAgreement;

public final class FakeDepot {

    public static void main(String[] args) {
        try {
            RentalAgreement agreement = new RentalAgreement("LADW", "07/01/20", 4, 20);
            agreement.checkout();
            System.out.println(agreement);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
