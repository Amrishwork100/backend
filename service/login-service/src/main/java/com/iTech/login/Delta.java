package com.itech.login;

import com.itech.login.utils.GenericClass;

import javax.crypto.spec.PSource;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Delta {

    public static void main(String[] args) {

        Student1 s11 = new Student1();
        s11.setName("Amrish");
        s11.setId(1);

        Student1 s12 = new Student1();
        s12.setName("AAmrish");
        s12.setId(1);

        System.out.println("S11 {} : " + s11.hashCode());
        System.out.println("S12 {} : " + s12.hashCode());
        System.out.println("Equality check " + s11.equals(s12));


        List<Integer> list1 = Arrays.asList(4, 9, 5, 2, 8, 6, 1, 1, 2);

        List<Integer> output1 = list1.stream().sorted().toList();
        System.out.println("Sort the obj using stream sorted method : ");
        System.out.println(output1);
        System.out.println("<<==== end  ===>>>>");

        List<Integer> output2 = list1.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().filter(n -> n.getValue() > 1)
                .map(Map.Entry::getKey)
                .toList();
        System.out.println("Get duplicate obj by using map and groupBy : ");
        System.out.println(output2);
        System.out.println("<<==== end  ===>>>>");

        Set<Integer> set = new HashSet<>();
        List<Integer> output3 = list1.stream().filter(n -> !set.add(n)).toList();
        System.out.println("Get duplicate obj by using set : ");
        System.out.println(output3);
        System.out.println("<<==== end  ===>>>>");


        Student1 s13 = new Student1(1, "Rahul", 20, 10000);
        Student1 s14 = new Student1(2, "Aakash", 24, 15000);
        Student1 s15 = new Student1(3, "Sasank", 21, 12000);
        Student1 s16 = new Student1(4, "Abhijit", 19, 5000);
        Student1 s17 = new Student1(5, "Venkata", 22, 20000);
        List<Student1> student1Obj = Arrays.asList(s13, s14, s15, s16, s17);

        /* MaxBy and MinBy */
        Optional<Student1> maxBy = student1Obj.stream()
                .filter(s -> s.getAge() > 20)
                .max(Comparator.comparing(Student1::getFee));
        System.out.println("Max Fee value by collectors maxBy method : ");
        System.out.println(maxBy.orElse(null));
        System.out.println("<<==== end  ===>>>>");

        Optional<Student1> minBy = student1Obj.stream()
                .filter(s -> s.getAge() > 20)
                .collect(Collectors.minBy(Comparator.comparing(Student1::getFee)));
        System.out.println("Min Fee value by collectors minBy method :  ");
        System.out.println(minBy.orElse(null));
        System.out.println("<<==== end  ===>>>>");

        // sort the pojo object -> sorted, comparator
        List<Student1> result = student1Obj.stream().sorted(Comparator.comparing(Student1::getAge)).toList();
        System.out.println("Sort stu by fee by using sorted and comparator method : ");
        result.forEach(System.out::println);
        System.out.println("<<==== end  ===>>>>");

        Map<Integer, List<Student1>> integerMap = student1Obj.stream().collect(Collectors.groupingBy(Student1::getAge));
        integerMap.forEach((k, v) -> System.out.println(k + "" + v));
        System.out.println("<<==== end  ===>>>>");


        Optional<Integer> integer = student1Obj.stream().map(Student1::getAge).max(Integer::compareTo);
        System.out.println("Find the Max age : ");
        System.out.println(integer.get());
        System.out.println("<<==== end  ===>>>>");
        // palindrome example
        List<String> words = Arrays.asList("radar", "hello", "deified");
        List<String> palindromes = words.stream()
                .filter(s -> s.equals(new StringBuilder(s).reverse().toString()))
                .toList();
        System.out.println("Find the Palindrome : ");
        System.out.println(palindromes);

        System.out.println("Example of Generic class in Java : ");
        GenericClass<String> genericClass = new GenericClass<>();
        genericClass.add("Hello world");
        String str = genericClass.get();
        System.out.println(str);

        GenericClass<Integer>  genericClass1= new GenericClass<>();
        genericClass1.add(12345);
        Integer integer1 = genericClass1.get();
        System.out.println(integer1);
        System.out.println("<<==== end  ===>>>>");




    }
}
