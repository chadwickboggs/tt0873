package com.tiffanytimbric.rentool.core.util;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.text.NumberFormat;
import java.util.Locale;

import static org.apache.commons.lang3.StringUtils.isBlank;

public final class LangUtil {

    private LangUtil() {
    }

    @Nonnull
    public static String currency(@Nullable final String amount) {
        if (isBlank(amount)) {
            return NumberFormat.getCurrencyInstance(Locale.US).format(0.0d);
        }

        return currency((Double.valueOf(amount)));
    }

    @Nonnull
    public static String currency(@Nullable final Integer amount) {
        if (amount == null) {
            return NumberFormat.getCurrencyInstance(Locale.US).format(0.0d);
        }

        return currency((Double.valueOf(amount)));
    }

    @Nonnull
    public static String currency(@Nullable final Long amount) {
        if (amount == null) {
            return NumberFormat.getCurrencyInstance(Locale.US).format(0.0d);
        }

        return currency((Double.valueOf(amount)));
    }

    @Nonnull
    public static String currency(@Nullable final Float amount) {
        if (amount == null) {
            return NumberFormat.getCurrencyInstance(Locale.US).format(0.0d);
        }

        return currency((Double.valueOf(amount)));
    }

    @Nonnull
    public static String currency(@Nullable final Double amount) {
        if (amount == null) {
            return NumberFormat.getCurrencyInstance(Locale.US).format(0.0d);
        }

        return NumberFormat.getCurrencyInstance(Locale.US).format(amount);
    }

}
