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


        System.out.println("---------------------------");
        try {
            //创建一个原始对象并添加一个名字
            DeepClone originalObject = new DeepClone();
            originalObject.addName("yasuo");

            //克隆一个新对象并添加一个名字
            DeepClone cloneObject = originalObject.deepClone();
            cloneObject.addName("timo");

            //打印原始对象和新对象的name
            originalObject.printNames();
            System.out.println("-----------");
            cloneObject.printNames();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
