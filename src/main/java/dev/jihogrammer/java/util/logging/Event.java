package dev.jihogrammer.java.util.logging;

import java.util.Arrays;

public class Event {
    private final Timestamp timestamp;
    private final Level level;
    private final Thread thread;
    private final String name;
    private final String format;
    private final Object[] args;

    public Event(Level level, Thread thread, String name, String format, Object... args) {
        this.timestamp = Timestamp.now();
        this.level = level;
        this.thread = thread;
        this.name = name;
        this.format = format;
        this.args = args;
    }

    @Override
    public String toString() {
        return "%s [%s] [%5.5s] [%s] %s %s".formatted(timestamp, thread.getName(), level, name, format, Arrays.toString(args));
    }
}
