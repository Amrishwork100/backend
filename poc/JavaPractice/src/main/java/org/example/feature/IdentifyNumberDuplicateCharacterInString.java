package org.example.feature;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IdentifyNumberDuplicateCharacterInString {

    public static void main(String[] args) {
        String str ="abcbadbaf";
        Character[] strArray = null;
        for (int i=0; i<str.length();i++){
            strArray = new Character[]{str.charAt(i)};
        }

        Map<Character, Long>  map = Stream.of(strArray)
                .collect(Collectors
                        .groupingBy(Function.identity(), Collectors.counting()));
        map.entrySet().forEach(System.out::println);
    }
}
