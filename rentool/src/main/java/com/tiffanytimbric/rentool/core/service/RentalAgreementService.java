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

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static com.tiffanytimbric.rentool.core.util.LangUtil.isWeekday;
import static com.tiffanytimbric.rentool.core.util.LangUtil.isWeekend;
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
    public RentalAgreement setPreDiscountCharge(
            @NonNull final RentalAgreement rentalAgreement
    ) {
        rentalAgreement.toolIdOpt().ifPresent(toolId ->
                rentalAgreement.checkoutDateOpt().ifPresent(checkoutDate ->
                        rentalAgreement.rentalDaysOpt().ifPresent(rentalDays ->
                                rentalAgreement.dailyRentalChargeOpt().ifPresent(dailyRentalCharge ->
                                        rentalAgreement.setPreDiscountCharge(
                                                calculatePreDiscountCharge(
                                                        toolId, checkoutDate, rentalDays, dailyRentalCharge
                                                )
                                        )
                                )
                        )
                )
        );

        return rentalAgreement;
    }

    public float calculatePreDiscountCharge(
            @NonNull final String toolId,
            @NonNull final LocalDate checkoutDate,
            int rentalDays,
            float dailyRentalCharge) {
        final Optional<Tool> toolOpt = toolRepository.findById(UUID.fromString(toolId));
        if (toolOpt.isEmpty()) {
            return rentalDays * dailyRentalCharge;
        }

        final Tool tool = toolOpt.get();

        return checkoutDate.datesUntil(checkoutDate.plusDays(rentalDays))
                .map(date -> {
                    if (isWeekday(date)) {
                        return tool.getWeekdaysFree() ? 0f : dailyRentalCharge;
                    }
                    else if (isWeekend(date)) {
                        return tool.getWeekendsFree() ? 0f : dailyRentalCharge;
                    }
                    else if (isHoliday(date)) {
                        return tool.getHolidaysFree() ? 0f : dailyRentalCharge;
                    }

                    return dailyRentalCharge;
                })
                .reduce(Float::sum)
                .orElse(
                        rentalDays * dailyRentalCharge
                );
    }

    private boolean isHoliday(@NonNull final LocalDate date) {
        // TODO: Implement.

        return false;
    }

    @NonNull
    public RentalAgreement setPreDiscountChargeCurrency(
            @NonNull final RentalAgreement rentalAgreement
    ) {
        rentalAgreement.preDiscountChargeOpt().ifPresent(preDiscountCharge ->
                rentalAgreement.setPreDiscountChargeCurrency(
                        LangUtil.currency(preDiscountCharge)
                )
        );

        return rentalAgreement;
    }

    @NonNull
    public RentalAgreement setTotalCharge(
            @NonNull final RentalAgreement rentalAgreement
    ) {
        rentalAgreement.preDiscountChargeOpt().ifPresent(preDiscountCharge ->
                rentalAgreement.discountPercentOpt().ifPresent(discountPercent ->
                        rentalAgreement.setTotalCharge(
                                preDiscountCharge * (1f - discountPercent / 100f)
                        )
                )
        );

        return rentalAgreement;
    }

    @NonNull
    public RentalAgreement setTotalChargeCurrency(
            @NonNull final RentalAgreement rentalAgreement
    ) {
        rentalAgreement.totalChargeOpt().ifPresent(totalCharge ->
                rentalAgreement.setTotalChargeCurrency(
                        LangUtil.currency(totalCharge)
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
                        toolRepository.findByCode(toolCode)
                )
                .ifPresent(tool ->
                        rentalAgreement.setToolId(
                                tool.getId().toString()
                        )
                );
    }
}
