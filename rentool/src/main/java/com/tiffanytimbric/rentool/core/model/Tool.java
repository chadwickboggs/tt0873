package com.tiffanytimbric.rentool.core.model;

import jakarta.persistence.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.io.Serial;
import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

@Entity
@Table(name = "tool")
public class Tool implements Serializable {

    @Serial
    private static final long serialVersionUID = 7128796278528878225L;

    @Id
    private UUID id;
    private String code;
    @JoinColumn(
            table = "ToolType",
            name = "type",
            referencedColumnName = "name",
            nullable = false
    )
    private String type;
    @JoinColumn(
            table = "Brand",
            name = "brand",
            referencedColumnName = "name",
            nullable = false
    )
    private String brand;
    private Float dailyCharge;
    private Boolean weekdaysFree;
    private Boolean weekendsFree;
    private Boolean holidaysFree;

    public Tool() {
    }

    public Tool(
            @NonNull final UUID id,
            @NonNull final String code,
            @NonNull final String type,
            @NonNull final String brand,
            @NonNull final Float dailyCharge,
            @NonNull final Boolean weekdaysFree,
            @NonNull final Boolean weekendsFree,
            @NonNull final Boolean holidaysFree
    ) {
        this.id = id;
        this.code = code;
        this.type = type;
        this.brand = brand;
        this.dailyCharge = dailyCharge;
        this.weekdaysFree = weekdaysFree;
        this.weekendsFree = weekendsFree;
        this.holidaysFree = holidaysFree;
    }

    @Nullable
    public UUID getId() {
        return id;
    }

    @NonNull
    public Optional<UUID> idOpt() {
        return Optional.ofNullable(id);
    }

    public void setId(@NonNull final UUID id) {
        this.id = id;
    }

    @Nullable
    public String getCode() {
        return code;
    }

    @NonNull
    public Optional<String> codeOpt() {
        return Optional.ofNullable(code);
    }

    public void setCode(@NonNull final String name) {
        this.code = name;
    }

    @Nullable
    public String getType() {
        return type;
    }

    @NonNull
    public Optional<String> typeOpt() {
        return Optional.ofNullable(type);
    }

    public void setType(@NonNull final String type) {
        this.type = type;
    }

    @Nullable
    public String getBrand() {
        return brand;
    }

    @NonNull
    public Optional<String> brandOpt() {
        return Optional.ofNullable(brand);
    }

    public void setBrand(@NonNull final String brand) {
        this.brand = brand;
    }

    @Nullable
    public Float getDailyCharge() {
        return dailyCharge;
    }

    @NonNull
    public Optional<Float> dailyChargeOpt() {
        return Optional.ofNullable(dailyCharge);
    }

    public void setDailyCharge(@NonNull final Float dailyCharge) {
        this.dailyCharge = dailyCharge;
    }

    @Nullable
    public Boolean getWeekdaysFree() {
        return weekdaysFree;
    }

    @NonNull
    public Optional<Boolean> weekdaysFreeOpt() {
        return Optional.ofNullable(weekdaysFree);
    }

    public void setWeekdaysFree(@NonNull final Boolean weekdayssFree) {
        this.weekdaysFree = weekdayssFree;
    }

    @Nullable
    public Boolean getWeekendsFree() {
        return weekendsFree;
    }

    @NonNull
    public Optional<Boolean> weekendsFreeOpt() {
        return Optional.ofNullable(weekendsFree);
    }

    public void setWeekendsFree(@NonNull final Boolean weekendsFree) {
        this.weekendsFree = weekendsFree;
    }

    @Nullable
    public Boolean getHolidaysFree() {
        return holidaysFree;
    }

    @NonNull
    public Optional<Boolean> holidaysFreeOpt() {
        return Optional.ofNullable(holidaysFree);
    }

    public void setHolidaysFree(@NonNull final Boolean holidaysFree) {
        this.holidaysFree = holidaysFree;
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
        Tool rhs = (Tool) obj;
        return new EqualsBuilder()
                .append(this.id, rhs.id)
                .append(this.code, rhs.code)
                .append(this.type, rhs.type)
                .append(this.brand, rhs.brand)
                .append(this.dailyCharge, rhs.dailyCharge)
                .append(this.weekdaysFree, rhs.weekdaysFree)
                .append(this.weekendsFree, rhs.weekendsFree)
                .append(this.holidaysFree, rhs.holidaysFree)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .toHashCode();
    }

    @Override
    @NonNull
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", code)
                .append("type", type)
                .append("brand", brand)
                .append("dailyCharge", dailyCharge)
                .append("weekdaysFree", weekdaysFree)
                .append("weekendsFree", weekendsFree)
                .append("holidaysFree", holidaysFree)
                .toString();
    }

}
