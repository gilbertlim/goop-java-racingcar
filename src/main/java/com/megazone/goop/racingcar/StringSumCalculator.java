package com.megazone.goop.racingcar;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringSumCalculator {

    private static final String DEFAULT_DELIMITERS = "[,|:]";

    private String[] split(String text) {
        if (text == null) {
            return new String[] {};
        }

        Matcher m = Pattern.compile("//(.)\n(.*)").matcher(text);
        if (m.find()) {
            return m.group(2).split(DEFAULT_DELIMITERS.replace("]", "|" + m.group(1) + "]"));
        }

        return text.split(DEFAULT_DELIMITERS);
    }

    public int splitAndSum(String text) throws Exception{
        return Arrays.stream(split(text))
            .filter(n -> !n.isEmpty())
            .mapToInt(Integer::parseInt)
            .map(n -> {
                if (n < 0) {
                    throw new RuntimeException("Negative numbers are not allowed.");
                }
                return n;
            }).sum();
    }
}