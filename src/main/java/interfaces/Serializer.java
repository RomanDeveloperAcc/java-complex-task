package interfaces;

import java.util.List;

public interface Serializer<T> {
    public List<Object> serialize(T obj) throws IllegalAccessException;
}
