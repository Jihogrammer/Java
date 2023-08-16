package dev.jihogrammer.java.util.logging;

import dev.jihogrammer.java.util.logging.console.ConsoleAppender;

import java.util.*;
import java.util.stream.Stream;

public class LoggerConfig {
    public static final EventHandler EVENT_HANDLER;

    static {
        Collection<Appender> appenders = new HashSet<>();
        appenders.add(new ConsoleAppender());
        System.out.println("> appenders: " + appenders);

        EnumSet<Level> enabledLevels = EnumSet.noneOf(Level.class);
        Stream.of(System.getenv("logging.level"), System.getProperty("logging.level"), "info")
                .filter(Objects::nonNull)
                .findFirst()
                .ifPresent(config -> {
                    Level logLevel = Level.of(config);
                    Arrays.stream(Level.values())
                            .filter(level -> level.isGreaterThanEquals(logLevel))
                            .forEach(enabledLevels::add);
                    System.out.println("> logging.level is assigned to " + logLevel);
                });

        EVENT_HANDLER = new EventHandler(appenders, enabledLevels);
    }

    private LoggerConfig() {}
}
