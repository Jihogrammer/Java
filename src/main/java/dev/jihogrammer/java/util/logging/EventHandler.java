package dev.jihogrammer.java.util.logging;

import java.util.Collection;
import java.util.EnumSet;

// TODO convert to publisher
public final class EventHandler {
    private final Collection<Appender> appenders;
    private final EnumSet<Level> enabledLevels;

    public EventHandler(Collection<Appender> appenders, EnumSet<Level> enabledLevels) {
        this.appenders = appenders;
        this.enabledLevels = enabledLevels;
    }

    // TODO change method name for publisher
    public void add(final Event event) {
        if (enabledLevels.contains(event.getLevel())) {
            appenders.forEach(appender -> appender.accept(event));
        }
    }
}
