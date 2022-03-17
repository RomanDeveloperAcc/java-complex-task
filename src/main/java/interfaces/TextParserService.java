package interfaces;

import java.util.ArrayList;

public interface TextParserService<T> {
    public ArrayList<T> parse(ArrayList<String> list);
}
