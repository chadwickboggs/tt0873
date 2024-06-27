package com.tiffanytimbric.rentool.core.service;

import com.tiffanytimbric.rentool.core.repository.RentalAgreementRepository;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@PreAuthorize("hasRole('USER')")
public class RentalService {

    private RentalAgreementRepository rentalAgreementRepository;

    public RentalService() {
    }

    public RentalService(RentalAgreementRepository rentalAgreementRepository) {
        this.rentalAgreementRepository = rentalAgreementRepository;
    }

    @Nullable
    public RentalAgreementRepository getRentalAgreementRepository() {
        return rentalAgreementRepository;
    }

    @NonNull
    public Optional<RentalAgreementRepository> rentalAgreementRepositoryOpt() {
        return Optional.ofNullable(rentalAgreementRepository);
    }

    public void setRentalAgreementRepository(
            @Nullable final RentalAgreementRepository rentalAgreementRepository
    ) {
        this.rentalAgreementRepository = rentalAgreementRepository;
    }

}
