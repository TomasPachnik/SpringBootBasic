package sk.tomas.app.dao;

import sk.tomas.app.bo.Key;

import java.util.List;

/**
 * Created by tomas on 23.12.2016.
 */
public interface KeyDao {

    List<Key> getAll();

    String getValue(String name);

}
