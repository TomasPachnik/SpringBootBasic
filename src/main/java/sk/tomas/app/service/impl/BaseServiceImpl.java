package sk.tomas.app.service.impl;

import sk.tomas.app.dao.BaseDao;
import sk.tomas.app.model.base.Entity;
import sk.tomas.app.service.BaseService;

import java.util.List;
import java.util.UUID;

/**
 * Created by Tomas Pachnik on 05-Jan-17.
 */
public abstract class BaseServiceImpl<T extends Entity> implements BaseService<T> {

    protected abstract BaseDao getDao();

    public UUID create(T t) {
        return getDao().create(t);
    }

    public UUID update(T t) {
        return getDao().update(t);
    }

    public void delete(UUID uuid) {
        getDao().delete(uuid);
    }

    public List<T> list() {
        return getDao().list();
    }

}
