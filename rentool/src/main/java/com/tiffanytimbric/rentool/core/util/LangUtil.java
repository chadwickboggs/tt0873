package com.tiffanytimbric.rentool.core.util;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.text.NumberFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.Locale;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.isBlank;

public final class LangUtil {

    private LangUtil() {
    }

    @Nonnull
    public static <T> Optional<T> opt(
            @Nullable final T value
    ) {
        return Optional.ofNullable(value);
    }

    public static boolean isWeekday(
            @Nonnull final LocalDate date
    ) {
        if (date == null) {
            throw new IllegalArgumentException(
                    "Parameter `date` must be non-null."
            );
        }

        return !isWeekend(date);
    }

    public static boolean isWeekend(
            @Nonnull final LocalDate date
    ) {
        if (date == null) {
            throw new IllegalArgumentException(
                    "Parameter `date` must be non-null."
            );
        }

        final DayOfWeek day = DayOfWeek.of(
                date.get(ChronoField.DAY_OF_WEEK)
        );

        return day == DayOfWeek.SUNDAY || day == DayOfWeek.SATURDAY;
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
