package dev.jihogrammer.java.util.logging.console;

import dev.jihogrammer.java.util.logging.LogFormatter;
import dev.jihogrammer.java.reflect.ClassFinder;
import dev.jihogrammer.java.reflect.FieldFinder;
import dev.jihogrammer.java.util.time.Time;
import org.slf4j.Logger;
import org.slf4j.event.Level;

import java.time.format.DateTimeFormatter;

public class ConsoleLogFormatter implements LogFormatter {
    private static final DateTimeFormatter TIME_FORMATTER;
    private static final String LOG_FORMAT;

    static {
        TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

        int maxClassNameLength = new ClassFinder().findAllClasses().stream()
                .filter(aClass -> new FieldFinder(aClass).hasField(Logger.class))
                .mapToInt(aClass -> aClass.getSimpleName().length())
                .max().orElseThrow();
        LOG_FORMAT = "%s [%32s] [%5s] [%" + maxClassNameLength + "s] %s";
    }

    @Override
    public String format(final String name, final Level level, final String message, final Object[] args) {
        return LOG_FORMAT.formatted(timestamp(), threadName(), level, name, body(message, args));
    }

    private String timestamp() {
        return Time.now().format(TIME_FORMATTER);
    }

    private String threadName() {
        return Thread.currentThread().getName();
    }

    private String body(final String message, final Object[] args) {
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
