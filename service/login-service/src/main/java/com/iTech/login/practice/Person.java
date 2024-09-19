package com.itech.login.practice;

import lombok.Data;

import java.util.List;

@Data
public class Person {

    private String name;

    private List<String>  languages;

    public <T> Person(String name, List<String> languages) {
        this.languages=languages;
        this.name=name;
    }
}
