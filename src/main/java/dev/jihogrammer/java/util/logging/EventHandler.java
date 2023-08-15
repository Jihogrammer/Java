package dev.jihogrammer.java.util.logging;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// TODO convert to publisher
public final class EventHandler {
    private final Collection<Appender> appenders;

    public EventHandler(Appender... appenders) {
        this.appenders = Stream.of(appenders).filter(Objects::nonNull).collect(Collectors.toSet());
        assert !this.appenders.isEmpty();
    }

    // TODO change method name for publisher
    public void add(Event event) {
        appenders.forEach(appender -> appender.accept(event));
    }
}
