package cn.jiguang.design.pattern.singleton;

public class Singleton04 {
    private static class SingletonHolder {
        private static final Singleton04 INSTANCE = new Singleton04();
    }

    private Singleton04() {
    }

    public static final Singleton04 getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
