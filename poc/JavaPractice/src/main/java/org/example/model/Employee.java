package org.example.model;

import jdk.jfr.DataAmount;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Employee {
    private  int id;
    private String name;
    private  int age;
}
