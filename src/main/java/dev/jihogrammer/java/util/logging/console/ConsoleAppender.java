package dev.jihogrammer.java.util.logging.console;

import dev.jihogrammer.java.util.logging.*;

public final class ConsoleAppender extends BaseAppender {
    private static final String GRAY = "\u001B[0;90m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RED = "\u001B[31m";
    private static final String NORMAL = "\u001B[0m";

    private final Formatter formatter;

    public ConsoleAppender() {
        this(DEFAULT_FORMATTER);
    }

    public ConsoleAppender(Formatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public void accept(final Event event) {
        System.out.println(coloring(event));
    }

    private String coloring(final Event event) {
        String color = switch (event.getLevel()) {
            case TRACE -> GRAY;
            case DEBUG -> GREEN;
            case WARN -> YELLOW;
            case ERROR -> RED;
            default -> NORMAL;
        };
        return color + String.join("\n" + color, formatter.format(event).split("\n"));
    }
}
