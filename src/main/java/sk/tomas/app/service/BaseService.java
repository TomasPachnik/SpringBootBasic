package sk.tomas.app.service;

import sk.tomas.app.model.base.Entity;

import java.util.List;
import java.util.UUID;

/**
 * Created by Tomas Pachnik on 05-Jan-17.
 */
public interface BaseService<T extends Entity> {

    UUID create(T t);

    UUID update(T t);

    void delete(UUID uuid);

    List<T> list();
}
