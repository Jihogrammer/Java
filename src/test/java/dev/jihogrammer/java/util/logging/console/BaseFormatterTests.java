package dev.jihogrammer.java.util.logging.console;

import dev.jihogrammer.java.util.logging.Event;
import dev.jihogrammer.java.util.logging.Formatter;
import dev.jihogrammer.java.util.logging.Level;
import dev.jihogrammer.java.util.logging.Timestamp;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class BaseFormatterTests {
    @Test
    void format() {
        // given
        Timestamp expectedTimestamp = Timestamp.now();
        Thread expectedThread = Thread.currentThread();
        Level expectedLevel = Level.INFO;
        String expectedName = "test";
        Event event = new Event(expectedTimestamp, expectedThread, expectedLevel, expectedName, "hello {}", "world");

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String logFormat = "%s [%s] [%s] %s - %s";
        Formatter formatter = new BaseFormatter(logFormat, dateTimeFormatter);
        // when
        String actual = formatter.format(event);
        // then
        assertEquals(
                expectedTimestamp.format(dateTimeFormatter) + " " +
                        "[" + expectedThread.getName() + "] " +
                        "[" + expectedLevel.name() + "] " +
                        expectedName + " - hello world",
                actual
        );
    }
}