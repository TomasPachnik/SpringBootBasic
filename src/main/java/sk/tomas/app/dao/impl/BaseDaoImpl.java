package sk.tomas.app.dao.impl;

import ma.glasnost.orika.MapperFacade;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import sk.tomas.app.dao.BaseDao;
import sk.tomas.app.model.base.Entity;
import sk.tomas.app.orm.EntityNode;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

import static sk.tomas.app.util.ErrorMessages.MISSING_UUID;
import static sk.tomas.app.util.ErrorMessages.MOREOVER_UUID;

/**
 * Created by Tomas Pachnik on 04-Jan-17.
 */

@Transactional
public abstract class BaseDaoImpl<T extends Entity, N extends EntityNode> implements BaseDao<T> {

    private static final Logger logger = LoggerFactory.getLogger(BaseDaoImpl.class);
    private Class<T> clazz;
    private Class<N> nodeClazz;

    @Resource
    private SessionFactory sessionFactory;
    @Autowired
    private MapperFacade mapper;

    public BaseDaoImpl(Class<T> clazz, Class<N> nodeClazz) {
        this.clazz = clazz;
        this.nodeClazz = nodeClazz;
    }

    public UUID create(T t) {
        if (t.getUuid() != null) {
            throw new IllegalArgumentException(MOREOVER_UUID.getMessage());
        }
        t.setUuid(UUID.randomUUID());
        EntityNode n = mapper.map(t, nodeClazz);
        getCurrentSession().save(n);
        return t.getUuid();
    }

    public UUID update(T t) {
        if (t.getUuid() == null) {
            throw new IllegalArgumentException(MISSING_UUID.getMessage());
        }
        EntityNode n = mapper.map(t, nodeClazz);
        getCurrentSession().update(n);
        return t.getUuid();
    }

    public void delete(UUID uuid) {
        T t = null;
        try {
            t = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            logger.error(e.toString());
        }
        t.setUuid(uuid);
        EntityNode n = mapper.map(t, nodeClazz);
        getCurrentSession().delete(n);
    }

    public List<T> list() {
        List<N> result = (List<N>) getCurrentSession().createQuery("from " + nodeClazz.getSimpleName()).list();
        return mapper.mapAsList(result, clazz);
    }

    public T findByValue(String key, String value) {
        DetachedCriteria criteria = DetachedCriteria.forClass(nodeClazz);
        criteria.add(Restrictions.eq(key, value));
        EntityNode n = (N) criteria.getExecutableCriteria(getCurrentSession()).uniqueResult();
        return mapper.map(n, clazz);
    }

    Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

}
