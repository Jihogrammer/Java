package dev.jihogrammer.java.util.logging;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TimestampTests {
    @Test
    void now() throws IllegalAccessException {
        // given
        Timestamp timestamp = Timestamp.now();
        // when
        for (Field field : Timestamp.class.getDeclaredFields()) {
            if (field.getType().isAssignableFrom(LocalDateTime.class) && field.getName().equals("timestamp")) {
                field.setAccessible(true);
                field.set(timestamp, LocalDateTime.of(1994, 6, 14, 0, 0));
            }
        }
        // then
        assertEquals("1994-06-14 00:00:00.000", timestamp.toString());
    }
}