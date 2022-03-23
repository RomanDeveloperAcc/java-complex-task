package services;

import interfaces.Serializer;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ObjectSerializer<T> implements Serializer<T> {
    public List<Object> serialize(T object) throws IllegalAccessException {
        Field[] fields = object.getClass().getDeclaredFields();
        List<Object> testList = new ArrayList<>();

        for (Field field : fields) {
            field.setAccessible(true);
            testList.add(field.get(object));
        }

        return testList;
    }
}
