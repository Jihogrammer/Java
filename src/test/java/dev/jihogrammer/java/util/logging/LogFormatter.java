package dev.jihogrammer.java.util.logging;

import org.slf4j.event.Level;

public interface LogFormatter {
    String format(final String name, final Level level, final String message, final Object[] args);
}
