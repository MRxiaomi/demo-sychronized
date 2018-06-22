package com.example.demo.rpc.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.example.demo.rpc.java.bean.Student;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by liuyumeng on 2018/5/25.
 */
public class BaseDemo {
    public static void main(String[] args) {
        Kryo kryo = new Kryo();

        Student s = new Student("Kryo序列化");
        ObjectOutputStream objectOutputStream = null;
        try {
            Output output = new Output(new FileOutputStream("/tmp/student.db"));
            kryo.writeObject(output, s);
            output.close();

            Input input = new Input(new FileInputStream("/tmp/student.db"));
            Student student = (Student) kryo.readObject(input, Student.class);
            input.close();
            //不要在断言里写业务代码
            assert "java序列化1".equals(student.getName()) : "不相等";
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
