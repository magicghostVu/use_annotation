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
public @interface SerializeField {
    TypeField type();

    byte id();

    Class elementsType() default Object.class;

    Class keyType() default Object.class;

    Class valueType() default Object.class;
}
