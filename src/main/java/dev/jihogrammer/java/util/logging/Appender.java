package dev.jihogrammer.java.util.logging;

public interface Appender {
    void accept(Event event);
}
