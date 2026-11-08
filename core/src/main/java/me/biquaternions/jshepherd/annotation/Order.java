package me.biquaternions.jshepherd.annotation;

import de.bsommerfeld.jshepherd.annotation.Key;
import de.bsommerfeld.jshepherd.annotation.Section;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specifies the position of a {@link Key} or {@link Section} field in the configuration file.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Order {
    int value();
}
