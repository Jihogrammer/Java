package dev.jihogrammer.java.util.logging.console;

import dev.jihogrammer.java.util.logging.LogFormatter;
import org.slf4j.Marker;
import org.slf4j.event.Level;

import java.io.PrintStream;
import java.util.*;
import java.util.stream.Stream;

// TODO 나중에 Appender 추가되면 대거 제거될 예정
public abstract class BaseConsoleLogger implements ConsoleLogger {
    private static final Set<Level> ENABLED_LEVELS = EnumSet.noneOf(Level.class);
    private static final LogFormatter LOG_FORMATTER = new ConsoleLogFormatter();

    static {
        Stream.of(System.getenv("logging.level"), System.getProperty("logging.level"), "info")
                .filter(Objects::nonNull)
                .findFirst()
                .ifPresent(config -> {
                    Level logLevel = Enum.valueOf(Level.class, config.toUpperCase());
                    Arrays.stream(Level.values())
                            .filter(level -> level.ordinal() <= logLevel.ordinal())
                            .forEach(ENABLED_LEVELS::add);
                    System.out.println("> logging.level is assigned to " + logLevel);
                });
    }

    @Override
    public void print(final Level level, final String message, final Object... args) {
        if (isEnabledForLevel(level)) {
            writer().println(coloring(level) + getLogFormatter().format(getName(), level, message, args));
        }
    }

    private PrintStream writer() {
        return System.out;
    }

    @Override
    public LogFormatter getLogFormatter() {
        return LOG_FORMATTER;
    }

    @Override
    public boolean isEnabledForLevel(final Level level) {
        return ENABLED_LEVELS.contains(level);
    }

    // TODO implement targets

    public boolean isTraceEnabled() {
        throw new RuntimeException("not implemented");
    }

    public void trace(final String msg) {
        print(Level.TRACE, msg);
    }

    public void trace(final String format, final Object arg) {
        print(Level.TRACE, format, arg);
    }

    public void trace(final String format, final Object arg1, final Object arg2) {
        print(Level.TRACE, format, arg1, arg2);
    }

    public void trace(String format, Object... arguments) {
        throw new RuntimeException("not implemented");
    }

    public void trace(String msg, Throwable t) {
        throw new RuntimeException("not implemented");
    }

    public boolean isTraceEnabled(Marker marker) {
        throw new RuntimeException("not implemented");
    }

    public void trace(Marker marker, String msg) {
        throw new RuntimeException("not implemented");
    }

    public void trace(Marker marker, String format, Object arg) {
        throw new RuntimeException("not implemented");
    }

    public void trace(Marker marker, String format, Object arg1, Object arg2) {
        throw new RuntimeException("not implemented");
    }

    public void trace(Marker marker, String format, Object... argArray) {
        throw new RuntimeException("not implemented");
    }

    public void trace(Marker marker, String msg, Throwable t) {
        throw new RuntimeException("not implemented");
    }

    public boolean isDebugEnabled() {
        throw new RuntimeException("not implemented");
    }

    public void debug(final String msg) {
        print(Level.DEBUG, msg);
    }

    public void debug(String format, Object arg) {
        throw new RuntimeException("not implemented");
    }

    public void debug(final String format, Object arg1, Object arg2) {
        print(Level.DEBUG, format, arg1, arg2);
    }

    public void debug(String format, Object... arguments) {
        throw new RuntimeException("not implemented");
    }

    public void debug(String msg, Throwable t) {
        throw new RuntimeException("not implemented");
    }

    public boolean isDebugEnabled(Marker marker) {
        throw new RuntimeException("not implemented");
    }

    public void debug(Marker marker, String msg) {
        throw new RuntimeException("not implemented");
    }

    public void debug(Marker marker, String format, Object arg) {
        throw new RuntimeException("not implemented");
    }

    public void debug(Marker marker, String format, Object arg1, Object arg2) {
        throw new RuntimeException("not implemented");
    }

