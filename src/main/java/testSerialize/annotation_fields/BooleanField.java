package testSerialize.annotation_fields;

/**
 * Created by Fresher on 07/03/2018.
 */
public @interface BooleanField {
    boolean requireSize() default true;
    byte indexField();
}
