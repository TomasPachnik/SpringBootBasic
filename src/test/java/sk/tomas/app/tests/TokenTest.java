package sk.tomas.app.tests;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sk.tomas.app.dao.TokenDao;
import sk.tomas.app.model.TokenEntity;

/**
 * Created by Tomas Pachnik on 18-Jan-17.
 */
public class TokenTest extends BaseTest {

    @Autowired
    TokenDao tokenDao;

    @Test
    public void createTokenTest() {
        //vytvorim rolu
        TokenEntity tokenEntity = new TokenEntity("token", 1L, "login");
        tokenDao.create(tokenEntity);
        TokenEntity byValue = tokenDao.findByValue("login", "login");
        Assert.assertTrue("Token nevytvoreny", tokenEntity.getToken().equals(byValue.getToken()));
    }

}
