package cn.jiguang.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDao {

    private static Logger logger = LoggerFactory.getLogger(JDBCDao.class);
    private static Connection connection = null;
    private static JDBCFactory jdbcFactory;

    public JDBCDao(String dbName) {
        try {
            jdbcFactory = new JDBCFactory(dbName);
            connection = JDBCFactory.getConnection();
        } catch (SQLException e) {
            logger.error("init JDBC connect error.");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
