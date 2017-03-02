package sk.tomas.app.dao.impl;

import ma.glasnost.orika.MapperFacade;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.id.IdentifierGenerationException;
import org.hibernate.query.Query;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import sk.tomas.app.dao.BaseDao;
import sk.tomas.app.model.base.Entity;
import sk.tomas.app.orm.EntityNode;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

import static sk.tomas.app.util.ErrorMessages.*;

/**
 * Created by Tomas Pachnik on 04-Jan-17.
 */
@Transactional
public abstract class BaseDaoImpl<T extends Entity, N extends EntityNode> implements BaseDao<T> {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(BaseDaoImpl.class);
    private Class<T> clazz;
    private Class<N> nodeClazz;

    @Resource
    private SessionFactory sessionFactory;
    @Autowired
    private MapperFacade mapper;

    BaseDaoImpl(Class<T> clazz, Class<N> nodeClazz) {
        this.clazz = clazz;
        this.nodeClazz = nodeClazz;
    }

    public UUID create(T t) {
        if (t.getUuid() != null) {
            throw new IllegalArgumentException(MOREOVER_UUID.getMessage());
        }
        t.setUuid(UUID.randomUUID());
        EntityNode n = mapper.map(t, nodeClazz);
        try {
            getCurrentSession().save(n);
        } catch (IdentifierGenerationException e) {
            throw new IllegalArgumentException(RELATED_NOT_CREATED.getMessage());
        }
        return t.getUuid();
    }

    public UUID update(T t) {
        getCurrentSession().clear();
        if (t.getUuid() == null) {
            throw new IllegalArgumentException(MISSING_UUID.getMessage());
        }
        EntityNode n = mapper.map(t, nodeClazz);
        getCurrentSession().update(n);
        return t.getUuid();
    }

    public void delete(UUID uuid) {
        getCurrentSession().clear();
        if (uuid == null) {
            throw new IllegalArgumentException(MISSING_UUID.getMessage());
        }
        N n = null;
        try {
            n = nodeClazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            logger.error("Entita nezmazana!\n" + e.toString());
        }

        if (n != null) {
            n.setUuid(uuid.toString());
            getCurrentSession().delete(n);
        }
    }

    @SuppressWarnings("unchecked")
    public List<T> list() {
        List<N> result = (List<N>) getCurrentSession().createQuery("from " + nodeClazz.getSimpleName()).list();
        return mapper.mapAsList(result, clazz);
    }

    @SuppressWarnings("unchecked")
    public List<T> list(int firstResult, int maxResult, String orderBy, boolean desc) {
        String direction;
        if (desc) {
            direction = "desc";
        } else {
            direction = "asc";
        }
        if (orderBy == null || orderBy.isEmpty()) {
            orderBy = "uuid";
        }
        Query query = getCurrentSession().createQuery("from " + nodeClazz.getSimpleName() + " a order by a." + orderBy + " " + direction);
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResult);
        List<N> nList = query.list();
        return mapper.mapAsList(nList, clazz);
    }

    public long count() {
        String countQ = "Select count (a.uuid) from " + nodeClazz.getSimpleName() + " a";
        Query countQuery = getCurrentSession().createQuery(countQ);
        return (long) countQuery.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public T findByValue(String key, String value) {
        DetachedCriteria criteria = DetachedCriteria.forClass(nodeClazz);
        criteria.add(Restrictions.eq(key, value));
        EntityNode n = (N) criteria.getExecutableCriteria(getCurrentSession()).uniqueResult();
        return mapper.map(n, clazz);
    }

    public T findByUuid(UUID uuid) {
        N n = getCurrentSession().get(nodeClazz, uuid.toString());
        return mapper.map(n, clazz);
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

}
