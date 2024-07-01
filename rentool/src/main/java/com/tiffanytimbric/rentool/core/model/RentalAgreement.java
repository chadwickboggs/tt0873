package com.tiffanytimbric.rentool.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Entity
@Table(name = "rental_agrmnt")
public class RentalAgreement implements Serializable, Cloneable {

    @Serial
    private static final long serialVersionUID = 7767596765816499575L;

    @Id
    private UUID id;
    @JoinColumn(
            table = "Tool",
            name = "tool_id",
            referencedColumnName = "id",
            nullable = false
    )
//    @NotBlank
    private String toolId;

    @Transient
    private String toolCode;

    @Transient
    private String toolType;

    @JoinColumn(
            table = "User",
            name = "renter_id",
            referencedColumnName = "id",
            nullable = false
    )
//    @NotBlank
    private String renterId;

    @Transient
    private String renterName;

    @Range(
            min = 1,
            message = "The rental days value must be greater than 1."
    )
    private Integer rentalDays = 1;

    @JsonFormat(pattern = "MM/dd/yy")
    @DateTimeFormat(pattern = "MM/dd/yy")
    private LocalDate checkoutDate;

    private Float dailyRentalCharge;

    @Transient
    private String dailyRentalChargeCurrency;

//    private Integer chargeDays;  // TODO: Add method which calculates this value.

    @Transient
    private Float preDiscountCharge;
    @Transient
    private String preDiscountChargeCurrency;

    @Transient
    private Float totalCharge;
    @Transient
    private String totalChargeCurrency;

    @Range(
            min = 0, max = 100,
            message = "The discount percentage value must be between 0 and 100."
    )
    private Integer discountPercent = 0;

    private String state = "Proposed";
    private String dataItem;

    public RentalAgreement() {
    }

    public RentalAgreement(
            @NonNull final UUID id,
            @NonNull final String toolId,
            @NonNull final String renterId,
            @NonNull final Integer rentalDays,
            @NonNull final LocalDate checkoutDate,
            @NonNull final Float dailyRentalCharge,
            @NonNull final Integer discountPercent
    ) {
        this(
                id, toolId, null, null, renterId, null,
                rentalDays, checkoutDate, dailyRentalCharge,
                null, null, null, null, null, discountPercent
        );
    }

    public RentalAgreement(
            @NonNull final UUID id,
            @NonNull final String toolId,
            @NonNull final String toolCode,
            @NonNull final String toolType,
            @NonNull final String renterId,
            @NonNull final String renterName,
            @NonNull final Integer rentalDays,
            @NonNull final LocalDate checkoutDate,
            @NonNull final Float dailyRentalCharge,
            @NonNull final String dailyRentalChargeCurrency,
            @NonNull final Float preDiscountCharge,
            @NonNull final String preDiscountChargeCurrency,
            @NonNull final Float totalCharge,
            @NonNull final String totalChargeCurrency,
            @NonNull final Integer discountPercent
    ) {
        this.id = id;
        this.toolId = toolId;
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.renterId = renterId;
        this.renterName = renterName;
        this.rentalDays = rentalDays;
        this.checkoutDate = checkoutDate;
        this.dailyRentalCharge = dailyRentalCharge;
        this.dailyRentalChargeCurrency = dailyRentalChargeCurrency;
        this.preDiscountCharge = preDiscountCharge;
        this.preDiscountChargeCurrency = preDiscountChargeCurrency;
        this.totalCharge = totalCharge;
        this.totalChargeCurrency = totalChargeCurrency;
        this.discountPercent = discountPercent;
    }

    @Nullable
    public UUID getId() {
        return id;
    }

    @NonNull
    public Optional<UUID> idOpt() {
        return Optional.ofNullable(id);
    }

    @Nullable
    public String getToolId() {
        return toolId;
    }

    @NonNull
    public Optional<String> toolIdOpt() {
        return Optional.ofNullable(toolId);
    }

    public void setToolId(@NonNull final String toolOneId) {
        this.toolId = toolOneId;
    }

    @Nullable
    public String getRenterId() {
        return renterId;
    }

    @NonNull
    public Optional<String> renterIdOpt() {
        if (isBlank(renterId)) {
            return Optional.empty();
        }

        return Optional.of(renterId);
    }

    public void setRenterId(@NonNull final String renterId) {
        this.renterId = renterId;
    }

    @NonNull
    public Optional<UUID> nameOpt() {
        return Optional.ofNullable(id);
    }

    public void setId(@NonNull final UUID name) {
        this.id = name;
    }

    @Nullable
    public Integer getRentalDays() {
        return rentalDays;
    }

    @NonNull
    public Optional<Integer> rentalDaysOpt() {
        return Optional.ofNullable(rentalDays);
    }

    public void setRentalDays(
            @Nullable final Integer rentalDays
    ) {
        this.rentalDays = rentalDays;
    }

    @Nullable
    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    @NonNull
    public Optional<LocalDate> checkoutDateOpt() {
        return Optional.ofNullable(checkoutDate);
    }

    public void setCheckoutDate(
            @Nullable final LocalDate checkoutDate
    ) {
        this.checkoutDate = checkoutDate;
    }

    @Nullable
    public Float getDailyRentalCharge() {
        return dailyRentalCharge;
    }

    @NonNull
    public Optional<Float> dailyRentalChargeOpt() {
        return Optional.ofNullable(dailyRentalCharge);
    }

    public void setDailyRentalCharge(
            @Nullable final Float dailyRentalCharge
    ) {
        this.dailyRentalCharge = dailyRentalCharge;
    }

    @Nullable
    public Integer getDiscountPercent() {
        return discountPercent;
    }

