package testSerialize.impl;

import testSerialize.CanDeserialize;
import testSerialize.CanSerialize;
import testSerialize.annotation_fields.SerializeField;
import testSerialize.annotation_fields.TypeField;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Fresher on 07/03/2018.
 */
public class Person implements CanDeserialize, CanSerialize {


    @SerializeField(type = TypeField.INT_FIELD, index = 0)
    private int id;

    @SerializeField(type = TypeField.STRING_FIELD, index = 1)
    private String name;

    @SerializeField(type = TypeField.BOOLEAN_FIELD, index = 2)
    private boolean male;


    public Person() {
    }

    @Override
    public CanDeserialize deserialize(byte[] source) {
        return null;
    }

    @Override
    public byte[] serialize() {

        Field[] allFields = getClass().getFields();

        Map<Byte, Short> header = new HashMap<>();

        for (Field f : allFields) {
            SerializeField metaDataOfField = f.getAnnotation(SerializeField.class);


            switch (metaDataOfField.type()) {
                case INT_FIELD: {
                    break;
                }
                case MAP_FIELD:
                case BYTE_FIELD:
                case LIST_FIELD:
                case DOUBLE_FIELD:
                case STRING_FIELD:
                case BOOLEAN_FIELD:
            }

            //header.put(metaDataOfField.index(), Integer.S)

        }


        return new byte[0];
    }

    @Override
    public short size() {
        return 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }
}
