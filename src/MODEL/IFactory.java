package MODEL;

import java.util.ArrayList;

public interface IFactory<T> {

    T getItem (String key);
    void addItem();
    ArrayList<T> getAll();

}
