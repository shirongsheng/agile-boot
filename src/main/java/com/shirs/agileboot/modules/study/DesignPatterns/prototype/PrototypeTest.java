package com.shirs.agileboot.modules.study.DesignPatterns.prototype;

public class PrototypeTest {
    public static void main(String[] args) {
        ShallowClone origin = new ShallowClone();
        try {
            ShallowClone shallowClone = origin.clone();
            origin.addName("shirs");
            shallowClone.addName("shirs_v2");
            origin.printNames();
            origin.changeAge(30);
            origin.changeAddress("深圳");
            System.out.println("origin.age:"+origin.age);
            System.out.println("origin.address:"+origin.address);
            System.out.println("--------");
            shallowClone.printNames();
            System.out.println("shallowClone.age:"+shallowClone.age);
            System.out.println("shallowClone.address:"+shallowClone.address);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
