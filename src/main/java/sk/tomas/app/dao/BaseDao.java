package sk.tomas.app.dao;

import sk.tomas.app.model.base.Entity;

import java.util.List;
import java.util.UUID;

/**
 * Created by Tomas Pachnik on 04-Jan-17.
 */
public interface BaseDao<T extends Entity> {

    UUID create(T t);

    UUID update(T t);

    void delete(UUID uuid);

    List<T> list();

    T findByValue(String key, String value);
}
