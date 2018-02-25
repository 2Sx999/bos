package cn.porkchop.bos.dao.impl;

import cn.porkchop.bos.dao.BaseDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
    private Class<T> entityClass;

    public BaseDaoImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
        entityClass = (Class<T>) actualTypeArguments[0];
    }

    @Autowired
    public void setMySessionFactory(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    public void save(T entity) {
        getHibernateTemplate().save(entity);
    }

    @Override
    public void delete(T entity) {
        getHibernateTemplate().delete(entity);
    }

    @Override
    public void update(T entity) {
        getHibernateTemplate().update(entity);
    }

    @Override
    public List<T> findAll() {
        return (List<T>) getHibernateTemplate().find("from " + entityClass.getSimpleName());
    }

    @Override
    public List<T> find(String hql, Object[] objects) {
        return (List<T>) getHibernateTemplate().find(hql, objects);
    }

    @Override
    public T findById(Serializable id) {
        return getHibernateTemplate().get(entityClass, id);
    }

    @Override
    public List<?> findByCriteria(DetachedCriteria dc) {
        return findByCriteria(dc, 0, -1);
    }

    @Override
    public List<?> findByCriteria(DetachedCriteria dc, int start, int rows) {
        return getHibernateTemplate().findByCriteria(dc, start, rows);
    }

    @Override
    public void executeUpdate(String queryName, Object... objects) {
        Session session = getSessionFactory().getCurrentSession();
        Query query = session.getNamedQuery(queryName);
        for (int i = 0; i < objects.length; i++) {
            query.setParameter(i, objects[i]);
        }
        query.executeUpdate();
    }

    @Override
    public void saveOrUpdate(T entity) {
        getHibernateTemplate().saveOrUpdate(entity);
    }


}
