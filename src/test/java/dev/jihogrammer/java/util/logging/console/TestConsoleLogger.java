package dev.jihogrammer.java.util.logging.console;

import dev.jihogrammer.java.util.logging.console.BaseConsoleLogger;
import lombok.Getter;
import org.slf4j.event.Level;

// TODO 로깅에서 제외할 패키지 받을 수 있게 설정
@Getter
public final class TestConsoleLogger extends BaseConsoleLogger {
    private final String name;

    public TestConsoleLogger(final Class<?> clazz) {
        this.name = clazz.getSimpleName();
    }

    @Override
    public String coloring(final Level level) {
        return switch (level) {
            case TRACE -> "\u001B[0;90m";
            case DEBUG -> "\u001B[32m";
            case WARN -> "\u001B[33m";
            case ERROR -> "\u001B[31m";
            default -> "\u001B[0m";
        };
    }
}
