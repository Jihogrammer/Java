package dev.jihogrammer.java.util.logging;

import java.time.format.DateTimeFormatter;

public abstract class BaseAppender implements Appender {
    protected static final Formatter DEFAULT_FORMATTER;

    static {
        String logFormat = "%s [%19.19s] [%5s] %s - %s";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
        DEFAULT_FORMATTER = new SimpleFormatter(logFormat, dateTimeFormatter);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
