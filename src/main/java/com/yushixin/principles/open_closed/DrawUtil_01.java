package com.yushixin.principles.open_closed;

public class DrawUtil_01 {

    public static void main(String[] args) {
        PaintBrush brush = new PaintBrush();
        brush.draw( new Triangle() );
        brush.draw( new Rectangle() );
    }

    static class PaintBrush {
        // 使用方（对修改关闭）
        public void draw(Shape shape) {
            switch (shape.type) {
                case 1:
                    drawTriangle();
                    break;
                case 2:
                    drawRectangle();
                    break;
                //TODO 扩展分支
            }
        }
        private void drawTriangle() {
            System.out.println("画三角形");
        }
        private void drawRectangle() {
            System.out.println("画长方形");
        }
        //TODO 扩展方法
    }

    // 提供方（对扩展开放）
    static class Shape {
        public int type;
    }
    static class Triangle extends Shape {
        public Triangle() {
            super.type = 1;
        }
    }
    static class Rectangle extends Shape {
        public Rectangle() {
            super.type = 2;
        }
    }
    //TODO 扩展类
}
