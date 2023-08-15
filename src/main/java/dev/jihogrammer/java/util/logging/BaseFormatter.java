package dev.jihogrammer.java.util.logging;

import dev.jihogrammer.java.util.logging.Event;
import dev.jihogrammer.java.util.logging.Formatter;

import java.time.format.DateTimeFormatter;

public class BaseFormatter implements Formatter {
    private final String logFormat;
    private final DateTimeFormatter dateTimeFormatter;

    public BaseFormatter(final String logFormat, final DateTimeFormatter dateTimeFormatter) {
        this.logFormat = logFormat;
        this.dateTimeFormatter = dateTimeFormatter;
    }

    @Override
    public String format(final Event event) {
        return logFormat.formatted(
                        event.getTimestamp().format(dateTimeFormatter),
                        event.getThread().getName(),
                        event.getLevel(),
                        event.getName(),
                        body(event.getFormat(), event.getArgs())
                );
    }

    private String body(final String format, final Object[] args) {
        if (format.contains("{}")) {
            StringBuilder sb = new StringBuilder();
            String[] tokens = format.split("\\{}");

            int index = 0;
            while (index < tokens.length) {
                sb.append(tokens[index]);
                if (index < args.length) {
                    sb.append(args[index].toString());
                }
                index++;
            }

            return sb.toString();
        }
        return format;
    }
}
