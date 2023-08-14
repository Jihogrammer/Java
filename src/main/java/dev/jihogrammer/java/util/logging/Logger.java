package dev.jihogrammer.java.util.logging;

public interface Logger {
    void trace(String message);
    void trace(String format, Object... args);
    void debug(String message);
    void debug(String format, Object... args);
    void info(String message);
    void info(String format, Object... args);
    void warn(String message);
    void warn(String format, Object... args);
    void error(String message);
    void error(String message, Throwable e);
    void error(String format, Object... args);
    void error(String format, Throwable e, Object... args);
}
