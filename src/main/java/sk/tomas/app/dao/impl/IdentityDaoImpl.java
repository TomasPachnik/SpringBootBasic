package sk.tomas.app.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import sk.tomas.app.dao.IdentityDao;
import sk.tomas.app.model.Identity;
import sk.tomas.app.orm.IdentityNode;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Created by tomas on 24.12.2016.
 */

@Repository
public class IdentityDaoImpl extends BaseDaoImpl<Identity, IdentityNode> implements IdentityDao {

    private static Logger loger = LoggerFactory.getLogger(IdentityDaoImpl.class);

    @Resource
    private DataSource dataSource;

    public IdentityDaoImpl() {
        super(Identity.class, IdentityNode.class);
    }

    @Override
    public boolean hasRole(UUID identityUuid, UUID roleUuid) {
        String hql = "SELECT i.login FROM Identity i " +
                "JOIN role_identity ri ON i.uuid = ri.identity_uuid " +
                "JOIN Role r ON ri.role_uuid = r.uuid " +
                "WHERE i.uuid = ? and r.uuid = ?";
        Connection connection;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(hql);
            preparedStatement.setString(1, identityUuid.toString());
            preparedStatement.setString(2, roleUuid.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            loger.error("hasRole: " + e);
        }
        return false;
    }
}
