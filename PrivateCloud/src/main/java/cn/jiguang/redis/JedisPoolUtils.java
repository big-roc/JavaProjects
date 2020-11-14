package cn.jiguang.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JedisPoolUtils {
    private static JedisPool pool = null;

    static {
        //加载配置文件
        InputStream in = JedisPoolUtils.class.getClassLoader().getResourceAsStream("redis.properties");
        Properties pro = new Properties();
        try {
            pro.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 获得池子对象
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(Integer.parseInt(pro.get("redis.maxIdle").toString()));
        poolConfig.setMaxWaitMillis(Integer.parseInt(pro.get("redis.maxWait").toString()));
        poolConfig.setMinIdle(Integer.parseInt(pro.get("redis.minIdle").toString()));
        poolConfig.setMaxTotal(Integer.parseInt(pro.get("redis.maxTotal").toString()));

        String host = pro.getProperty("redis.host");
        Integer port = Integer.parseInt(pro.get("redis.port").toString());
        pool = new JedisPool(poolConfig, host, port);
    }

    //获得jedis资源的方法
    static Jedis getJedis() {
        return pool.getResource();
    }

    public static void main(String[] args) {
        Jedis jedis = getJedis();
        System.out.println(jedis);
    }
}
