package sk.tomas.app.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sk.tomas.app.dao.IdentityDao;
import sk.tomas.app.model.Identity;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by tomas on 24.12.2016.
 */

@Transactional
@Repository
public class IdentityDaoImpl implements IdentityDao {

    @Resource
    private SessionFactory sessionFactory;

    @Override
    public int save(Identity identity) {
        getCurrentSession().save(identity);
        return identity.getId();
    }

    @Override
    public int update(Identity identity) {
        getCurrentSession().update(identity);
        return identity.getId();
    }

    @Override
    public void delete(int id) {
        Identity identity = new Identity();
        identity.setId(id);
        getCurrentSession().delete(identity);
    }

    @Override
    public List<Identity> list() {
        List<Identity> result = (List<Identity>) getCurrentSession().createQuery("from Identity").list();
        return result;
    }

    @Override
    public Identity findByValue(String key, String value) {
        Criteria criteria = getCurrentSession().createCriteria(Identity.class);
        return (Identity) criteria.add(Restrictions.eq(key, value)).uniqueResult();
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
