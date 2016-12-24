package sk.tomas.app.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sk.tomas.app.bo.Key;
import sk.tomas.app.dao.KeyDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by tomas on 23.12.2016.
 */
@Repository
public class KeyDaoImpl implements KeyDao {
    static Logger log = LoggerFactory.getLogger(KeyDaoImpl.class);

    @Autowired
    private DataSource dataSource;

    @Override
    public List<Key> getAll() {
        List<Key> resultList = new LinkedList<>();
        String sql = "select * FROM configuration";
        Connection conn;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Key key = new Key(
                        rs.getInt("ID"),
                        rs.getString("NAME"),
                        rs.getString("VALUE")
                );
                resultList.add(key);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return resultList;
    }

    @Override
    public String getValue(String name) {
        return null;
    }
}
