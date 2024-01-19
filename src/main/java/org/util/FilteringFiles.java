package org.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Класс FilteringFiles отвечает за фильтрацию данных из нескольких файлов.
 * Данные, в зависимости от типа, добавляются в соответствующие списки.
 */
public class FilteringFiles {
    private List<String> inputFiles;
    private List<String> integerList = new ArrayList<>();
    private List<String> floatList = new ArrayList<>();
    private List<String> stringList = new ArrayList<>();

    public FilteringFiles(List<String> inputFiles) {
        this.inputFiles = inputFiles;
    }

    /**
     * Метод dataFilter проходит построчно по каждому переданному в конструкторе файлу,
     * и в зависимости от того может ли строка быть преобразована в целый числовой тип или с плавающей точкой,
     * распределяет строки по трем спискам integerList, floatList и stringList.
     */
    public void dataFilter() {
        for (String inputFile : inputFiles) {
            try (Scanner scanner = new Scanner(new File(inputFile))) {
                while (scanner.hasNext()) {
                    String token = scanner.nextLine();
                    if (isInteger(token)) {
                        integerList.add(token);
                    } else if (isFloat(token)) {
                        floatList.add(token);
                    } else {
                        stringList.add(token);
                    }
                }
            } catch (FileNotFoundException e) {
                System.err.println("File not found: " + inputFile);
            }
        }
    }

    private static boolean isInteger(String s) {
        try {
            Long.parseLong(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isFloat(String s) {
        try {
            Float.parseFloat(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void setInputFiles(List<String> inputFiles) {
        this.inputFiles = inputFiles;
    }

    public List<String> getIntegerList() {
        return integerList;
    }

    public List<String> getFloatList() {
        return floatList;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public void setIntegerList(List<String> integerList) {
        this.integerList = integerList;
    }

    public void setFloatList(List<String> floatList) {
        this.floatList = floatList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }
}
