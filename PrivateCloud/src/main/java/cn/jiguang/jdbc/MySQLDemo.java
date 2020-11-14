package cn.jiguang.jdbc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.sql.*;

public class MySQLDemo {
    // JDBC驱动名及数据库URL
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://172.17.8.18:3306/test?useUnicode=true&amp;rewriteBatchedStatements=true&amp;characterEncoding=utf-8&amp;autoReconnect=true&amp;failOverReadOnly=false";

    // 数据库用户名和密码
    private static final String USER = "root";
    private static final String PASS = "csc_stats";

    public static void main(String[] args) {
        Connection conn = null;
        Statement statement = null;

        try {
            // 注册JDBC驱动器
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            statement = conn.createStatement();
            String sql;
            sql = "SELECT DATA FROM conf WHERE component = 'redis';";
            ResultSet rs = statement.executeQuery(sql);

            // 展开结果集
            while (rs.next()) {
                // 打印数据
                System.out.println(rs.getString("data"));

                // 解析JSON
                String res = rs.getString("data");
                JSONObject obj = JSON.parseObject(res);
                String name = obj.getString("name");
                System.out.println(name);
                Integer age = obj.getInteger("age");
                System.out.println(age);
            }

            // 完成后关闭
            rs.close();
            statement.close();
            conn.close();
        } catch (Exception e) {
            // 处理JDBC错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

    }

    public void json() {

    }
}