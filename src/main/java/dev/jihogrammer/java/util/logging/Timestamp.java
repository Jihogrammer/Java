package dev.jihogrammer.java.util.logging;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class Timestamp {
    private static final DateTimeFormatter DEFAULT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    private final LocalDateTime timestamp;

    private Timestamp() {
        timestamp = LocalDateTime.now();
    }

    public static Timestamp now() {
        return new Timestamp();
    }

    public String format(DateTimeFormatter formatter) {
        return formatter.format(timestamp);
    }

    @Override
    public String toString() {
        return format(DEFAULT_DATE_TIME_FORMATTER);
    }
}
