package dev.jihogrammer.java.util.reflection;

public class FieldFinder {
    public boolean hasFieldInClass(Class<?> clazz, String fieldName) {
        try {
            clazz.getDeclaredField(fieldName);
        } catch (Throwable ignored) {
            return false;
        }
        return true;
    };
}
