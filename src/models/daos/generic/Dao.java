package models.daos.generic;

public interface Dao<T> {

    void create(T entity);

    T read(Long id);

    Iterable<T> readAll();

    void update(T entity);

    void delete(Long id);

    boolean exists(Long id);

    long count();

}
