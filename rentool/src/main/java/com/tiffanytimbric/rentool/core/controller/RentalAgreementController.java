package com.tiffanytimbric.rentool.core.controller;

import com.tiffanytimbric.rentool.core.model.RentalAgreement;
import com.tiffanytimbric.rentool.core.model.Tool;
import com.tiffanytimbric.rentool.core.model.User;
import com.tiffanytimbric.rentool.core.repository.RentalAgreementRepository;
import com.tiffanytimbric.rentool.core.repository.ToolRepository;
import com.tiffanytimbric.rentool.core.repository.UserRepository;
import com.tiffanytimbric.rentool.core.service.RentalAgreementService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isBlank;

@RestController
//@PreAuthorize("hasRole('USER')")
public class RentalAgreementController {

    public static final String ACTION_ACCEPT = "Accept";
    public static final String ACTION_CANCEL = "Cancel";
    public static final String ACTION_PICK_UP = "PickUp";
    public static final String ACTION_FAIL = "Fail";
    public static final String ACTION_RETURN = "Return";

    private final RentalAgreementService rentalAgreementService;
    private final RentalAgreementRepository rentalAgreementRepository;
    private final UserRepository userRepository;
    private final ToolRepository toolRepository;

    public RentalAgreementController(
            @NonNull final RentalAgreementService rentalAgreementService,
            @NonNull final RentalAgreementRepository rentalAgreementRepository,
            @NonNull final UserRepository userRepository,
            @NonNull final ToolRepository toolRepository
    ) {
        this.rentalAgreementService = rentalAgreementService;
        this.rentalAgreementRepository = rentalAgreementRepository;
        this.userRepository = userRepository;
        this.toolRepository = toolRepository;
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

    @GetMapping("/rentalAgreementAccept/{rentalAgreementId}/{userId}")
    @NonNull
    public ResponseEntity<RentalAgreement> actionAcceptRentalAgreement(
            @PathVariable final UUID rentalAgreementId,
            @PathVariable final UUID userId
    ) {
        final Optional<RentalAgreement> rentalAgreementOpt = rentalAgreementService.handleRentalAgreementEvent(
                ACTION_ACCEPT, rentalAgreementId, userId
        ).stream()
                .map(rentalAgreementService::setTransientFields)
                .findFirst();

        return ResponseEntity.of(rentalAgreementOpt);
    }

    @GetMapping("/rentalAgreementCancel/{rentalAgreementId}/{userId}")
    @NonNull
    public ResponseEntity<RentalAgreement> actionCancelRentalAgreement(
            @PathVariable final UUID rentalAgreementId,
            @PathVariable final UUID userId
    ) {
        final Optional<RentalAgreement> rentalAgreementOpt = rentalAgreementService.handleRentalAgreementEvent(
                ACTION_CANCEL, rentalAgreementId, userId
        ).stream()
                .map(rentalAgreementService::setTransientFields)
                .findFirst();

        return ResponseEntity.of(rentalAgreementOpt);
    }

    @GetMapping("/rentalAgreementPickUp/{rentalAgreementId}/{userId}")
    @NonNull
    public ResponseEntity<RentalAgreement> actionPickUpRentalAgreement(
            @PathVariable final UUID rentalAgreementId,
            @PathVariable final UUID userId
    ) {
        final Optional<RentalAgreement> rentalAgreementOpt = rentalAgreementService.handleRentalAgreementEvent(
                ACTION_PICK_UP, rentalAgreementId, userId
        ).stream()
                .map(rentalAgreementService::setTransientFields)
                .findFirst();

        return ResponseEntity.of(rentalAgreementOpt);
    }

    @GetMapping("/rentalAgreementFail/{rentalAgreementId}/{userId}")
    @NonNull
    public ResponseEntity<RentalAgreement> actionFailRentalAgreement(
            @PathVariable final UUID rentalAgreementId,
            @PathVariable final UUID userId
    ) {
        final Optional<RentalAgreement> rentalAgreementOpt = rentalAgreementService.handleRentalAgreementEvent(
                ACTION_FAIL, rentalAgreementId, userId
        ).stream()
                .map(rentalAgreementService::setTransientFields)
                .findFirst();

        return ResponseEntity.of(rentalAgreementOpt);
    }

    @GetMapping("/rentalAgreementReturn/{rentalAgreementId}/{userId}")
    @NonNull
    public ResponseEntity<RentalAgreement> actionReturnRentalAgreement(
            @PathVariable final UUID rentalAgreementId,
            @PathVariable final UUID userId
    ) {
        final Optional<RentalAgreement> rentalAgreementOpt = rentalAgreementService.handleRentalAgreementEvent(
                ACTION_RETURN, rentalAgreementId, userId
        ).stream()
                .map(rentalAgreementService::setTransientFields)
                .findFirst();

        return ResponseEntity.of(rentalAgreementOpt);
    }

    @GetMapping("/rentalAgreement")
    @NonNull
    public ResponseEntity<List<RentalAgreement>> readAllRentalAgreements() {
        return ResponseEntity.ofNullable(
                rentalAgreementRepository.findAll().stream()
                        .map(rentalAgreementService::setTransientFields)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/rentalAgreement/{id}")
    @NonNull
    public ResponseEntity<RentalAgreement> readRentalAgreement(
            @PathVariable @Nullable final UUID id
    ) {
        if (id == null) {
            return ResponseEntity.of(Optional.empty());
        }

        final Optional<RentalAgreement> rentalAgreementOpt = rentalAgreementRepository.findById(id)
                .map(rentalAgreementService::setTransientFields);

        return ResponseEntity.of(rentalAgreementOpt);
    }

    @GetMapping("/rentalAgreementByToolCode/{code}")
    @NonNull
    public ResponseEntity<List<RentalAgreement>> readRentalAgreementsByToolCode(
            @PathVariable @Nullable final String code
    ) {
        if (isBlank(code)) {
            return ResponseEntity.of(Optional.empty());
        }

        final Optional<Tool> toolOpt = toolRepository.findByCode(code);
        if (toolOpt.isEmpty()) {
            return ResponseEntity.of(Optional.empty());
        }

        final String toolId = toolOpt.get().getId().toString();

        final List<RentalAgreement> rentalAgreements = rentalAgreementRepository.findByToolId(
                toolId
        ).stream()
                .map(rentalAgreementService::setTransientFields)
                .collect(Collectors.toList());

        return ResponseEntity.of(
                Optional.of(rentalAgreements)
        );
    }

    @GetMapping("/rentalAgreementByUserName/{name}")
    @NonNull
    public ResponseEntity<List<RentalAgreement>> readRentalAgreementsByUserName(
            @PathVariable @Nullable final String name
    ) {
        if (isBlank(name)) {
            return ResponseEntity.of(Optional.empty());
        }

        final Optional<User> renterOpt = userRepository.findByName(name);
        if (renterOpt.isEmpty()) {
            return ResponseEntity.of(Optional.empty());
        }

        final String renterId = renterOpt.get().getId().toString();

        final List<RentalAgreement> rentalAgreements = rentalAgreementRepository.findByRenterId(
                renterId
        ).stream()
                .map(rentalAgreementService::setTransientFields)
                .collect(Collectors.toList());

        return ResponseEntity.of(
                Optional.of(rentalAgreements)
        );
    }

    @PostMapping("/rentalAgreement")
    @NonNull
    public ResponseEntity<RentalAgreement> createRentalAgreement(
            @RequestBody @Valid  @Nullable final RentalAgreement rentalAgreement
    ) {
        if (rentalAgreement == null) {
            return ResponseEntity.of(Optional.empty());
        }

        if (rentalAgreement.idOpt().isEmpty()) {
            rentalAgreement.setId(UUID.randomUUID());
        }

        final RentalAgreement savedRentalAgreement = rentalAgreementRepository.save(rentalAgreement);

        rentalAgreementService.setTransientFields(savedRentalAgreement);

        return ResponseEntity.of(
                Optional.of(savedRentalAgreement)
        );
    }

    @PostMapping("/rentalAgreementByName")
    @NonNull
    public ResponseEntity<RentalAgreement> createRentalAgreementByName(
            @RequestBody @Valid @Nullable final RentalAgreement rentalAgreement
    ) {
        if (rentalAgreement == null) {
            return ResponseEntity.of(Optional.empty());
        }

        if (rentalAgreement.idOpt().isEmpty()) {
            rentalAgreement.setId(UUID.randomUUID());
        }

        rentalAgreementService.setToolId(rentalAgreement);
        rentalAgreementService.setRenterId(rentalAgreement);

        final RentalAgreement savedRentalAgreement = rentalAgreementRepository.save(rentalAgreement);

        rentalAgreementService.setTransientFields(savedRentalAgreement);

        return ResponseEntity.of(
                Optional.of(savedRentalAgreement)
        );
    }

    @PutMapping("/rentalAgreement")
    @NonNull
    public ResponseEntity<RentalAgreement> updateRentalAgreement(
            @RequestBody @Valid @Nullable final RentalAgreement rentalAgreement
    ) {
        if (rentalAgreement == null) {
            return ResponseEntity.of(Optional.empty());
        }

        final RentalAgreement savedRentalAgreement = rentalAgreementRepository.save(rentalAgreement);

        rentalAgreementService.setTransientFields(savedRentalAgreement);

        return ResponseEntity.of(
                Optional.of(savedRentalAgreement)
        );
    }

    @PatchMapping("/rentalAgreement")
    @NonNull
    public ResponseEntity<RentalAgreement> patchRentalAgreement(
            @RequestBody @Valid @Nullable final RentalAgreement rentalAgreement
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
