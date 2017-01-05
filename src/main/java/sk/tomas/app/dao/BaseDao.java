package sk.tomas.app.dao;

import org.hibernate.Session;
import sk.tomas.app.model.base.Entity;

import java.util.List;

/**
 * Created by Tomas Pachnik on 04-Jan-17.
 */
public interface BaseDao<T extends Entity> {

    int create(T t);

    int update(T t);

    void delete(int id);

    List<T> list();

    T findByValue(String key, String value);
}
