package sk.tomas.app.dao.impl;

import org.springframework.stereotype.Repository;
import sk.tomas.app.dao.TokenDao;
import sk.tomas.app.model.TokenEntity;
import sk.tomas.app.orm.TokenNode;

/**
 * Created by Tomas Pachnik on 18-Jan-17.
 */

@Repository
public class TokenDaoImpl extends BaseDaoImpl<TokenEntity, TokenNode> implements TokenDao {

    TokenDaoImpl() {
        super(TokenEntity.class, TokenNode.class);
    }
}
