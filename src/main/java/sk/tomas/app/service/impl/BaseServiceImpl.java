package sk.tomas.app.service.impl;

import sk.tomas.app.dao.BaseDao;
import sk.tomas.app.exception.InputValidationException;
import sk.tomas.app.model.base.Entity;
import sk.tomas.app.service.BaseService;

import java.util.List;
import java.util.UUID;

/**
 * Created by Tomas Pachnik on 05-Jan-17.
 */

public abstract class BaseServiceImpl<T extends Entity> implements BaseService<T> {

    protected abstract BaseDao getDao();

    @SuppressWarnings("unchecked")
    public UUID create(T t) {
        return getDao().create(t);
    }

    @SuppressWarnings("unchecked")
    public UUID update(T t) {
        return getDao().update(t);
    }

    public void delete(UUID uuid) throws InputValidationException {
        getDao().delete(uuid);
    }

    @SuppressWarnings("unchecked")
    public List<T> list() {
        return getDao().list();
    }

    @SuppressWarnings("unchecked")
    public List<T> list(int firstResult, int maxResult, String orderBy, boolean desc) {
        return getDao().list(firstResult, maxResult, orderBy, desc);
    }

    public long count() {
        return getDao().count();
    }

    @SuppressWarnings("unchecked")
    public T findByUuid(UUID uuid) {
        return (T) getDao().findByUuid(uuid);
    }
}
