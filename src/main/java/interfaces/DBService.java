package interfaces;

public interface DBService<T> {
    public void createTable();
    public void readTable();
    public void insertIntoTable(T object);
    public void deleteTable();
}