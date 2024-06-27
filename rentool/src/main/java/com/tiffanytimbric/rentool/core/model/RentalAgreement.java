package com.tiffanytimbric.rentool.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Entity
@Table(name = "rental_agrmnt")
public class RentalAgreement implements Serializable, Cloneable {

    @Serial
    private static final long serialVersionUID = 7767596765816499575L;

    @Id
    private UUID id;
    @JoinColumn(
            name = "tool_id",
            nullable = false,
            referencedColumnName = "id"
    )
    @JsonProperty("tool_id")
    private UUID toolId;
    private Integer rentalDays;
    private LocalDate checkoutDate;
//    private LocalDate dueDate;  // TODO: Add method which calculates this value.
    private Float dailyRentalCharge;
//    private Integer chargeDays;  // TODO: Add method which calculates this value.
//    private Float preDiscountCharge;  // TODO: Add method which calculates this value.
    private Float discountPercent;
//    private Float discountAmount;  // TODO: Add method which calculates this value.
//    private Float finalCharge;  // TODO: Add method which calculates this value.

    public RentalAgreement() {
    }

    public RentalAgreement(
            @NonNull final UUID id,
            @NonNull final UUID toolId,
            @NonNull final Integer rentalDays,
            @NonNull final LocalDate checkoutDate,
            @NonNull final Float dailyRentalCharge,
            @NonNull final Float discountPercent
    ) {
        this.id = id;
        this.toolId = toolId;
        this.rentalDays = rentalDays;
        this.checkoutDate = checkoutDate;
        this.dailyRentalCharge = dailyRentalCharge;
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
    public UUID getToolId() {
        return toolId;
    }

    @NonNull
    public Optional<UUID> toolIdOpt() {
        return Optional.ofNullable(toolId);
    }

    public void setToolId(@NonNull final UUID toolOneId) {
        this.toolId = toolOneId;
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
    public Float getDiscountPercent() {
        return discountPercent;
    }

    @NonNull
    public Optional<Float> discountPercentOpt() {
        return Optional.ofNullable(discountPercent);
    }

    public void setDiscountPercent(
            @Nullable final Float discountPercent
    ) {
        this.discountPercent = discountPercent;
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
                .append(this.rentalDays, rhs.rentalDays)
                .append(this.checkoutDate, rhs.checkoutDate)
                .append(this.dailyRentalCharge, rhs.dailyRentalCharge)
                .append(this.discountPercent, rhs.discountPercent)
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
                .append("rentalDays", rentalDays)
                .append("checkoutDate", checkoutDate)
                .append("dailyRentalCharge", dailyRentalCharge)
                .append("discountPercent", discountPercent)
                .toString();
    }
}
