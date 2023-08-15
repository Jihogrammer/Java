package dev.jihogrammer.java.util.logging;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class EventHandler {
    private final Collection<Appender> appenders;

    public EventHandler(Appender... appenders) {
        this.appenders = Stream.of(appenders).filter(Objects::nonNull).collect(Collectors.toSet());
        assert !this.appenders.isEmpty();
    }

    public void add(Event event) {
        appenders.forEach(appender -> appender.accept(event));
    }
}