    @NonNull
    public Optional<Integer> discountPercentOpt() {
        return Optional.ofNullable(discountPercent);
    }

    public void setDiscountPercent(
            @Nullable final Integer discountPercent
    ) {
        this.discountPercent = discountPercent;
    }

    @NonNull
    public String getState() {
        return state;
    }

    @NonNull
    public Optional<String> stateOpt() {
        if (isBlank(state)) {
            return Optional.empty();
        }

        return Optional.of(state);
    }

    public void setState(
            @Nullable final String state
    ) {
        this.state = state;
    }

    @Nullable
    public String getDataItem() {
        return dataItem;
    }

    @NonNull
    public Optional<String> dataItemOpt() {
        if (isBlank(dataItem)) {
            return Optional.empty();
        }

        return Optional.of(dataItem);
    }

    public void setDataItem(
            @Nullable final String dataItem
    ) {
        this.dataItem = dataItem;
    }

    @Nullable
    public String getToolCode() {
        return toolCode;
    }

    @NonNull
    public Optional<String> toolCodeOpt() {
        if (isBlank(toolCode)) {
            return Optional.empty();
        }

        return Optional.of(toolCode);
    }

    public void setToolCode(@NonNull final String toolCode) {
        this.toolCode = toolCode;
    }

    @Nullable
    public String getToolType() {
        return toolType;
    }

    @NonNull
    public Optional<String> toolTypeOpt() {
        if (isBlank(toolType)) {
            return Optional.empty();
        }

        return Optional.of(toolType);
    }

    public void setToolType(@NonNull final String toolType) {
        this.toolType = toolType;
    }

    @Nullable
    public String getRenterName() {
        return renterName;
    }

    @NonNull
    public Optional<String> renterNameOpt() {
        if (isBlank(renterName)) {
            return Optional.empty();
        }

        return Optional.of(renterName);
    }

    public void setRenterName(@NonNull final String renterName) {
        this.renterName = renterName;
    }

    @Nullable
    public String getDailyRentalChargeCurrency() {
        return dailyRentalChargeCurrency;
    }

    @NonNull
    public Optional<String> dailyRentalChargeCurrencyOpt() {
        if (isBlank(dailyRentalChargeCurrency)) {
            return Optional.empty();
        }

        return Optional.of(dailyRentalChargeCurrency);
    }

    public void setDailyRentalChargeCurrency(
            @NonNull final String dailyRentalChargeCurrency
    ) {
        this.dailyRentalChargeCurrency = dailyRentalChargeCurrency;
    }

    @Nullable
    public Float getPreDiscountCharge() {
        return preDiscountCharge;
    }

    @NonNull
    public Optional<Float> preDiscountChargeOpt() {
        return Optional.ofNullable(preDiscountCharge);
    }

    public void setPreDiscountCharge(@NonNull final Float preDiscountCharge) {
        this.preDiscountCharge = preDiscountCharge;
    }

    @Nullable
    public Float getTotalCharge() {
        return totalCharge;
    }

    @NonNull
    public Optional<Float> totalChargeOpt() {
        return Optional.ofNullable(totalCharge);
    }

    public void setTotalCharge(@NonNull final Float totalCharge) {
        this.totalCharge = totalCharge;
    }

    @Nullable
    public String getPreDiscountChargeCurrency() {
        return preDiscountChargeCurrency;
    }

    @NonNull
    public Optional<String> preDiscountChargeCurrencyOpt() {
        if (isBlank(preDiscountChargeCurrency)) {
            return Optional.empty();
        }

        return Optional.of(preDiscountChargeCurrency);
    }

    public void setPreDiscountChargeCurrency(
            @NonNull final String preDiscountChargeCurrency
    ) {
        this.preDiscountChargeCurrency = preDiscountChargeCurrency;
    }

    @Nullable
    public String getTotalChargeCurrency() {
        return totalChargeCurrency;
    }

    @NonNull
    public Optional<String> totalChargeCurrencyOpt() {
        if (isBlank(totalChargeCurrency)) {
            return Optional.empty();
        }

        return Optional.of(totalChargeCurrency);
    }

    public void setTotalChargeCurrency(
            @NonNull final String totalChargeCurrency
    ) {
        this.totalChargeCurrency = totalChargeCurrency;
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        RentalAgreement rhs = (RentalAgreement) obj;
        return new EqualsBuilder()
                .append(this.id, rhs.id)
                .append(this.toolId, rhs.toolId)
                .append(this.toolCode, rhs.toolCode)
                .append(this.toolType, rhs.toolType)
                .append(this.renterId, rhs.renterId)
                .append(this.renterName, rhs.renterName)
                .append(this.rentalDays, rhs.rentalDays)
                .append(this.checkoutDate, rhs.checkoutDate)
                .append(this.dailyRentalCharge, rhs.dailyRentalCharge)
                .append(this.dailyRentalChargeCurrency, rhs.dailyRentalChargeCurrency)
                .append(this.discountPercent, rhs.discountPercent)
                .append(this.preDiscountCharge, rhs.preDiscountCharge)
                .append(this.state, rhs.state)
                .append(this.dataItem, rhs.dataItem)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("toolId", toolId)
                .append("toolCode", toolCode)
                .append("toolType", toolType)
                .append("renterId", renterId)
                .append("renterName", renterName)
                .append("rentalDays", rentalDays)
                .append("checkoutDate", checkoutDate)
                .append("dailyRentalCharge", dailyRentalCharge)
                .append("dailyRentalChargeCurrency", dailyRentalChargeCurrency)
                .append("discountPercent", discountPercent)
                .append("preDiscountCharge", preDiscountCharge)
                .append("state", state)
                .append("dataItem", dataItem)
                .toString();
    }
}
