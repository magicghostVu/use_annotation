package testSerialize.utils;

import org.apache.commons.lang3.mutable.MutableShort;
import testSerialize.CanSerialize;
import testSerialize.annotation_fields.TypeField;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Fresher on 07/03/2018.
 */
public class SizeUtils {


    private static Map<Class, Short> mapSizePrimitive;

    // chỉ tính các kiểu data cơ bản, tạm thời sử dụng 6 kiểu
    // sẽ không trả lại giá trị đúng nếu truyền không đúng 6 kiểu đấy
    private static short calSizePrimitive(Object source) {

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


    // check xem có phải primitive
    // tính ra cỡ theo byte của một object
    public static short calSimpleSize(TypeField type, Object dataSource) {
        short tmpSize = calSizePrimitive(dataSource);

        if (tmpSize > 0) {
            return tmpSize;
        }
        // sẽ tính toán thêm ở đây, chỉ còn lại map field và list field
        else {

            short size = 0;

            switch (type) {
                case MAP_FIELD: {

                    if (!(dataSource instanceof Map)) {
                        throw new IllegalArgumentException("map field is not a map");
                    }

                    Map data = (Map) dataSource;

                    // map rỗng
                    if (data.isEmpty()) {
                        size = 0;
                    }
                    //kiểm tra data key và value
                    else {
                        Object oneKey = data.keySet().iterator().next();
                        Object oneVal = data.values().iterator().next();
                        boolean keyIsInvalid = oneKey instanceof CanSerialize;
                        boolean valueInValid = oneVal instanceof CanSerialize;

                        if (keyIsInvalid || valueInValid) {
                            throw new IllegalArgumentException("key or value is can not serialize");
                        }

                        // size of th map
                        MutableShort res = new MutableShort(calSizePrimitive((short) 0));

                        data.forEach((key, val) -> {
                            CanSerialize k = (CanSerialize) key;
                            CanSerialize v = (CanSerialize) val;
                            res.add(k.size());
                            res.add(v.size());
                        });
                        size = res.shortValue();

                    }

                    //Object
                    break;
                }
                case LIST_FIELD: {
                    break;
                }
            }

        }

        return 0;
    }

}
