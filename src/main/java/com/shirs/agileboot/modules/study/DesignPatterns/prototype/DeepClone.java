package com.shirs.agileboot.modules.study.DesignPatterns.prototype;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DeepClone implements Serializable {

    private List<String> nameList = new ArrayList<>();

    int age = 24;

    String address = "西安";

    private String name = "shirs";

    public DeepClone deepClone() throws IOException, ClassNotFoundException {
        //将对象写到流里
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(this);
        //从流里读回来
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return (DeepClone) ois.readObject();
    }

    public void addName(String name) {
        nameList.add(name);
    }

    public void printNames() {
        for (String name : nameList) {
            System.out.println(name);
        }
        System.out.println(name);
        System.out.println(age);
    }
}
