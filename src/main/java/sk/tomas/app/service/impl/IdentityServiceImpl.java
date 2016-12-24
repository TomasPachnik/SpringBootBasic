package sk.tomas.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.tomas.app.bo.Key;
import sk.tomas.app.dao.KeyDao;
import sk.tomas.app.service.IdentityService;

import java.util.List;

/**
 * Created by tomas on 23.12.2016.
 */
@Service
public class IdentityServiceImpl implements IdentityService {

    @Autowired
    private KeyDao keyDao;

    @Override
    public List<Key> getKeys() {
        return keyDao.getAll();
    }
}
