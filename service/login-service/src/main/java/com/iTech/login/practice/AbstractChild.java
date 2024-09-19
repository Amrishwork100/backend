package com.itech.login.practice;

public class AbstractChild extends MyAbstractClass {
    AbstractChild(){
        //super();
    }
    @Override
    public void m2() {
        System.out.println("I am m2() method of MyAbstractClass");

    }

    public static void main(String[] args) {
        AbstractChild ac = new AbstractChild();
        MyAbstractClass mac = new AbstractChild();
        mac.m1();
        mac.m2();
        ac.m1();
        ac.m2();
    }
}
