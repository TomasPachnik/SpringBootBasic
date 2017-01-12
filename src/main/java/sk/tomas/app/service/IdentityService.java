package sk.tomas.app.service;

import sk.tomas.app.model.Identity;
import sk.tomas.app.model.Key;

import java.util.List;

/**
 * Created by tomas on 23.12.2016.
 */
public interface IdentityService extends BaseService<Identity> {

    List<Key> getKeys();

    Identity findBySurname(String surName);

    Identity findByLogin(String login);



}
