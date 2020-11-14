package cn.jiguang.redis;

import redis.clients.jedis.Jedis;

import java.util.List;

public class MessageConsumer implements Runnable {
    public static final String MESSAGE_KEY = "msg:dapeng_test";
    private Jedis jedis = JedisPoolUtils.getJedis();

    public void consumerMessage() {
        //0是timeout,返回的是一个集合，第一个是消息的key，第二个是消息的内容
        List<String> brpop = jedis.brpop(10, MESSAGE_KEY);
        System.out.println(brpop);
    }

    @Override
    public void run() {
        while (true) {
            consumerMessage();
        }
    }

    public static void main(String[] args) {
        MessageConsumer messageConsumer = new MessageConsumer();
        Thread t1 = new Thread(messageConsumer, "thread6");
        t1.start();
    }
}