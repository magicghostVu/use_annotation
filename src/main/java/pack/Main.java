package pack;

import my_annotation.F_Annotation;
import my_annotation.MyFClassAnntotation;

import java.lang.reflect.Field;

/**
 * Created by magic on 04/03/2018.
 */
public class Main {
    public static void main(String[] args) throws Exception{
        System.out.println("okok");

        ClassUseAnnotation c1 = new ClassUseAnnotation();
        Class<?> cl = ClassUseAnnotation.class;

        boolean hadAnntF_A = cl.isAnnotationPresent(F_Annotation.class);

        System.out.println(hadAnntF_A);

        boolean hadMyFAnntClass = cl.isAnnotationPresent(MyFClassAnntotation.class);

        MyFClassAnntotation fClassAnntotation = cl.getAnnotation(MyFClassAnntotation.class);

        fClassAnntotation.name();


        Field[] allField = cl.getDeclaredFields();



        for(Field f:allField){
            F_Annotation f_annotation= f.getAnnotation(F_Annotation.class);


            if(f_annotation!=null){
                f.setAccessible(true);
                f.set(c1, 1);
                f.setAccessible(false);
            }

        }


        System.out.println(hadMyFAnntClass);


    }
}
