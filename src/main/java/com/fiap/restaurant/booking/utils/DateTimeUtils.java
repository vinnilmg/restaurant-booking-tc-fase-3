package com.fiap.restaurant.booking.utils;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {
    private static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm";

    private DateTimeUtils() {
    }

    public static LocalDateTime toLocalDateTime(final String dateTime) {
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(DEFAULT_PATTERN));
    }

    public static LocalTime toLocalTime(final String dateTime) {
        return LocalTime.parse(dateTime, DateTimeFormatter.ofPattern("HH:mm"));
    }

    public static String toDefaultFormat(final LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern(DEFAULT_PATTERN));
    }

    public static boolean isFutureDate(final LocalDateTime dateTime) {
        return dateTime.isAfter(LocalDateTime.now());
    }
}
