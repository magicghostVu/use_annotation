package testSerialize.annotation_fields;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Fresher on 07/03/2018.
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.FIELD})
public @interface ByteField {
    boolean requireSize() default true;
    byte indexField();
}
