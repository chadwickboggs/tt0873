package com.tiffanytimbric.rentool.core.service;

import com.tiffanytimbric.fsm.FiniteStateMachine;
import com.tiffanytimbric.fsm.State;
import com.tiffanytimbric.rentool.core.model.RentalAgreement;
import com.tiffanytimbric.rentool.core.model.Tool;
import com.tiffanytimbric.rentool.core.model.User;
import com.tiffanytimbric.rentool.core.repository.RentalAgreementRepository;
import com.tiffanytimbric.rentool.core.repository.ToolRepository;
import com.tiffanytimbric.rentool.core.repository.UserRepository;
import com.tiffanytimbric.rentool.core.util.LangUtil;
import com.tiffanytimbric.rentool.core.util.RentalAgreementUtil;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Service
//@PreAuthorize("hasRole('USER')")
public class RentalAgreementService {

    private RentalAgreementRepository rentalAgreementRepository;
    private UserRepository userRepository;
    private ToolRepository toolRepository;

    public RentalAgreementService(
            @NonNull final RentalAgreementRepository rentalAgreementRepository,
            @NonNull final UserRepository userRepository,
            @NonNull final ToolRepository toolRepository
    ) {
        this.rentalAgreementRepository = rentalAgreementRepository;
        this.userRepository = userRepository;
        this.toolRepository = toolRepository;
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

    @Nullable
    public UserRepository getUserRepository() {
        return userRepository;
    }

    @NonNull
    public Optional<UserRepository> userRepositoryOpt() {
        return Optional.ofNullable(userRepository);
    }

    public void setUserRepository(@NonNull final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Nullable
    public ToolRepository getToolRepository() {
        return toolRepository;
    }

    @NonNull
    public Optional<ToolRepository> toolRepositoryOpt() {
        return Optional.ofNullable(toolRepository);
    }

    public void setToolRepository(@NonNull final ToolRepository toolRepository) {
        this.toolRepository = toolRepository;
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

    @NonNull
    public RentalAgreement setToolCode(
            @NonNull final RentalAgreement rentalAgreement
    ) {
        rentalAgreement.toolIdOpt()
                .flatMap(toolId ->
                        toolRepository.findById(UUID.fromString(toolId)).stream()
                                .map(Tool::getCode)
                                .filter(Objects::nonNull)
                                .findFirst()
                ).ifPresent(rentalAgreement::setToolCode);

        return rentalAgreement;
    }

    @NonNull
    public RentalAgreement setToolType(
            @NonNull final RentalAgreement rentalAgreement
    ) {
        rentalAgreement.toolIdOpt()
                .flatMap(toolId ->
                        toolRepository.findById(UUID.fromString(toolId)).stream()
                                .map(Tool::getType)
                                .filter(Objects::nonNull)
                                .findFirst()
                ).ifPresent(rentalAgreement::setToolType);

        return rentalAgreement;
    }

    @NonNull
    public RentalAgreement setRenterName(
            @NonNull final RentalAgreement rentalAgreement
    ) {
        rentalAgreement.renterIdOpt()
                .flatMap(renterId ->
                        userRepository.findById(UUID.fromString(renterId)).stream()
                                .map(User::getName)
                                .filter(Objects::nonNull)
                                .findFirst()
                ).ifPresent(rentalAgreement::setRenterName);

        return rentalAgreement;
    }

    @NonNull
    public RentalAgreement setDailyRentalChargeCurrency(
            @NonNull final RentalAgreement rentalAgreement
    ) {
        rentalAgreement.dailyRentalChargeOpt().ifPresent(dailyRentalCharge ->
                rentalAgreement.setDailyRentalChargeCurrency(
                        LangUtil.currency(dailyRentalCharge)
                )
        );

        return rentalAgreement;
    }

    @NonNull
    public RentalAgreement setCheckoutDateFormatted(
            @NonNull final RentalAgreement rentalAgreement
    ) {
        rentalAgreement.checkoutDateOpt().ifPresent(checkoutDate ->
                rentalAgreement.setCheckoutDateFormatted(
                        new SimpleDateFormat("MM/dd/yy").format(
                                checkoutDate
                        )
                )
        );

        return rentalAgreement;
    }

    public void setRenterId(
            @Nullable final RentalAgreement rentalAgreement
    ) {
        if (rentalAgreement == null) {
            return;
        }

        rentalAgreement.renterNameOpt()
                .flatMap(renterName ->
                        userRepository.findByName(renterName).stream().findFirst()
                )
                .ifPresent(renter ->
                        rentalAgreement.setRenterId(
                                renter.getId().toString()
                        )
                );
    }

    public void setToolId(
            @Nullable final RentalAgreement rentalAgreement
    ) {
        if (rentalAgreement == null) {
            return;
        }

        rentalAgreement.toolCodeOpt()
                .flatMap(toolCode ->
                        toolRepository.findByCode(toolCode).stream().findFirst()
                )
                .ifPresent(tool ->
                        rentalAgreement.setToolId(
                                tool.getId().toString()
                        )
                );
    }
}
