package cn.jiguang.design.pattern.singleton;

public class Singleton00 {
    private static Singleton00 instance;

    private Singleton00() {
    }

    public static Singleton00 getInstance() {
        if (instance == null) {
            instance = new Singleton00();
        }
        return instance;
    }
}

