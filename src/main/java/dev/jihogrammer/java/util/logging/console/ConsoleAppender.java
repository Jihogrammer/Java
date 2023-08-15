package dev.jihogrammer.java.util.logging.console;

import dev.jihogrammer.java.util.logging.Appender;
import dev.jihogrammer.java.util.logging.Event;
import dev.jihogrammer.java.util.logging.Formatter;
import dev.jihogrammer.java.util.logging.Level;

public final class ConsoleAppender implements Appender {

    private final Formatter formatter;

    public ConsoleAppender(Formatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public void accept(Event event) {
        System.out.println(coloring(event.getLevel()) + formatter.format(event));
    }

    private String coloring(final Level level) {
        return switch (level) {
            case TRACE -> "\u001B[0;90m";
            case DEBUG -> "\u001B[32m";
            case WARN -> "\u001B[33m";
            case ERROR -> "\u001B[31m";
            default -> "\u001B[0m";
        };
    }
}
