package sk.tomas.app.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;
import sk.tomas.app.dao.BaseDao;
import sk.tomas.app.model.base.Entity;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Tomas Pachnik on 04-Jan-17.
 */

@Transactional
public abstract class BaseDaoImpl<T extends Entity> implements BaseDao<T> {

    private Class<T> clazz;
    @Resource
    private SessionFactory sessionFactory;

    public BaseDaoImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    public int create(T t) {
        getCurrentSession().save(t);
        return t.getId();
    }

    public int update(T t) {
        getCurrentSession().update(t);
        return t.getId();
    }

    public void delete(int id) {
        T t = null;
        try {
            t = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        t.setId(id);
        getCurrentSession().delete(t);
    }

    public List<T> list() {
        List<T> result = (List<T>) getCurrentSession().createQuery("from Identity").list();
        return result;
    }

    public T findByValue(String key, String value) {
        Criteria criteria = getCurrentSession().createCriteria(clazz);
        return (T) criteria.add(Restrictions.eq(key, value)).uniqueResult();
    }

    Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

}
