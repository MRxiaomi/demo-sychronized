package com.example.demo.jpa;

import javax.persistence.Entity;

/**
 * Created by liuyumeng on 2018/2/10.
 */
@Entity
public class Person {
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

    @Override
    public String toString() {
        return id+":"+name;
    }
}
