package sk.tomas.app.service;

import sk.tomas.app.model.Identity;
import sk.tomas.app.model.Key;

import java.util.List;

/**
 * Created by tomas on 23.12.2016.
 */
public interface IdentityService {

    List<Key> getKeys();

    int create(Identity identity);

    Identity findBySurname(String surName);

    int update(Identity identity);

    void delete(int id);

    List<Identity> list();

}
