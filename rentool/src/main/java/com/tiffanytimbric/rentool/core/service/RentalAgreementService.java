package com.tiffanytimbric.rentool.core.service;

import com.tiffanytimbric.fsm.FiniteStateMachine;
import com.tiffanytimbric.fsm.State;
import com.tiffanytimbric.rentool.core.model.RentalAgreement;
import com.tiffanytimbric.rentool.core.repository.RentalAgreementRepository;
import com.tiffanytimbric.rentool.core.util.RentalAgreementUtil;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Service
//@PreAuthorize("hasRole('USER')")
public class RentalAgreementService {

    private RentalAgreementRepository rentalAgreementRepository;

    public RentalAgreementService(
            @NonNull final RentalAgreementRepository rentalAgreementRepository
    ) {
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

    @NonNull
    public Optional<RentalAgreement> handleRentalAgreementEvent(
            @Nullable final String eventName,
            @Nullable final UUID rentalAgreementId,
            @Nullable final UUID userId
    ) {
        if (isBlank(eventName) || rentalAgreementId == null || userId == null) {
            return Optional.empty();
        }

        if (!rentalAgreementRepository.existsById(rentalAgreementId)) {
            return Optional.empty();
        }

        final Optional<RentalAgreement> rentalAgreementOpt = rentalAgreementRepository.findById(rentalAgreementId);
        if (rentalAgreementOpt.isEmpty()) {
            return rentalAgreementOpt;
        }

        return handleRentalAgreementEvent(
                eventName, rentalAgreementOpt.get(), userId
        );
    }

    @NonNull
    public Optional<RentalAgreement> handleRentalAgreementEvent(
            @Nullable final String eventName,
            @Nullable RentalAgreement rentalAgreement,
            @Nullable final UUID userId
    ) {
        if (isBlank(eventName) || rentalAgreement == null || userId == null) {
            return Optional.empty();
        }

        final FiniteStateMachine rentalAgreementFsm = getRentalAgreementFsm(rentalAgreement);

        final State<String> toState = rentalAgreementFsm.handleEvent(eventName);

        if (rentalAgreement.getState().equalsIgnoreCase(toState.name())) {
            return Optional.of(rentalAgreement);
        }

        rentalAgreement.setState(toState.name());

        rentalAgreement = rentalAgreementRepository.save(rentalAgreement);

        return Optional.of(rentalAgreement);
    }

    @NonNull
    public FiniteStateMachine getRentalAgreementFsm(
            @Nullable final RentalAgreement rentalAgreement
    ) {
        final FiniteStateMachine fsm = RentalAgreementUtil.newRentalAgreementFsm();
        if (rentalAgreement == null) {
            return fsm;
        }

        fsm.findState(rentalAgreement.getState()).ifPresent(fsmState -> {
            fsm.setCurrentState(new State<>(
                    rentalAgreement.getState(),
                    rentalAgreement.getDataItem(),
                    ((State) fsmState).transitions()
            ));
        });

        return fsm;
    }

}
