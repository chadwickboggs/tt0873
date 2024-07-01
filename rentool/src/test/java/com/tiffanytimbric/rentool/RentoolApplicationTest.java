package com.tiffanytimbric.rentool;

import com.tiffanytimbric.rentool.core.controller.RentalAgreementController;
import com.tiffanytimbric.rentool.core.model.RentalAgreement;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class RentoolApplicationTest {

    @Inject
    private RentalAgreementController rentalAgreementController;

    @Test
    void contextLoads() {
        assertThat(rentalAgreementController).isNotNull();
    }

    @Test
    public void testCheckout_JAKR_Discount_Invalid() {
        final RentalAgreement rentalAgreement = new RentalAgreement(
                null,
                "1233ba9c-8403-492e-9f4c-5b3b5464c3a4",
                "04a7bf6a-f317-4eea-9311-fa4d711f860f",
                5,
                LocalDate.of(2015, 9, 3),
                10.15f,
                101
        );

        assertThrows(
                TransactionSystemException.class,
                () -> rentalAgreementController.createRentalAgreement(rentalAgreement)
        );
    }

    @Test
    public void testCreateRentalAgreement_LADW_Discount_10() {
        final RentalAgreement inputRentalAgreement = new RentalAgreement(
                null,
                "1233ba9c-8403-492e-9f4c-5b3b5464c3a3",
                "04a7bf6a-f317-4eea-9311-fa4d711f860f",
                3,
                LocalDate.of(2020, 7, 20),
                10.15f,
                10
        );
        final RentalAgreement expectedRentalAgreement = expectedRentalAgreement(
                inputRentalAgreement,
                "Proposed",
                "JAKD",
                "Jackhammer",
                "tiffanytimbric", LocalDate.of(2020, 7, 23),
                "$10.15",
                3,
                30.449999f,
                "$30.45",
                27.404999f,
                "$27.40"
        );

        testCreateRentalAgreement(inputRentalAgreement, expectedRentalAgreement);
    }

    @Test
    public void testCreateRentalAgreement_CHNS_Discount_25() {
        final RentalAgreement inputRentalAgreement = new RentalAgreement(
                null,
                "1233ba9c-8403-492e-9f4c-5b3b5464c3a1",
                "04a7bf6a-f317-4eea-9311-fa4d711f860f",
                5,
                LocalDate.of(2015, 7, 2),
                10.15f,
                25
        );
        final RentalAgreement expectedRentalAgreement = expectedRentalAgreement(
                inputRentalAgreement,
                "Proposed",
                "CHNS",
                "Chainsaw",
                "tiffanytimbric", LocalDate.of(2015, 7, 7),
                "$10.15",
                5,
                50.75f,
                "$50.75",
                38.0625f,
                "$38.06"
        );

        testCreateRentalAgreement(inputRentalAgreement, expectedRentalAgreement);
    }

    @Test
    public void testCreateRentalAgreement_JAKD_Discount_0() {
        final RentalAgreement inputRentalAgreement = new RentalAgreement(
                null,
                "1233ba9c-8403-492e-9f4c-5b3b5464c3a3",
                "04a7bf6a-f317-4eea-9311-fa4d711f860f",
                6,
                LocalDate.of(2015, 9, 3),
                10.15f,
                0
        );
        final RentalAgreement expectedRentalAgreement = expectedRentalAgreement(
                inputRentalAgreement,
                "Proposed",
                "JAKD",
                "Jackhammer",
                "tiffanytimbric", LocalDate.of(2015, 9, 9),
                "$10.15",
                6,
                60.9f,
                "$60.90",
                60.9f,
                "$60.90"
        );

        testCreateRentalAgreement(inputRentalAgreement, expectedRentalAgreement);
    }

    @Test
    public void testCreateRentalAgreement_JAKR_Discount_0() {
        final RentalAgreement inputRentalAgreement = new RentalAgreement(
                null,
                "1233ba9c-8403-492e-9f4c-5b3b5464c3a4",
                "04a7bf6a-f317-4eea-9311-fa4d711f860f",
                9,
                LocalDate.of(2015, 7, 2),
                10.15f,
                0
        );
        final RentalAgreement expectedRentalAgreement = expectedRentalAgreement(
                inputRentalAgreement,
                "Proposed",
                "JAKR",
                "Jackhammer",
                "tiffanytimbric", LocalDate.of(2015, 7, 11),
                "$10.15",
                9,
                91.350006f,
                "$91.35",
                91.350006f,
                "$91.35"
        );

        testCreateRentalAgreement(inputRentalAgreement, expectedRentalAgreement);
    }

    @Test
    public void testCreateRentalAgreement_JAKR_Discount_50() {
        final RentalAgreement inputRentalAgreement = new RentalAgreement(
                null,
                "1233ba9c-8403-492e-9f4c-5b3b5464c3a4",
                "04a7bf6a-f317-4eea-9311-fa4d711f860f",
                4,
                LocalDate.of(2015, 7, 20),
                10.15f,
                50
        );
        final RentalAgreement expectedRentalAgreement = expectedRentalAgreement(
                inputRentalAgreement,
                "Proposed",
                "JAKR",
                "Jackhammer",
                "tiffanytimbric", LocalDate.of(2015, 7, 24),
                "$10.15",
                4,
                40.6f,
                "$40.60",
                20.3f,
                "$20.03"
        );

        testCreateRentalAgreement(inputRentalAgreement, expectedRentalAgreement);
    }

    private void testCreateRentalAgreement(
            @Nonnull final RentalAgreement inputRentalAgreement,
            @Nonnull final RentalAgreement expectedRentalAgreement
    ) {
        final ResponseEntity<RentalAgreement> responseEntity =
                rentalAgreementController.createRentalAgreement(inputRentalAgreement);

        assertThat(responseEntity).isNotNull();

        assertThat(responseEntity.getStatusCode().is2xxSuccessful()).isTrue();

        assertThat(responseEntity.hasBody()).isTrue();
        final RentalAgreement createdRentalAgreement = responseEntity.getBody();
        assertThat(createdRentalAgreement).isNotNull();

        createdRentalAgreement.setId(null);
        assertThat(createdRentalAgreement).isEqualTo(expectedRentalAgreement);
    }

    @Nonnull
    private static RentalAgreement expectedRentalAgreement(
            @Nonnull final RentalAgreement rentalAgreement,
            @Nullable final String state,
            @Nullable final String toolCode,
            @Nullable final String toolType,
            @Nullable final String renterName,
            @Nullable final LocalDate dueDate,
            @Nullable final String dailyRentalChargeCurrency,
            int chargeDays, float preDiscountCharge,
            @Nullable final String preDiscountChargeCurrency,
            float finalCharge,
            @Nullable final String finalChargeCurrency
    ) {
        final RentalAgreement expectedRentalAgreement = (RentalAgreement) rentalAgreement.clone();
        expectedRentalAgreement.setState(state);
        expectedRentalAgreement.setToolCode(toolCode);
        expectedRentalAgreement.setToolType(toolType);
        expectedRentalAgreement.setRenterName(renterName);
        expectedRentalAgreement.setDueDate(dueDate);
        expectedRentalAgreement.setDailyRentalChargeCurrency(dailyRentalChargeCurrency);
        expectedRentalAgreement.setChargeDays(chargeDays);
        expectedRentalAgreement.setPreDiscountCharge(preDiscountCharge);
        expectedRentalAgreement.setPreDiscountChargeCurrency(preDiscountChargeCurrency);
        expectedRentalAgreement.setFinalCharge(finalCharge);
        expectedRentalAgreement.setFinalChargeCurrency(finalChargeCurrency);

        return expectedRentalAgreement;
    }

}