    public void debug(Marker marker, String format, Object... arguments) {
        throw new RuntimeException("not implemented");
    }

    public void debug(Marker marker, String msg, Throwable t) {
        throw new RuntimeException("not implemented");
    }

    public boolean isInfoEnabled() {
        throw new RuntimeException("not implemented");
    }

    public void info(final String msg) {
        print(Level.INFO, msg);
    }

    public void info(final String format, final Object arg) {
        print(Level.INFO, format, arg);
    }

    public void info(final String format, final Object arg1, final Object arg2) {
        print(Level.INFO, format, arg1, arg2);
    }

    public void info(String format, Object... arguments) {
        throw new RuntimeException("not implemented");
    }

    public void info(String msg, Throwable t) {
        throw new RuntimeException("not implemented");
    }

    public boolean isInfoEnabled(Marker marker) {
        throw new RuntimeException("not implemented");
    }

    public void info(Marker marker, String msg) {
        throw new RuntimeException("not implemented");
    }

    public void info(Marker marker, String format, Object arg) {
        throw new RuntimeException("not implemented");
    }

    public void info(Marker marker, String format, Object arg1, Object arg2) {
        throw new RuntimeException("not implemented");
    }

    public void info(Marker marker, String format, Object... arguments) {
        throw new RuntimeException("not implemented");
    }

    public void info(Marker marker, String msg, Throwable t) {
        throw new RuntimeException("not implemented");
    }

    public boolean isWarnEnabled() {
        throw new RuntimeException("not implemented");
    }

    public void warn(final String msg) {
        print(Level.WARN, msg);
    }

    public void warn(String format, Object arg) {
        throw new RuntimeException("not implemented");
    }

    public void warn(String format, Object... arguments) {
        throw new RuntimeException("not implemented");
    }

    public void warn(String format, Object arg1, Object arg2) {
        throw new RuntimeException("not implemented");
    }

    public void warn(String msg, Throwable t) {
        throw new RuntimeException("not implemented");
    }

    public boolean isWarnEnabled(Marker marker) {
        throw new RuntimeException("not implemented");
    }

    public void warn(Marker marker, String msg) {
        throw new RuntimeException("not implemented");
    }

    public void warn(Marker marker, String format, Object arg) {
        throw new RuntimeException("not implemented");
    }

    public void warn(Marker marker, String format, Object arg1, Object arg2) {
        throw new RuntimeException("not implemented");
    }

    public void warn(Marker marker, String format, Object... arguments) {
        throw new RuntimeException("not implemented");
    }

    public void warn(Marker marker, String msg, Throwable t) {
        throw new RuntimeException("not implemented");
    }

    public boolean isErrorEnabled() {
        throw new RuntimeException("not implemented");
    }

    public void error(String msg) {
        throw new RuntimeException("not implemented");
    }

    public void error(String format, Object arg) {
        print(Level.ERROR, format, arg);
    }

    public void error(String format, Object arg1, Object arg2) {
        throw new RuntimeException("not implemented");
    }

    public void error(String format, Object... arguments) {
        throw new RuntimeException("not implemented");
    }

    public void error(String msg, Throwable t) {
        throw new RuntimeException("not implemented");
    }

    public boolean isErrorEnabled(Marker marker) {
        throw new RuntimeException("not implemented");
    }

    public void error(Marker marker, String msg) {
        throw new RuntimeException("not implemented");
    }

    public void error(Marker marker, String format, Object arg) {
        throw new RuntimeException("not implemented");
    }

    public void error(Marker marker, String format, Object arg1, Object arg2) {
        throw new RuntimeException("not implemented");
    }

    public void error(Marker marker, String format, Object... arguments) {
        throw new RuntimeException("not implemented");
    }

    public void error(Marker marker, String msg, Throwable t) {
        throw new RuntimeException("not implemented");
    }
}
