package com.ysx.create.prototype;

import java.io.*;

/**
 * <b>原型对象-深拷贝</b>
 * <ol>
 *     <li>覆写clone的方式实现深拷贝</li>
 *     <li>序列化的方式实现深拷贝</li>
 * </ol>
 */
public class Prototype implements Serializable, Cloneable {

    private static final long serialVersionUID = -155958503167338162L;

    // 基本数据类型
    private int number;

    // 字符串
    private String name;

    // 对象数据类型
    private Inner inner;

    public Inner getInner() {
        return inner;
    }

    public void setInner(Inner inner) {
        this.inner = inner;
    }

    @Override
    public String toString() {
        return "Prototype{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", inner=" + inner +
                '}';
    }

    public Prototype(int number, String name) {
        this.number = number;
        this.name = name;
        this.inner = new Inner(100, "Inner");
    }

    /**
     * 覆写clone的方式实现深拷贝
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Prototype prototype = (Prototype) super.clone();
        // 深拷贝,该方式对于嵌套对象不是特别友好,需要手动递归实现
        prototype.setInner( prototype.getInner().clone() );
        return prototype;
    }

    /**
     * 序列化的方式实现深拷贝
     */
    public Object serialize() {
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        try {
            // 对象序列化
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(this);

            // 对象反序列化
            bis = new ByteArrayInputStream( bos.toByteArray() );
            ois = new ObjectInputStream(bis);

            // 返回克隆对象
            return ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if ( ois != null ) {
                    ois.close();
                }
                if ( bis != null ) {
                    bis.close();
                }
                if ( oos != null ) {
                    oos.close();
                }
                if ( bos != null ) {
                    bos.close();
                }
            } catch (Exception ignore) {}
        }
    }

    /**
     * 内部类
     */
    public static class Inner implements Serializable, Cloneable {

        private static final long serialVersionUID = 8628761977615909129L;

        // 基本数据类型
        private int number;

        // 字符串
        private String name;

        public Inner(int number, String name) {
            this.number = number;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Inner{" +
                    "number=" + number +
                    ", name='" + name + '\'' +
                    '}';
        }

        @Override
        public Inner clone() {
            try {
                return (Inner) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new AssertionError();
            }
        }
    }
}
