package org.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
/**
 * FillingFiles класс отвечает за запись данных в файлы и
 * использует экземпляр класса Statistics для вывода статистики.
 */

public class FillingFiles {

    private String outputPath;
    private String filePrefix;
    private Boolean appendMode;
    private Boolean statistic;
    private Boolean fullStatistic;


    public FillingFiles(
            String outputPath,
            String filePrefix,
            Boolean appendMode,
            Boolean statistic,
            Boolean fullStatistic
    ) {
        this.outputPath = outputPath;
        this.filePrefix = filePrefix;
        this.appendMode = appendMode;
        this.statistic = statistic;
        this.fullStatistic = fullStatistic;
    }

    /**
     * Метод writeToFiles принимает три списка строк и записывает каждый в соответствующий файл, если список не пуст.
     * Вызывается метод printStatistics из класса Statistics, при необходимости вывода статистики в консоль.
     * @param integerList - список строк, которые могут быть преобразованы в целый числовой тип
     * @param floatList - список строк, которые могут быть преобразованы в числовой тип с плавающей точкой
     * @param stringList - список строк, которые не могут быть преобразованы в числовой тип
     */
    public void writeToFiles(List<String> integerList, List<String> floatList, List<String> stringList) {
        if (!integerList.isEmpty()) {
            writeToFile(outputPath, filePrefix + "integers.txt", integerList, appendMode);
        }
        if (!floatList.isEmpty()) {
            writeToFile(outputPath, filePrefix + "floats.txt", floatList, appendMode);
        }
        if (!stringList.isEmpty()) {
            writeToFile(outputPath, filePrefix + "strings.txt", stringList, appendMode);
        }
        if (statistic || fullStatistic) {
            Statistics.printStatistics(integerList, "integers", fullStatistic);
            Statistics.printStatistics(floatList, "floats", fullStatistic);
            Statistics.printStatistics(stringList, "strings", fullStatistic);
        }
    }

    private static void writeToFile(String outputPath, String fileName, List<String> data, boolean appendMode) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(new File(outputPath, fileName), appendMode))) {
            for (String line : data) {
                writer.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public String getOutputPath() {
        return outputPath;
    }

    public String getFilePrefix() {
        return filePrefix;
    }

    public Boolean getAppendMode() {
        return appendMode;
    }

    public Boolean getStatistic() {
        return statistic;
    }

    public Boolean getFullStatistic() {
        return fullStatistic;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public void setFilePrefix(String filePrefix) {
        this.filePrefix = filePrefix;
    }

    public void setAppendMode(Boolean appendMode) {
        this.appendMode = appendMode;
    }

    public void setStatistic(Boolean statistic) {
        this.statistic = statistic;
    }

    public void setFullStatistic(Boolean fullStatistic) {
        this.fullStatistic = fullStatistic;
    }
}
