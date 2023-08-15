package dev.jihogrammer.java.util.logging;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class TimestampTests {
    @Test
    void testFormat() throws IllegalAccessException {
        // given
        Timestamp timestamp = Timestamp.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // when
        for (Field field : Timestamp.class.getDeclaredFields()) {
            if (field.getType().isAssignableFrom(LocalDateTime.class) && field.getName().equals("timestamp")) {
                field.setAccessible(true);
                field.set(timestamp, LocalDateTime.of(1994, 6, 14, 0, 0));
            }
        }
        // then
        assertEquals("1994-06-14", timestamp.format(dateTimeFormatter));
    }
}