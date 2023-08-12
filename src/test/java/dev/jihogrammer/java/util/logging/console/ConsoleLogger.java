package dev.jihogrammer.java.util.logging.console;

import dev.jihogrammer.java.util.logging.LogFormatter;
import org.slf4j.Logger;
import org.slf4j.event.Level;

public interface ConsoleLogger extends Logger {
    void print(final Level level, final String message, final Object... args);
    LogFormatter getLogFormatter();
    String coloring(Level level);
}
