package cn.porkchop.bos.dao;

import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {
    void save(T entity);

    void delete(T entity);

    void update(T entity);

    List<T> findAll();

    List<T> find(String hql, Object[] objects);

    public T findById(Serializable id);

    List<?> findByCriteria(DetachedCriteria dc);

    /**
     * @param start
     *         开始的index,从0开始
     * @param rows
     *         最大的条目数,负数表示无限制
     * @date 2018/2/21 11:46
     * @author porkchop
     */
    List<?> findByCriteria(DetachedCriteria dc, int start, int rows);

    void executeUpdate(String queryName, Object... objects);

    void saveOrUpdate(T entity);

}
