package interfaces;

import java.util.Map;

public interface FileWriterService<T extends Map<String, Object>> {
    public void writeData(T obj);
}
