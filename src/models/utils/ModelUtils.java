package models.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ModelUtils {

    private ModelUtils(){}

    public static <T> List<T> iterableToList(Iterable<T> iterable){
        List<T> list = new ArrayList<>();
        iterable.forEach(list::add);
        return list;
    }

    public static <T> List<T> collectionToList(Collection<T> collection){
        return new ArrayList<>(collection);
    }
}
