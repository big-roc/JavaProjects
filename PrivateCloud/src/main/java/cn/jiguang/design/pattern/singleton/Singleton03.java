package cn.jiguang.design.pattern.singleton;

public class Singleton03 {
    private volatile static Singleton03 singleton03;

    private Singleton03() {
    }

    public static Singleton03 getSingleton03() {
        if (singleton03 == null) {
            synchronized (Singleton03.class) {
                if (singleton03 == null) {
                    singleton03 = new Singleton03();
                }
            }
        }
        return singleton03;
    }
}
