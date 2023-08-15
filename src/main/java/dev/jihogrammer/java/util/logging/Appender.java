package dev.jihogrammer.java.util.logging;

// TODO convert to Subscriber
public interface Appender {
    void accept(Event event);
}
