package com.shirs.agileboot.modules.study.DesignPatterns.prototype;

import java.util.ArrayList;
import java.util.List;

public class ShallowClone implements Cloneable {

    private List<String> nameList = new ArrayList<>();

    int age = 24;

    String address = "西安";

    public void addName(String name){
        nameList.add(name);
    }

    public void printNames(){
        for (String name:nameList){
            System.out.println(name);
        }
    }

    public void changeAge(int age){
        this.age = age;
    }

    public void changeAddress(String address){
        this.address = address;
    }

    protected ShallowClone clone() throws CloneNotSupportedException {
        ShallowClone clone = (ShallowClone) super.clone();
        return clone;
    }
}
