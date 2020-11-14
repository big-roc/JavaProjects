package cn.jiguang.design.pattern.factory;

public class FactoryPatternDemo {
    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();

        // 或者Circle的对象，并调用它的draw方法
        Shape shape1 = shapeFactory.getShape("CIRCLE");
        shape1.draw();

        //获取Rectangle的对象，并调用它的draw 方法
        Shape shape2 = shapeFactory.getShape("RECTANGLE");
        shape2.draw();

        //获取Square的对象，并调用它的draw方法
        Shape shape3 = shapeFactory.getShape("SQUARE");
        shape3.draw();
    }
}
