package dev.jihogrammer.java.util.logging.console;

import dev.jihogrammer.java.util.logging.LogFormatter;
import dev.jihogrammer.java.util.reflection.ClassFinder;
import dev.jihogrammer.java.util.reflection.FieldFinder;
import org.slf4j.event.Level;

import java.lang.reflect.Modifier;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConsoleLogFormatter implements LogFormatter {
    private static final int MAX_NAME_LENGTH = new ClassFinder().findAllClasses().stream()
            .filter(clazz -> new FieldFinder().hasFieldInClass(clazz, "log"))
            .filter(clazz -> !Modifier.isFinal(clazz.getModifiers()))
            .mapToInt(clazz -> clazz.getSimpleName().length())
            .max()
            .orElse(20);

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    @Override
    public String format(final String name, final Level level, final String message, final Object[] args) {
        return LocalDateTime.now().format(DATE_TIME_FORMATTER) + ' ' +
                '[' + formatWithLength(Thread.currentThread().getName(), 19) + ']' + ' ' +
                '[' + formatWithLength(level.name(), 5) + ']' + ' ' +
                '[' + formatWithLength(name, MAX_NAME_LENGTH) + ']' + ' ' +
                formatMessageBody(message, args);
    }

    private String formatWithLength(final String str, final int length) {
        return String.format("%" + length + "s", str.substring(Math.max(0, str.length() - length)));
    }

    private String formatMessageBody(final String message, final Object[] args) {
        if (message.contains("{}")) {
            StringBuilder sb = new StringBuilder();
            String[] tokens = message.split("\\{}");
            for (int index = 0; index < Math.min(tokens.length, args.length); index++) {
                sb.append(tokens[index]).append(args[index].toString());
            }
            return sb.toString();
        }
        return message;
    }
}
