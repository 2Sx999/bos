package cn.porkchop.bos.dao;

import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {
    void save(T entity);

    void delete(T entity);

    void update(T entity);

    List<T> findAll();

    public T findById(Serializable id);

    List<T> findByCriteria(DetachedCriteria dc);

    void executeUpdate(String queryName,Object... objects);
}
