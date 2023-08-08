package dev.jihogrammer.java.util.logging;

import org.slf4j.Logger;
import org.slf4j.Marker;
import org.springframework.boot.logging.LogLevel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Consumer;

public final class TestLogger implements Logger {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
    private static final Map<LogLevel, String> COLOR_MAP = new EnumMap<>(LogLevel.class);
    private final static Set<LogLevel> ENABLED_LEVELS = EnumSet.noneOf(LogLevel.class);
    private final String name;

    static {
        COLOR_MAP.put(LogLevel.TRACE, "\u001B[0;90m");
        COLOR_MAP.put(LogLevel.DEBUG, "\u001B[32m");
        COLOR_MAP.put(LogLevel.INFO, "\u001B[0m");
        COLOR_MAP.put(LogLevel.WARN, "\u001B[33m");
        COLOR_MAP.put(LogLevel.ERROR, "\u001B[31m");
        COLOR_MAP.put(LogLevel.FATAL, "\u001B[1;91m");
        COLOR_MAP.put(LogLevel.OFF, "\u001B[0m");

        Consumer<LogLevel> logLevelAdder = (logLevel) -> {
            for (LogLevel level : LogLevel.values()) {
                if (level.ordinal() >= logLevel.ordinal()) {
                    ENABLED_LEVELS.add(level);
                }
            }
        };

        Consumer<String> prettyPrinter = (message) -> {
            char[] line = new char[message.length()];
            Arrays.fill(line, '=');
            System.err.println();
            System.err.println(line);
            System.err.println(message);
            System.err.println(line);
            System.err.println();
        };

        try {
            LogLevel logLevel = Enum.valueOf(LogLevel.class, System.getenv("logging.level").toUpperCase());
            logLevelAdder.accept(logLevel);
            prettyPrinter.accept("environment logging.level: " + logLevel);
        } catch (Throwable failedToLoadFromEnv) {
            try {
                LogLevel logLevel = Enum.valueOf(LogLevel.class, System.getProperty("logging.level").toUpperCase());
                logLevelAdder.accept(logLevel);
                prettyPrinter.accept("property logging.level: " + logLevel);
            } catch (Throwable failedToLoadFromProp) {
                logLevelAdder.accept(LogLevel.INFO);
                prettyPrinter.accept("default logging.level: " + LogLevel.INFO);
            }
        }
    }

