package com.itech.login.tech;

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
        Employee employee = new Employee(1, "Rahul", 30, "Technology");
        List<Employee> first = new ArrayList<>();
        first.add(employee);
        Map<String,List<Employee>> grp = first.stream().collect(Collectors.groupingBy(Employee::getDeptName));

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
        System.out.println(newList);


    }


}

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
class  Employee{

    private int id;
    private String name;
    private int age;
    private String deptName;

}
