package com.tiffanytimbric.rentool.core.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.io.Serial;
import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Entity
@Table(name = "user")
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1686949015034332965L;

    @Id
    private UUID id;
    private String name;
    private String photoUrl;
    private int balance;
/*
    @OneToMany
    private Set<Tool> tools;
*/

    public User() {
    }

    public User(
            @NonNull final UUID id,
            @NonNull final String name,
            @NonNull final String photoUrl,
            final int balance/*,
            @NonNull final Set<Tool> tools*/
    ) {
        this.id = id;
        this.name = name;
        this.photoUrl = photoUrl;
        this.balance = balance;
//        this.tools = tools;
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
    public String getName() {
        return name;
    }

    @NonNull
    public Optional<String> nameOpt() {
        return Optional.ofNullable(name);
    }

    public void setName(@NonNull final String name) {
        this.name = name;
    }

    @Nullable
    public String getPhotoUrl() {
        return photoUrl;
    }

    @NonNull
    public Optional<String> photoUrlOpt() {
        if (isBlank(photoUrl)) {
            return Optional.empty();
        }

        return Optional.ofNullable(photoUrl);
    }

    public void setPhotoUrl(@NonNull final String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(final int balance) {
        this.balance = balance;
    }

/*
    @Nullable
    public Set<Tool> getTools() {
        return tools;
    }

    @NonNull
    public Set<Tool> toolsOpt() {
        if (CollectionUtils.isEmpty(tools)) {
            return Set.of();
        }

        return tools;
    }

    public void setTools(@NonNull final Set<Tool> tools) {
        this.tools = tools;
    }
*/

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
        User rhs = (User) obj;
        return new EqualsBuilder()
                .append(this.id, rhs.id)
                .append(this.name, rhs.name)
                .append(this.photoUrl, rhs.photoUrl)
                .append(this.balance, rhs.balance)
//                .append(this.tools, rhs.tools)
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
                .append("name", name)
                .append("photoUrl", photoUrl)
                .append("balance", balance)
//                .append("tools", tools)
                .toString();
    }
}
