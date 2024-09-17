package org.example.feature;

import org.example.model.Employee;

import java.util.*;
import java.util.function.Function;

public class SortingUsingJava8 {

    public static void main(String[] args) {
        Employee employee1 = new Employee(1, "rahul", 20);
        Employee employee2 = new Employee(3, "Amrish", 30);
        Employee employee3 = new Employee(5, "Hari", 40);
        Employee employee4 = new Employee(9, "Ronit", 25);
        Employee employee5 = new Employee(7, "Ishan", 50);

        List<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
        employees.add(employee4);
        employees.add(employee5);

        List<Employee> sortbyIdlist = employees.stream().sorted(Comparator.comparing(Employee::getId))
                .toList();
        sortbyIdlist.forEach(System.out::println);

        List<Employee> sortbyNamelist = employees.stream().sorted(Comparator.comparing(Employee::getName))
                .toList();
        System.out.println("-----------------------------------------------");
        sortbyNamelist.forEach(System.out::println);



    }


}
