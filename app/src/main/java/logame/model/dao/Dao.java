package logame.model.dao;

import java.util.List;

public interface Dao<T> {
    
    public List<T> findAll();

    public T findById(Number id);

    public void create(T entity);

    public void update(T entity);
}
