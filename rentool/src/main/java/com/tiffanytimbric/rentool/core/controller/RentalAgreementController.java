package com.tiffanytimbric.rentool.core.controller;

import com.tiffanytimbric.rentool.core.model.RentalAgreement;
import com.tiffanytimbric.rentool.core.repository.RentalAgreementRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
@PreAuthorize("hasRole('USER')")
public class RentalAgreementController {

    private final RentalAgreementRepository rentalAgreementRepository;

    public RentalAgreementController(
            @NonNull final RentalAgreementRepository rentalAgreementRepository
    ) {
        this.rentalAgreementRepository = rentalAgreementRepository;
    }

    @GetMapping("/rentalAgreementExist/{id}")
    @NonNull
    public boolean doesExist(
            @PathVariable @Nullable final UUID id
    ) {
        if (id == null) {
            return rentalAgreementRepository.count() > 0;
        }

        return rentalAgreementRepository.existsById(id);
    }

    @GetMapping("/rentalAgreement/{id}")
    @NonNull
    public ResponseEntity<RentalAgreement> readRentalAgreement(
            @PathVariable @Nullable final UUID id
    ) {
        if (id == null) {
            return ResponseEntity.of(Optional.empty());
        }

        return ResponseEntity.of(
                rentalAgreementRepository.findById(id)
        );
    }

    @PostMapping("/rentalAgreement")
    @NonNull
    public ResponseEntity<RentalAgreement> createRentalAgreement(
            @RequestBody @Nullable final RentalAgreement rentalAgreement
    ) {
        if (rentalAgreement == null) {
            return ResponseEntity.of(Optional.empty());
        }

        if (rentalAgreement.idOpt().isEmpty()) {
            rentalAgreement.setId(UUID.randomUUID());
        }

        return ResponseEntity.of(
                Optional.of(rentalAgreementRepository.save(rentalAgreement))
        );
    }

    @PutMapping("/rentalAgreement")
    @NonNull
    public ResponseEntity<RentalAgreement> updateRentalAgreement(
            @RequestBody @Nullable final RentalAgreement rentalAgreement
    ) {
        if (rentalAgreement == null) {
            return ResponseEntity.of(Optional.empty());
        }

        return ResponseEntity.of(
                Optional.of(rentalAgreementRepository.save(rentalAgreement))
        );
    }

    @PatchMapping("/rentalAgreement")
    @NonNull
    public ResponseEntity<RentalAgreement> patchRentalAgreement(
            @RequestBody @Nullable final RentalAgreement rentalAgreement
    ) {
        if (rentalAgreement == null) {
            return ResponseEntity.of(Optional.empty());
        }

        throw new ResponseStatusException(
                HttpStatusCode.valueOf(400),
                "Invalid method, method not implemented.  Method Name: \"patchRentalAgreement\""
        );
    }

    @DeleteMapping("/rentalAgreement/{id}")
    @NonNull
    public ResponseEntity<RentalAgreement> deleteRentalAgreement(
            @PathVariable @Nullable final UUID id
    ) {
        if (id == null) {
            return ResponseEntity.of(Optional.empty());
        }

        final Optional<RentalAgreement> rentalAgreementOpt = rentalAgreementRepository.findById(id);
        if (rentalAgreementOpt.isEmpty()) {
            return ResponseEntity.of(Optional.empty());
        }

        rentalAgreementRepository.deleteById(id);

        return ResponseEntity.of(rentalAgreementOpt);
    }

}
