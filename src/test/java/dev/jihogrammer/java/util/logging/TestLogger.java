package dev.jihogrammer.java.util.logging;

import dev.jihogrammer.java.util.reflection.ClassFinder;
import dev.jihogrammer.java.util.reflection.FieldFinder;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.springframework.boot.logging.LogLevel;

import java.lang.reflect.Modifier;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Consumer;

// TODO 로깅에서 제외할 패키지 받을 수 있게 설정

// TODO `ConsoleLogger` 추상 클래스 혹은 인터페이스 작성
public final class TestLogger implements Logger {
    // TODO `LogFormatter`로 이동
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
    // TODO `ConsoleLogger` 추상 클래스 혹은 인터페이스로 이동
    private static final Map<LogLevel, String> COLOR_MAP = new EnumMap<>(Map.of(
            LogLevel.TRACE, "\u001B[0;90m",
            LogLevel.DEBUG, "\u001B[32m",
            LogLevel.INFO, "\u001B[0m",
            LogLevel.WARN, "\u001B[33m",
            LogLevel.ERROR, "\u001B[31m",
            LogLevel.FATAL, "\u001B[1;91m",
            LogLevel.OFF, "\u001B[0m"
    ));
    // TODO `ConsoleLogger` 추상 클래스 혹은 인터페이스로 이동
    private final static Set<LogLevel> ENABLED_LEVELS = EnumSet.noneOf(LogLevel.class);
    // TODO `LogFormatter`로 이동
    private final static int MAX_NAME_LENGTH;
    private final String name;

    static {
        MAX_NAME_LENGTH = new ClassFinder().findAllClasses().stream()
                .filter(clazz -> new FieldFinder().hasFieldInClass(clazz, "log"))
                .filter(clazz -> !Modifier.isFinal(clazz.getModifiers()))
                .mapToInt(clazz -> clazz.getSimpleName().length())
                .max()
                .orElse(20);

        Consumer<LogLevel> logLevelAdder = (logLevel) -> {
            for (LogLevel level : LogLevel.values()) {
                if (level.ordinal() >= logLevel.ordinal()) {
                    ENABLED_LEVELS.add(level);
                }
            }
        };

        try {
            System.out.print(COLOR_MAP.get(LogLevel.FATAL));
            LogLevel logLevel = Enum.valueOf(LogLevel.class, System.getenv("logging.level").toUpperCase());
            logLevelAdder.accept(logLevel);
            System.out.println("> logging.level is '" + logLevel + "' by system environment");
        } catch (Throwable failedToLoadFromSystemEnvironment) {
            try {
                LogLevel logLevel = Enum.valueOf(LogLevel.class, System.getProperty("logging.level").toUpperCase());
                logLevelAdder.accept(logLevel);
                System.out.println("> logging.level is '" + logLevel + "' by system property");
            } catch (Throwable failedToLoadFromSystemProperties) {
                logLevelAdder.accept(LogLevel.INFO);
                System.out.println("> logging.level is '" + LogLevel.INFO + "' (default)");
            }
        } finally {
            System.out.print(COLOR_MAP.get(LogLevel.OFF));
        }
    }

    public TestLogger(Class<?> clazz) {
        this.name = clazz.getSimpleName();

        Class<?> superclass = clazz.getSuperclass();
        if (superclass == null || !superclass.equals(Object.class)) {
            print(LogLevel.WARN, "Assign it as an interface or super class if you can.");
        }
    }

    @Override
    public String getName() {
        return name;
    }

    private void print(final LogLevel level, final String message, final Object... args) {
        if (ENABLED_LEVELS.contains(level)) {
            System.out.println(COLOR_MAP.get(level) + format(level, message, args));
        }
    }

    // TODO `LogFormatter`로 이동
    private String format(final LogLevel level, final String message, final Object[] args) {
        return formatTimestamp() + ' ' +
                '[' + formatWithLength(Thread.currentThread().getName(), 19) + ']' + ' ' +
                '[' + formatWithLength(level.name(), 5) + ']' + ' ' +
                '[' + formatWithLength(name, MAX_NAME_LENGTH) + ']' + ' ' +
                formatMessageBody(message, args);
    }

    // TODO `LogFormatter`로 이동
    private String formatMessageBody(String message, Object[] args) {
        if (message.contains("{}")) {
            StringBuilder sb = new StringBuilder();
            String[] tokens = message.split("\\{}");
            for (int index = 0; index < Math.min(tokens.length, args.length); index++) {
                sb.append(tokens[index]).append(args[index].toString());
            }
            return sb.toString();
        }
        return message;
    }

    // TODO `LogFormatter`로 이동
    private String formatWithLength(final String str, final int length) {
        return String.format("%" + length + "s", str.substring(Math.max(0, str.length() - length)));
    }

    // TODO `LogFormatter`로 이동
    private String formatTimestamp() {
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
    public void warn(final String msg) {
        print(LogLevel.WARN, msg);
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
        print(LogLevel.ERROR, format, arg);
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
