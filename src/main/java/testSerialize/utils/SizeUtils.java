package testSerialize.utils;

import testSerialize.CanSerialize;
import testSerialize.annotation_fields.TypeField;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Fresher on 07/03/2018.
 */
public class SizeUtils {


    private static Map<Class, Short> mapSizePrimitive;

    // chỉ tính các kiểu data cơ bản
    private short calSizePrimitive(Object source) {

        short sizeByte = Byte.SIZE;

        // put all data of primitive type
        if (mapSizePrimitive == null) {
            mapSizePrimitive = new HashMap<>();
            mapSizePrimitive.put(Integer.class, (short) (Integer.SIZE / sizeByte));
            mapSizePrimitive.put(Double.class, (short) (Double.SIZE / sizeByte));
            mapSizePrimitive.put(Short.class, (short) (Short.SIZE / sizeByte));
            mapSizePrimitive.put(Byte.class, (short) (Byte.SIZE / sizeByte));
            mapSizePrimitive.put(Boolean.class, (short) (Byte.SIZE / sizeByte));
            mapSizePrimitive.put(Long.class, (short) (Long.SIZE / sizeByte));
        }

        Class t = source.getClass();

        if (mapSizePrimitive.containsKey(t)) {
            return mapSizePrimitive.get(t);
        } else {
            return -1;
        }

    }


    // integer, double, short, byte, boolean, String
    private short calSizeByType(Class<?> cl, CanSerialize source) {


        throw new IllegalArgumentException("class not supported");
    }


    public static short calSimpleSize(TypeField type, Object dataSource) {


        short sizeByte = Byte.SIZE;

        short size = -1;

        switch (type) {
            case BOOLEAN_FIELD: {
                size = (short) (Byte.SIZE / sizeByte);
                break;
            }
            case STRING_FIELD: {

                //String source

                break;
            }
            case DOUBLE_FIELD:
            case LIST_FIELD:
            case BYTE_FIELD:
            case MAP_FIELD:
            case INT_FIELD: {

            }
        }

        return size;
    }

}
