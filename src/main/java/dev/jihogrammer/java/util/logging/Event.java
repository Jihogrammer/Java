package dev.jihogrammer.java.util.logging;

import lombok.Getter;

@Getter
public class Event {
    private final Timestamp timestamp;
    private final Thread thread;
    private final Level level;
    private final String name;
    private final String format;
    private final Object[] args;

    public Event(Timestamp timestamp, Thread thread, Level level, String name, String format, Object... args) {
        this.timestamp = timestamp;
        this.thread = thread;
        this.level = level;
        this.name = name;
        this.format = format;
        this.args = args;
    }
}
