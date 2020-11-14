package cn.jiguang.jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.SQLException;

public class ComboDemo {

    public static void main(String[] args) {
        ComboPooledDataSource pool = new ComboPooledDataSource("gltest");
        try {
            pool.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
