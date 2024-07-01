package com.tiffanytimbric.rentool.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Optional;

@Entity
@Table(name = "holiday")
public class Holiday implements Serializable {

    @Serial
    private static final long serialVersionUID = 3773838143746346242L;

    @Id
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dayDate;

    @NotBlank
    private String name;

    public Holiday() {
    }

    public Holiday(
            @NonNull final LocalDate dayDate,
            @NonNull final String name
    ) {
        this.dayDate = dayDate;
        this.name = name;
    }

    @Nullable
    public String getName() {
        return name;
    }

    @NonNull
    public Optional<String> nameOpt() {
        if (StringUtils.isBlank(name)) {
            return Optional.empty();
        }

        return Optional.of(name);
    }

    public void setName(@NonNull final String name) {
        this.name = name;
    }

    @Nullable
    public LocalDate getDayDate() {
        return dayDate;
    }

    @NonNull
    public Optional<LocalDate> dayDateOpt() {
        return Optional.ofNullable(dayDate);
    }

    public void setDayDate(@NonNull final LocalDate dayDate) {
        this.dayDate = dayDate;
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
        Holiday rhs = (Holiday) obj;
        return new EqualsBuilder()
                .append(this.dayDate, rhs.dayDate)
                .append(this.name, rhs.name)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(dayDate)
                .toHashCode();
    }

    @Override
    @NonNull
    public String toString() {
        return new ToStringBuilder(this)
                .append("dayDate", dayDate)
                .append("name", name)
                .toString();
    }
}
