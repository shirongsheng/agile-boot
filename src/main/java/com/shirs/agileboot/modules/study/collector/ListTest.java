package com.shirs.agileboot.modules.study.collector;

import com.shirs.agileboot.modules.study.entity.Person;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ListTest {
    public static void main(String[] args) {
        List<String> strList = Arrays.asList("tom","bob","july");
        String collect = strList.stream().collect(Collectors.joining());
        System.out.println(collect);
        collect = strList.stream().collect(Collectors.joining(","));
        System.out.println(collect);
        collect = strList.stream().collect(Collectors.joining("-"));
        System.out.println(collect);
        collect = strList.stream().collect(Collectors.joining("-","{","]"));
        System.out.println(collect);
        List<Person> list = Person.getPersonList();
        System.out.println("--Join person name--");
        String result=  list.stream().map(p -> p.getName()).collect(Collectors.joining());
        System.out.println(result);
        result=  list.stream().map(p -> p.getName()).collect(Collectors.joining("|"));
        System.out.println(result);
        result=  list.stream().map(p -> p.getName()).collect(Collectors.joining("-","[","]"));
        System.out.println(result);

        System.out.println("--Join person age--");
        result=  list.stream().map(p -> String.valueOf(p.getAge())).collect(Collectors.joining());
        System.out.println(result);
        result=  list.stream().map(p -> String.valueOf(p.getAge())).collect(Collectors.joining("|"));
        System.out.println(result);
        result=  list.stream().map(p -> String.valueOf(p.getAge())).collect(Collectors.joining("-","[","]"));
        System.out.println(result);

        System.out.println("--Join person name-age--");
        result=  list.stream().map(p -> p.getName()+"-" + p.getAge()).collect(Collectors.joining("|"));
        System.out.println(result);
        result=  list.stream().map(p -> p.getName()+"-" + p.getAge()).collect(Collectors.joining("|","[","]"));
        System.out.println(result);
    }
}
