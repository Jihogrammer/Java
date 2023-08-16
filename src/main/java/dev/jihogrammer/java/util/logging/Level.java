package dev.jihogrammer.java.util.logging;

public enum Level {
    TRACE(100), DEBUG(200), INFO(300), WARN(400), ERROR(500);

    private final int priority;

    Level(int priority) {
        this.priority = priority;
    }

    public static Level of(final int value) {
        for (Level level : Level.values()) {
            if (level.priority == value) {
                return level;
            }
        }
        return INFO;
    }
    public static Level of(final String name) {
        for (Level level : Level.values()) {
            if (name.equalsIgnoreCase(level.name())) {
                return level;
            }
        }
        return INFO;
    }

    public boolean isGreaterThanEquals(Level another) {
        return this.priority >= another.priority;
    }
}
