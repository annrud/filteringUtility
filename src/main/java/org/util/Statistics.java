package org.util;

import java.util.List;

/**
 * Класс Statistics отвечает за вывод статистики по каждому типу отфильтрованных данных в консоль.
 */

public class Statistics {
    public static void printStatistics(List<String> dataList, String dataType, boolean fullStatistic){
        System.out.printf("Statistics of %s:%nCount: %d%n", dataType, dataList.size());
        if (fullStatistic) {
            switch (dataType) {
                case "integers" -> printIntegerStatistics(dataList);
                case "floats" -> printFloatStatistics(dataList);
                case "strings" -> printStringStatistics(dataList);
            }
        }
        System.out.println();
    }
    private static void printIntegerStatistics(List<String> dataList){
        List<Long> integerList = dataList.stream().map(Long::parseLong).toList();
        long min = integerList.stream().min(Long::compareTo).orElse(0L);
        long max = integerList.stream().max(Long::compareTo).orElse(0L);
        long sum = integerList.stream().mapToLong(Long::longValue).sum();
        double average = integerList.stream().mapToDouble(Long::doubleValue).average().orElse(0);
        System.out.println("Min: " + min);
        System.out.println("Max: " + max);
        System.out.println("Sum: " + sum);
        System.out.println("Average: " + average);
    }

    private static void printFloatStatistics(List<String> dataList){
        List<Float> floatList = dataList.stream().map(Float::parseFloat).toList();
        float min = floatList.stream().min(Float::compareTo).orElse(0f);
        float max = floatList.stream().max(Float::compareTo).orElse(0f);
        float sum = (float) floatList.stream().mapToDouble(Float::doubleValue).sum();
        float average = (float) floatList.stream().mapToDouble(Float::doubleValue).average().orElse(0f);
        System.out.println("Min: " + min);
        System.out.println("Max: " + max);
        System.out.println("Sum: " + sum);
        System.out.println("Average: " + average);
    }

    private static void printStringStatistics(List<String> dataList){
        int minLength = dataList.stream().map(String::length).min(Integer::compareTo).orElse(0);
        int maxLength = dataList.stream().map(String::length).max(Integer::compareTo).orElse(0);
        System.out.println("Min length: " + minLength);
        System.out.println("Max length: " + maxLength);
    }
}
