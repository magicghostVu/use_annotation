package pack;

import my_annotation.F_Annotation;
import my_annotation.MyFClassAnntotation;

/**
 * Created by magic on 04/03/2018.
 */
@MyFClassAnntotation(name = "this is name")
public class ClassUseAnnotation {
    @F_Annotation(name = "field1")
    private int field1;
    public ClassUseAnnotation() {
    }

    public int getField1() {
        return field1;
    }

    public void setField1(int field1) {
        this.field1 = field1;
    }
}
