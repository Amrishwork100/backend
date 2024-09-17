package com.itech.login.utils;

public class GenericClass<T> {
    T t1Obj;

    public GenericClass(){
    }
    public GenericClass(T t1Obj){
        this.t1Obj=t1Obj;
    }

    public void add(T t){
        this.t1Obj=t;
    }

    public T get(){
        return t1Obj;
    }
}
