package com.example.demo.serialize;

import java.io.*;

/**
 * Created by liuyumeng on 2018/9/29.
 * <p>
 * JAVA对象序列化 写盘 106字节
 */
public class JavaSerialize {
    public static void main(String[] args) throws IOException {
        //test1();
        test2();
    }

    /**
     * JAVA对象序列化 写盘 106字节
     */
    private static void test1() throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File("/tmp/person")));

        Person person = new Person();

        objectOutputStream.writeObject(person);
    }

    /**
     * 输出为字节数组
     */
    private static void test2() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(new Person());
        objectOutputStream.flush();

        byte[] bytes =  byteArrayOutputStream.toByteArray();
        for (int i = 0; i < bytes.length; i++) {
            System.out.printf(String.valueOf(bytes[i]));
        }
        System.out.printf("");
        System.out.printf(byteArrayOutputStream.toString());
    }

    static class Person implements Serializable {
        private int id;

        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
