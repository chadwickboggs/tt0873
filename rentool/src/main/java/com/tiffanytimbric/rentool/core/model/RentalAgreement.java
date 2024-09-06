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
import java.util.UUID;

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

    @JoinColumn(
            table = "User",
            name = "renter_id",
            referencedColumnName = "id",
            nullable = false
    )
//    @NotBlank
    private String renterId;

    @Range(
            min = 1,
            message = "The rental days value must be greater than 1."
    )
    private Integer rentalDays = 1;

    @JsonFormat(pattern = "MM/dd/yy")
    @DateTimeFormat(pattern = "MM/dd/yy")
    private LocalDate checkoutDate;

    private Float dailyRentalCharge;

    @Range(
            min = 0, max = 100,
            message = "The discount percentage value must be between 0 and 100."
    )
    private Integer discountPercent = 0;

    private String state = "Proposed";
    private String dataItem;

    @Transient private String toolCode;
    @Transient private String toolType;
    @Transient private String renterName;

    @JsonFormat(pattern = "MM/dd/yy")
    @DateTimeFormat(pattern = "MM/dd/yy")
    @Transient private LocalDate dueDate;

    @Transient private String dailyRentalChargeCurrency;
    @Transient private Integer chargeDays;
    @Transient private Float preDiscountCharge;
    @Transient private String preDiscountChargeCurrency;
    @Transient private Float finalCharge;
    @Transient private String finalChargeCurrency;

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
                id, toolId, renterId, rentalDays, checkoutDate, dailyRentalCharge, discountPercent,
                null, null, null, null, null, null, null, null, null, null
        );
    }

    public RentalAgreement(
            @NonNull final UUID id,
            @NonNull final String toolId,
            @NonNull final String renterId,
            @NonNull final Integer rentalDays,
            @NonNull final LocalDate checkoutDate,
            @NonNull final Float dailyRentalCharge,
            @NonNull final Integer discountPercent,
            @Nullable final String toolCode,
            @Nullable final String toolType,
            @Nullable final String renterName,
            @Nullable final LocalDate dueDate,
            @Nullable final String dailyRentalChargeCurrency,
            @Nullable final Integer chargeDays,
            @Nullable final Float preDiscountCharge,
            @Nullable final String preDiscountChargeCurrency,
            @Nullable final Float finalCharge,
            @Nullable final String finalChargeCurrency
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
        this.dueDate = dueDate;
        this.dailyRentalChargeCurrency = dailyRentalChargeCurrency;
        this.chargeDays = chargeDays;
        this.preDiscountCharge = preDiscountCharge;
        this.preDiscountChargeCurrency = preDiscountChargeCurrency;
        this.finalCharge = finalCharge;
        this.finalChargeCurrency = finalChargeCurrency;
        this.discountPercent = discountPercent;
    }

    @Nullable
    public UUID getId() {
        return id;
    }

    @Nullable
    public String getToolId() {
        return toolId;
    }

    public void setToolId(@NonNull final String toolOneId) {
        this.toolId = toolOneId;
    }

    @Nullable
    public String getRenterId() {
        return renterId;
    }

    public void setRenterId(@NonNull final String renterId) {
        this.renterId = renterId;
    }

    public void setId(@NonNull final UUID name) {
        this.id = name;
    }

    @Nullable
    public Integer getRentalDays() {
        return rentalDays;
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

    public void setCheckoutDate(
            @Nullable final LocalDate checkoutDate
    ) {
        this.checkoutDate = checkoutDate;
    }

    @Nullable
    public Float getDailyRentalCharge() {
        return dailyRentalCharge;
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

    public void setDiscountPercent(
            @Nullable final Integer discountPercent
    ) {
        this.discountPercent = discountPercent;
    }

    @NonNull
    public String getState() {
        return state;
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

    public void setDataItem(
            @Nullable final String dataItem
    ) {
        this.dataItem = dataItem;
    }

    @Nullable
    public String getToolCode() {
        return toolCode;
    }

    public void setToolCode(@NonNull final String toolCode) {
        this.toolCode = toolCode;
    }

    @Nullable
    public String getToolType() {
        return toolType;
    }

    public void setToolType(@NonNull final String toolType) {
        this.toolType = toolType;
    }

    @Nullable
    public String getRenterName() {
        return renterName;
    }

    public void setRenterName(@NonNull final String renterName) {
        this.renterName = renterName;
    }

    @Nullable
    public String getDailyRentalChargeCurrency() {
        return dailyRentalChargeCurrency;
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

    public void setPreDiscountCharge(@NonNull final Float preDiscountCharge) {
        this.preDiscountCharge = preDiscountCharge;
    }

    @Nullable
    public String getPreDiscountChargeCurrency() {
        return preDiscountChargeCurrency;
    }

    public void setPreDiscountChargeCurrency(
            @NonNull final String preDiscountChargeCurrency
    ) {
        this.preDiscountChargeCurrency = preDiscountChargeCurrency;
    }

    @Nullable
    public Float getFinalCharge() {
        return finalCharge;
    }

    public void setFinalCharge(@NonNull final Float finalCharge) {
        this.finalCharge = finalCharge;
    }

    @Nullable
    public String getFinalChargeCurrency() {
        return finalChargeCurrency;
    }

    public void setFinalChargeCurrency(
            @NonNull final String finalChargeCurrency
    ) {
        this.finalChargeCurrency = finalChargeCurrency;
    }

    @Nullable
    public Integer getChargeDays() {
        return chargeDays;
    }

    public void setChargeDays(@NonNull final Integer chargeDays) {
        this.chargeDays = chargeDays;
    }

    @Nullable
    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(@NonNull final LocalDate dueDate) {
        this.dueDate = dueDate;
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
                .append(this.chargeDays, rhs.chargeDays)
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
                .append("chargeDays", chargeDays)
                .append("state", state)
                .append("dataItem", dataItem)
                .toString();
    }
}
