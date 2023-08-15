package dev.jihogrammer.java.util.logging;

public enum Level {
    TRACE(100), DEBUG(200), INFO(300), WARN(400), ERROR(500);

    private final int value;

    Level(int value) {
        this.value = value;
    }

    public static Level of(final int value) {
        for (Level level : Level.values()) {
            if (level.value == value) {
                return level;
            }
        }
        return INFO;
    }
}
