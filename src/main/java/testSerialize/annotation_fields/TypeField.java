package testSerialize.annotation_fields;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Fresher on 07/03/2018.
 */
public enum TypeField {

    INT_FIELD((byte) 0),

    BYTE_FIELD((byte) 1),

    DOUBLE_FIELD((byte) 2),

    STRING_FIELD((byte) 3),

    BOOLEAN_FIELD((byte) 4),

    LIST_FIELD((byte) 5),

    MAP_FIELD((byte) 6),

    SHORT_FIELD((byte) 7),

    OBJECT_FIELD((byte) 8);


    private byte code;




    TypeField(byte code) {
        this.code = code;
    }

    public byte getCode() {
        return code;
    }

    private static Map<Byte, TypeField> cacheMap;

    public static TypeField fromCode(byte code) {
        if (cacheMap == null) {
            cacheMap = new HashMap<>();
            for (TypeField t : TypeField.values()) {
                cacheMap.put(t.getCode(), t);
            }
            return cacheMap.get(code);
        } else {
            return cacheMap.get(code);
        }
    }
}
