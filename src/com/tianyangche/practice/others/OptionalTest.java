package com.tianyangche.practice.others;

import java.util.Optional;

/**
 * Created by tianyangche on 4/6/16.
 */
public class OptionalTest {
    public static void main(String[] args) {
        String input = "123";
        Optional<String> optional = Optional.ofNullable(input);
        optional.ifPresent(System.out::print);

    }
}
