package org.example.tech;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Demo {
    public static void main(String[] args) {
        // Question 3
        Employee employee1 = new Employee(1, "A", 30, "J");
        Employee employee2 = new Employee(2, "B", 40, "P");
        Employee employee3 = new Employee(3, "C", 25, "J");
        List<Employee> first = new ArrayList<>();
        first.add(employee1);
        first.add(employee2);
        first.add(employee3);
        Map<String,List<Employee>> grp = first.stream().collect(Collectors.groupingBy(Employee::getDeptName));

        System.out.println(grp);
        List<List<Employee>> second = new ArrayList<>();
        second.add(first);
        List<List<List<Employee>>> emps = new ArrayList<>();
        emps.add(second);

        // Question 4
        List<Employee> newList = emps.stream()
                .flatMap(l ->
                        l.stream()
                                .flatMap(Collection::stream))
                .toList();
        //System.out.println(newList);


    }


}

