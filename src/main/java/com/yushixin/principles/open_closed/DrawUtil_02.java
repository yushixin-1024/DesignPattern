package com.yushixin.principles.open_closed;

public class DrawUtil_02 {

    public static void main(String[] args) {
        PaintBrush brush = new PaintBrush();
        brush.draw( new Triangle() );
        brush.draw( new Rectangle() );
    }

    static class PaintBrush {
        // 使用方（对修改关闭）
        public void draw(Shape shape) {
            shape.draw();
        }
    }

    // 提供方（对扩展开放）
    static abstract class Shape {
        // 抽象方法,由子类实现细节
        public abstract void draw();
    }
    static class Triangle extends Shape {
        @Override
        public void draw() {
            System.out.println("画三角形");
        }
    }
    static class Rectangle extends Shape {
        @Override
        public void draw() {
            System.out.println("画长方形");
        }
    }
    //TODO 扩展类
}
