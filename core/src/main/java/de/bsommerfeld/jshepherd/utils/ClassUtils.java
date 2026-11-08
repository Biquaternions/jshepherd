package de.bsommerfeld.jshepherd.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClassUtils {
    /**
     * Gets all declared fields from the given class and its superclasses, up to (but not including) Object.class or a
     * specified stop class. Fields from superclasses appear first in the list.
     *
     * @param clazz     The class to introspect.
     * @param stopClass The class at which to stop ascending the hierarchy (e.g., ConfigurablePojo.class).
     *
     * @return A list of all relevant fields.
     */
    public static List<Field> getAllFieldsInHierarchy(Class<?> clazz, Class<?> stopClass) {
        final java.util.Deque<Class<?>> b$hierarchy = new java.util.ArrayDeque<>();
        Class<?> currentClass = clazz;
        // Ascend until the stopClass or Object is reached
        while (currentClass != null && currentClass != Object.class &&
                (stopClass == null || !stopClass.equals(currentClass))) {
            b$hierarchy.addFirst(currentClass);
            currentClass = currentClass.getSuperclass();
        }
        final List<Field> fields = new ArrayList<>();
        for (final Class<?> type : b$hierarchy) {
            fields.addAll(Arrays.asList(type.getDeclaredFields()));
        }
        fields.sort(java.util.Comparator.comparingInt(ClassUtils::getOrder));
        return fields;
    }
    private static int getOrder(final Field field) {
        final me.biquaternions.jshepherd.annotation.Order order = field.getAnnotation(me.biquaternions.jshepherd.annotation.Order.class);
        return order == null ? 0 : order.value();
    }
}
