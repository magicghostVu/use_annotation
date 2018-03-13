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


    private synchronized static void initCacheMap() {
        short sizeByte = Byte.SIZE;
        if (mapSizePrimitive == null) {
            mapSizePrimitive = new HashMap<>();
            mapSizePrimitive.put(Integer.class, (short) (Integer.SIZE / sizeByte));
            mapSizePrimitive.put(Double.class, (short) (Double.SIZE / sizeByte));
            mapSizePrimitive.put(Short.class, (short) (Short.SIZE / sizeByte));
            mapSizePrimitive.put(Byte.class, (short) (Byte.SIZE / sizeByte));
            mapSizePrimitive.put(Boolean.class, (short) (Byte.SIZE / sizeByte));
            mapSizePrimitive.put(Long.class, (short) (Long.SIZE / sizeByte));
        }
    }

    public static boolean isPrimitive(Object o) {
        initCacheMap();
        if (o instanceof String) return true;
        if (mapSizePrimitive.containsKey(o.getClass())) return true;
        return false;
    }


    // tính ra size của một object primitive hoặc CanSerialize
    //sẽ không thể tính ra bất cứ object có kiểu khác
    public static short calSizePrimitiveOrSerialize(Object source) {


        if (source instanceof CanSerialize) {
            return ((CanSerialize) source).size();
        }

        short sizeByte = Byte.SIZE;

        if (source instanceof String) {
            String str = (String) source;
            // size của String
            short res = (short) (Short.SIZE / sizeByte);
            return (short) (res + str.length() * 2);
        }
        // put all data of primitive type
        initCacheMap();

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

                    //Object on

                    //boolean validDataType=

                    Map data = (Map) dataSource;

                    // map rỗng, size=2 , bằng kích thước 2 byte (chỉ bao gồm kích thước map)
                    if (data.isEmpty()) {
                        size = calSizePrimitiveOrSerialize((short) 0);
                    } else {

                        // cứ tính size của map trước đã, dùng một số short để lưu cỡ của map
                        MutableShort _tmpSize = new MutableShort(calSizePrimitiveOrSerialize((short) 0));

                        // cần thêm cỡ tương ứng của từng key và value
                        short sizeMap = (short) data.size();

                        // hard code
                        short sizeMetaDataForInforSizeEntries = (short) (sizeMap * 2 * calSizePrimitiveOrSerialize((short) 0));

                        _tmpSize.add(sizeMetaDataForInforSizeEntries);

                        //kích thước của data ()
                        data.forEach((key, val) -> {
                            _tmpSize.add(calSizePrimitiveOrSerialize(key));
                            _tmpSize.add(calSizePrimitiveOrSerialize(val));
                        });

                        size = _tmpSize.shortValue();

                    }
                    // map rỗng
                    //Object
                    break;
                }

                // tính size cho list
                case LIST_FIELD: {
                    List<?> data = (List<?>) dataSource;
                    if (data.isEmpty()) {
                        size = calSizePrimitiveOrSerialize((short) 0);
                    } else {

                        // cỡ của List
                        MutableShort sizeHeader = new MutableShort(calSizePrimitiveOrSerialize((short) 0));


                        int sizeList = data.size();
                        // chỗ lưu từng cỡ của từng element, mỗi element sẽ có 2 byte để lưu cỡ của data element đó
                        sizeHeader.add((short) (sizeList * 2 * calSizePrimitiveOrSerialize((short) 0)));


                        MutableShort sizeData= new MutableShort(0);

                        data.forEach(e->{
                            sizeData.add();
                        });

                    }


                    break;
                }
            }
            return size;

        }
        //return;
    }

}
