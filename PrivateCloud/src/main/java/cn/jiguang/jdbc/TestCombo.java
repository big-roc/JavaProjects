package cn.jiguang.jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;

public class TestCombo {
    private ComboPooledDataSource dataSource;

    private TestCombo() {
        try {
            dataSource = new ComboPooledDataSource();
            dataSource.setUser("root");
            dataSource.setPassword("csc_stats");
            dataSource.setJdbcUrl("jdbc:mysql://172.17.8.18:3306/test?useUnicode=true&charcterEncoding=utf-8");
            dataSource.setDriverClass("com.mysql.jdbc.Driver");
//            dataSource.setInitialPoolSize();
//            dataSource.setAcquireIncrement();
//            dataSource.setMinPoolSize();
//            dataSource.setMaxPoolSize();
//            dataSource.setMaxStatements();
//            dataSource.setMaxIdleTime();
//            dataSource.setIdleConnectionTestPeriod();
//            dataSource.setAcquireRetryAttempts();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (Exception e) {
            throw new RuntimeException("can not get sms database connection ", e);
        }
    }
}
