package dev.jihogrammer.java.util.logging.console;

import dev.jihogrammer.java.util.logging.LogFormatter;
import dev.jihogrammer.java.reflect.ClassFinder;
import dev.jihogrammer.java.reflect.FieldFinder;
import dev.jihogrammer.java.util.time.Time;
import org.slf4j.Logger;
import org.slf4j.event.Level;

import java.lang.reflect.Modifier;
import java.time.format.DateTimeFormatter;

public class ConsoleLogFormatter implements LogFormatter {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
    private static final int MAX_THREAD_NAME_LENGTH = 19;
    private static final int MAX_LEVEL_NAME_LENGTH = 5;
    private static final int MAX_CLASS_NAME_LENGTH = new ClassFinder().findAllClasses().stream()
            .filter(clazz -> new FieldFinder(clazz).hasField(Logger.class))
            .filter(clazz -> !Modifier.isFinal(clazz.getModifiers()))
            .mapToInt(clazz -> clazz.getSimpleName().length())
            .max()
            .orElse(20);

    @Override
    public String format(final String name, final Level level, final String message, final Object[] args) {
        return "%s [%s] [%s] [%s] %s".formatted(timestamp(), threadName(), levelName(level), className(name), messageBody(message, args));
    }

    private String timestamp() {
        return Time.now().format(TIME_FORMATTER);
    }

    private String threadName() {
        return formatWithLength(Thread.currentThread().getName(), MAX_THREAD_NAME_LENGTH);
    }

    private String levelName(final Level level) {
        return formatWithLength(level.name(), MAX_LEVEL_NAME_LENGTH);
    }

    private String className(String name) {
        return formatWithLength(name, MAX_CLASS_NAME_LENGTH);
    }

    private String formatWithLength(final String str, final int length) {
        return String.format("%" + length + "s", str.substring(Math.max(0, str.length() - length)));
    }

    private String messageBody(final String message, final Object[] args) {
        if (message.contains("{}")) {
            StringBuilder sb = new StringBuilder();
            String[] tokens = message.split("\\{}");

            int index = 0;
            while (index < tokens.length) {
                sb.append(tokens[index]);
                if (index < args.length) {
                    sb.append(args[index].toString());
                }
                index++;
            }

            return sb.toString();
        }
        return message;
    }
}