    public TestLogger(Class<?> clazz) {
        this(clazz.getSimpleName());
    }
    public TestLogger(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    private void print(final LogLevel level, final String message, final Object... args) {
        if (ENABLED_LEVELS.contains(level)) {
            System.out.print(COLOR_MAP.get(level));
            System.out.println(format(level, message, args));
            System.out.print(COLOR_MAP.get(LogLevel.OFF));
        }
    }

    // TODO `TestLogger`를 참조하는 객체 리스트를 만들고, Math.max(20, objectNames.max()) 처리 가능한지 확인 (static reflection)
    private String format(final LogLevel level, final String message, final Object[] args) {
        return timestamp() + ' ' +
                '[' + formatWithLength(Thread.currentThread().getName(), 19) + ']' + ' ' +
                '[' + formatWithLength(name, 15) + ']' + ' ' +
                formatWithLength(level.name(), 5) + " - " + makeMessage(message, args);
    }

    private String makeMessage(String message, Object[] args) {
        if (message.contains("{}")) {
            StringBuilder sb = new StringBuilder();
            String[] tokens = message.split("\\{}");
            int index = 0;
            for (; index < Math.min(tokens.length, args.length); index++) {
                sb.append(tokens[index]).append(args[index].toString());
            }
            while (index < tokens.length) {
                sb.append(tokens[index++]);
            }
            return sb.toString();
        }
        return message;
    }

    private String formatWithLength(final String str, final int length) {
        return String.format("%" + length + "s", str.substring(Math.max(0, str.length() - length)));
    }

    private String timestamp() {
        return LocalDateTime.now().format(DATE_TIME_FORMATTER);
    }

    @Override
    public boolean isTraceEnabled() {
        throw new RuntimeException("not implemented");
    }

    @Override
    public void trace(final String msg) {
        print(LogLevel.TRACE, msg);
    }

    @Override
    public void trace(String format, Object arg) {
        print(LogLevel.TRACE, format, arg);
    }

    @Override
    public void trace(final String format, final Object arg1, final Object arg2) {
        print(LogLevel.TRACE, format, arg1, arg2);
    }

    @Override
    public void trace(String format, Object... arguments) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public void trace(String msg, Throwable t) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public boolean isTraceEnabled(Marker marker) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public void trace(Marker marker, String msg) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public void trace(Marker marker, String format, Object arg) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public void trace(Marker marker, String format, Object arg1, Object arg2) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public void trace(Marker marker, String format, Object... argArray) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public void trace(Marker marker, String msg, Throwable t) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public boolean isDebugEnabled() {
        throw new RuntimeException("not implemented");
    }

    @Override
    public void debug(final String msg) {
        print(LogLevel.DEBUG, msg);
    }

    @Override
    public void debug(String format, Object arg) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public void debug(final String format, Object arg1, Object arg2) {
        print(LogLevel.DEBUG, format, arg1, arg2);
    }

    @Override
    public void debug(String format, Object... arguments) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public void debug(String msg, Throwable t) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public boolean isDebugEnabled(Marker marker) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public void debug(Marker marker, String msg) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public void debug(Marker marker, String format, Object arg) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public void debug(Marker marker, String format, Object arg1, Object arg2) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public void debug(Marker marker, String format, Object... arguments) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public void debug(Marker marker, String msg, Throwable t) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public boolean isInfoEnabled() {
        throw new RuntimeException("not implemented");
    }

    @Override
    public void info(final String msg) {
        print(LogLevel.INFO, msg);
    }

    @Override
    public void info(final String format, final Object arg) {
        print(LogLevel.INFO, format, arg);
    }

    @Override
    public void info(final String format, final Object arg1, final Object arg2) {
        print(LogLevel.INFO, format, arg1, arg2);
    }

    @Override
    public void info(String format, Object... arguments) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public void info(String msg, Throwable t) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public boolean isInfoEnabled(Marker marker) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public void info(Marker marker, String msg) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public void info(Marker marker, String format, Object arg) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public void info(Marker marker, String format, Object arg1, Object arg2) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public void info(Marker marker, String format, Object... arguments) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public void info(Marker marker, String msg, Throwable t) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public boolean isWarnEnabled() {
        throw new RuntimeException("not implemented");
    }

    @Override
    public void warn(String msg) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public void warn(String format, Object arg) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public void warn(String format, Object... arguments) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public void warn(String format, Object arg1, Object arg2) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public void warn(String msg, Throwable t) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public boolean isWarnEnabled(Marker marker) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public void warn(Marker marker, String msg) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public void warn(Marker marker, String format, Object arg) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public void warn(Marker marker, String format, Object arg1, Object arg2) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public void warn(Marker marker, String format, Object... arguments) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public void warn(Marker marker, String msg, Throwable t) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public boolean isErrorEnabled() {
        throw new RuntimeException("not implemented");
    }

    @Override
    public void error(String msg) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public void error(String format, Object arg) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public void error(String format, Object arg1, Object arg2) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public void error(String format, Object... arguments) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public void error(String msg, Throwable t) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public boolean isErrorEnabled(Marker marker) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public void error(Marker marker, String msg) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public void error(Marker marker, String format, Object arg) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public void error(Marker marker, String format, Object arg1, Object arg2) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public void error(Marker marker, String format, Object... arguments) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public void error(Marker marker, String msg, Throwable t) {
        throw new RuntimeException("not implemented");
    }
}
