package dev.jihogrammer.java.reflect;

import java.lang.reflect.Field;

public final class FieldFinder {
    private final Class<?> clazz;

    public FieldFinder(Class<?> clazz) {
        this.clazz = clazz;
    }

    public boolean hasField(String fieldName) {
        for (Field field : clazz.getDeclaredFields()) {
            if (field.getName().equals(fieldName)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasField(Class<?> type) {
        for (Field field : clazz.getDeclaredFields()) {
            if (field.getType().isAssignableFrom(type)) {
                return true;
            }
        }
        return false;
    }
}
