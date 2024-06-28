package com.tiffanytimbric.rentool.core.model;

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
import java.util.Date;
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
            name = "tool_id",
            nullable = false,
            referencedColumnName = "id"
    )
    private String toolId;
    private Integer rentalDays;
    private Date checkoutDate;
//    private LocalDate dueDate;  // TODO: Add method which calculates this value.
    private Float dailyRentalCharge;
//    private Integer chargeDays;  // TODO: Add method which calculates this value.
//    private Float preDiscountCharge;  // TODO: Add method which calculates this value.
    private Float discountPercent;
//    private Float discountAmount;  // TODO: Add method which calculates this value.
//    private Float finalCharge;  // TODO: Add method which calculates this value.
    private String state = "Proposed";
    private String dataItem;

    public RentalAgreement() {
    }

    public RentalAgreement(
            @NonNull final UUID id,
            @NonNull final String toolId,
            @NonNull final Integer rentalDays,
            @NonNull final Date checkoutDate,
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
    public Date getCheckoutDate() {
        return checkoutDate;
    }

    @NonNull
    public Optional<Date> checkoutDateOpt() {
        return Optional.ofNullable(checkoutDate);
    }

    public void setCheckoutDate(
            @Nullable final Date checkoutDate
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
                .append("rentalDays", rentalDays)
                .append("checkoutDate", checkoutDate)
                .append("dailyRentalCharge", dailyRentalCharge)
                .append("discountPercent", discountPercent)
                .append("state", state)
                .append("dataItem", dataItem)
                .toString();
    }
}
