package cn.jiguang.jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCFactory {
    private static Logger logger = LoggerFactory.getLogger(JDBCFactory.class);

    private static DataSource dataSource;
    private static String dbName = "default";
    private static Connection connection;

    public JDBCFactory() {
        dataSource = new ComboPooledDataSource();
    }

    public JDBCFactory(String dbName) {
        JDBCFactory.dbName = dbName;
        dataSource = new ComboPooledDataSource(JDBCFactory.dbName);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void close(Connection conn, Statement stam, ResultSet res) {
        if (res != null) {
            try {
                res.close();
            } catch (SQLException e) {
                logger.error("JDBCUtil close res error : " + e);
                e.printStackTrace();
            }
        }

        if (stam != null) {
            try {
                stam.close();
            } catch (SQLException e) {
                logger.error("JDBCUtil close stam error : " + e);
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                logger.error("JDBCUtil close conn error : " + e);
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws SQLException {
        JDBCFactory jdbcFactory = new JDBCFactory("gltest");
        Connection conn = getConnection();
        conn.close();
    }
}
