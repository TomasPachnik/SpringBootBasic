package sk.tomas.app.dao;

import sk.tomas.app.model.Identity;

import java.util.List;

/**
 * Created by tomas on 24.12.2016.
 */
public interface IdentityDao {

    int save(Identity identity);

    int update(Identity identity);

    void delete(int id);

    List<Identity> list();

}
