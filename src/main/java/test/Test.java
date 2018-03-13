package test;

import junit.framework.TestCase;
import testSerialize.utils.SizeUtils;

import java.lang.reflect.Constructor;

/**
 * Created by Fresher on 08/03/2018.
 */
public class Test extends TestCase {

    public void testSize() {
        short t = SizeUtils.calSizePrimitiveOrSerialize("12345");
        assertEquals(12, t);

    }



    public void testReflect() throws Exception {
        Class t = Integer.class;

        //Integer y= new Integer(5);

        Constructor c= t.getConstructor(int.class);

        Object r = c.newInstance(5);
        System.out.println();
    }

    public void testPrimitive(){
        Object t= new String("57657");

        assertEquals(true, SizeUtils.isPrimitive(t));
    }


}
