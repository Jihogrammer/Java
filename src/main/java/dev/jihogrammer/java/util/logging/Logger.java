package dev.jihogrammer.java.util.logging;

import java.util.Arrays;
import java.util.List;

public final class Logger {
    private final String name;
    private final EventHandler eventHandler;

    public Logger(Class<?> aClass) {
        this(aClass.getSimpleName());
    }

    public Logger(String name) {
        this.name = name;
        this.eventHandler = LoggerConfig.EVENT_HANDLER;
    }

    private void handleEvent(Event event) {
        eventHandler.add(event);
    }

    public void trace(String format, Object... args) {
        handleEvent(new Event(Timestamp.now(), Thread.currentThread(), Level.TRACE, name, format, args));
    }
    public void debug(String format, Object... args) {
        handleEvent(new Event(Timestamp.now(), Thread.currentThread(), Level.DEBUG, name, format, args));
    }
    public void info(String format, Object... args) {
        handleEvent(new Event(Timestamp.now(), Thread.currentThread(), Level.INFO, name, format, args));
    }
    public void warn(String format, Object... args) {
        handleEvent(new Event(Timestamp.now(), Thread.currentThread(), Level.WARN, name, format, args));
    }
    public void error(String format, Object... args) {
        handleEvent(new Event(Timestamp.now(), Thread.currentThread(), Level.ERROR, name, format, args));
    }
    public void error(String format, Throwable e, Object... args) {
        error(format + "\n" + tenLinesStackTrace(e), args);
    }

    private String tenLinesStackTrace(Throwable e) {
        return String.join("\n\t", Arrays.stream(e.getStackTrace()).limit(10).map(Object::toString).toList());
    }
}
