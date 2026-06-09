package models.daos.generic;

import models.entities.AbstractEntity;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class MemoryDao<T extends AbstractEntity> implements Dao<T> {

    protected Map<Long,T> persist = new HashMap<>();

    Long sequence = 0L;

    private Long incrementationAutoSequence(){
        return sequence++;
    }

    @Override
    public void create(T entity) {

        entity.setId(incrementationAutoSequence());

        persist.put(entity.getId(), entity);

    }

    @Override
    public T read(Long id) {
        return persist.get(id);
    }

    @Override
    public Iterable<T> readAll() {
        return Collections.unmodifiableCollection(persist.values());
    }

    @Override
    public void update(T entity) {
        persist.replace(entity.getId(), entity);
    }

    @Override
    public void delete(Long id) {
        persist.remove(id);
    }

    @Override
    public boolean exists(Long id) {
        return persist.containsKey(id);
    }

    @Override
    public long count() {
        return persist.size();
    }
}

