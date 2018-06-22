package com.example.demo.rpc.java.demos;

import com.example.demo.rpc.java.bean.Student;

import java.io.*;

/**
 * Created by liuyumeng on 2018/5/25.
 */
public class Demo {
    public static void main(String[] args) {
        Student s = new Student("java序列化");
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream("/tmp/student.db"));
            objectOutputStream.writeObject(s);
            objectOutputStream.close();

            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("/tmp/student.db"));
            try {
                Student student = (Student) objectInputStream.readObject();
                objectInputStream.close();

                //不要在断言里写业务代码
                assert "java序列化".equals(student.getName()):"不相等";
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
