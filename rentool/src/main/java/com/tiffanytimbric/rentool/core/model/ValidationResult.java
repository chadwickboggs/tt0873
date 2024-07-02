package com.tiffanytimbric.rentool.core.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.logging.log4j.util.Strings;
import org.springframework.lang.NonNull;

import java.io.Serial;
import java.io.Serializable;

public class ValidationResult implements Serializable {

    @Serial
    private static final long serialVersionUID = 2367284122413838347L;

    private boolean valid;

    private String message = Strings.EMPTY;
    public ValidationResult(boolean valid) {
        this.valid = valid;
    }

    public ValidationResult(
            boolean valid,
            @NonNull final String message
    ) {
        this.valid = valid;
        this.message = message;
    }

    public boolean isValid() {
        return valid;
    }

    public String getMessage() {
        return message;
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
        ValidationResult rhs = (ValidationResult) obj;
        return new EqualsBuilder()
                .append(this.valid, rhs.valid)
                .append(this.message, rhs.message)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(valid)
                .append(message)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("valid", valid)
                .append("message", message)
                .toString();
    }
}
