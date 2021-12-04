package cz.kajacx.limitedchests.util;

import java.lang.reflect.Field;

import javax.annotation.Nullable;

public class Reflect {

    private static @Nullable Field findField(Object object, String fieldName) {
        Class<?> clazz = object.getClass();
        try {
            return clazz.getField(fieldName);
        } catch (Exception ex) {
            // No need to worry, we can still try "getDeclaredField" in the entire ancestor tree
        }

        while (clazz != Object.class) {
            try {
                return clazz.getDeclaredField(fieldName);
            } catch (Exception ex) {
                // Continue search in ancestors ....
            }
            clazz = clazz.getSuperclass();
        }

        // Nothing was found
        Log.logger.warn(Log.missingFieldMarker, "Object '{}' does not have field '{}'.", object, fieldName);
        return null;
    }

    public static Object getField(Object object, String fieldName) {
        Field field = findField(object, fieldName);
        if (field == null) {
            return null;
        }

        boolean wasAccessible = field.isAccessible();
        Object value = null;
        field.setAccessible(true);
        try {
            value = field.get(object);
        } catch (Exception ex) {
            Log.logger.catching(ex);
        }
        if (!wasAccessible) {
            field.setAccessible(false);
        }
        return value;
    }

    public static void setField(Object object, String fieldName, Object value) {
        Field field = findField(object, fieldName);
        if (field == null) {
            return;
        }

        boolean wasAccessible = field.isAccessible();
        field.setAccessible(true);
        try {
            field.set(object, value);
        } catch (Exception ex) {
            Log.logger.catching(ex);
        }
        if (!wasAccessible) {
            field.setAccessible(false);
        }
    }

}
