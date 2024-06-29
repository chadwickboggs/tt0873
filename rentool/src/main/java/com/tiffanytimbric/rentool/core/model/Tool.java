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

    public Tool() {
    }

    public Tool(
            @NonNull final UUID id,
            @NonNull final String code,
            @NonNull final String type,
            @NonNull final String brand
    ) {
        this.id = id;
        this.code = code;
        this.type = type;
        this.brand = brand;
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
                .toString();
    }
}
