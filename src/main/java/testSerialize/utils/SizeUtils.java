package testSerialize.utils;

import org.apache.commons.lang3.mutable.MutableShort;
import testSerialize.CanSerialize;
import testSerialize.annotation_fields.TypeField;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Fresher on 07/03/2018.
 */
public class SizeUtils {


    private static Map<Class, Short> mapSizePrimitive;

    // tính ra size của một object primitive hoặc CanSerialize
    public static short calSizePrimitiveOrSerialize(Object source) {


        if (source instanceof CanSerialize) {
            return ((CanSerialize) source).size();
        }

        short sizeByte = Byte.SIZE;

        if (source instanceof String) {
            String str = (String) source;
            // size của String
            short res = (short) (Short.SIZE / sizeByte);
            return (short) (res + str.length() * 2 / sizeByte);
        }


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


    // check xem có phải primitive
    // tính ra cỡ theo byte của một object
    public static short calSimpleSize(TypeField type, Object dataSource) {
        short tmpSize = calSizePrimitiveOrSerialize(dataSource);

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
                        size = calSizePrimitiveOrSerialize((short) 0);
                    }
                    //kiểm tra data key và value, thuộc primitive hoặc kiểu Canserialize
                    // todo: fix it in afternoon
                    else {


                        Object oneKey = data.keySet().iterator().next();
                        Object oneVal = data.values().iterator().next();


                        boolean keyIsInvalid = calSizePrimitiveOrSerialize(oneKey) > 0;
                        boolean valueInValid = oneVal instanceof CanSerialize;

                        if (keyIsInvalid || valueInValid) {
                            throw new IllegalArgumentException("key or value can not be serialize");
                        }

                        // size of the map
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
                    List<?> data = (List<?>) dataSource;
                    if (data.isEmpty()) {
                        size = 0;
                    } else {
                        Object oneElement = data.get(0);
                        boolean canSeri = oneElement instanceof CanSerialize;
                        // check type
                        if (!canSeri) {
                            throw new IllegalArgumentException("element in list can not be serialize");
                        } else {

                            // size of the list
                            MutableShort tmp = new MutableShort((short) 0);

                            data.forEach(e -> {
                                CanSerialize c = (CanSerialize) e;
                                tmp.add(c.size());
                            });

                            size = tmp.shortValue();
                        }


                    }


                    break;
                }
            }
            return size;

        }
        //return;
    }

}
