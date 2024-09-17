package org.example.tech;

import java.util.List;
import java.util.stream.Stream;

public class Testing {
    public static void main(String[] args) {
        String[] s1 = new String[]{"VISA CARD", "MASTER CARD", "VISA CARD", "SIGNATURE CARD"};
        List<String> strings = Stream.of(s1)
                .filter(description -> description.equalsIgnoreCase("VISA CARD"))
                .toList();
        strings.forEach(System.out::println);
    }
}
