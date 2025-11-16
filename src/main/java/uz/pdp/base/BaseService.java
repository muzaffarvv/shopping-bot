package uz.pdp.base;

import java.io.IOException;
import java.util.UUID;

public  interface BaseService<T> {

    void add(T t) throws IOException;
    T get(UUID id);
    boolean update(T t);
    void delete(T t);
}
