package com.itech.login.practice;

import lombok.Data;

import java.util.Objects;

@Data
public class Student1 {
    private int id;
    private String name;
    private int age;
    private long fee;

    public Student1() {
    }
    public Student1(int id, String name, int age, long fee) {
        this.id = id;
        this.name=name;
        this.age = age;
        this.fee = fee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student1 student1 = (Student1) o;
        return id == student1.id
                && age == student1.age
                && fee == student1.fee
                && Objects.equals(name, student1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, fee);
    }
}
