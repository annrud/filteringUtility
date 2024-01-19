package org.util;
import org.apache.commons.cli.*;

import java.util.List;

/**
 * Utility - основной класс, включает метод main для запуска утилиты командной строки.
 * Здесь определяются опции командной строки, и происходит обработка аргументов,
 * создаются экземпляры других классов для обработки файлов и записи результатов.
 */
public class Utility {
    public static void main(String[] args) {
        final String DEFAULT_OUTPUT_PATH = ".";
        final String DEFAULT_FILE_PREFIX = "";
        Options options = new Options();
        options.addOption("o", "output", true, "Output path for result files");
        options.addOption("p", "prefix", true, "File name prefix");
        options.addOption("a", "append", false, "Append mode");
        options.addOption("s", "statistics", false, "Brief statistics");
        options.addOption("f", "full-statistics", false, "Full statistics");

        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine cmd = parser.parse(options, args);
            String outputPath = cmd.getOptionValue("o", DEFAULT_OUTPUT_PATH);
            String filePrefix = cmd.getOptionValue("p", DEFAULT_FILE_PREFIX);
            boolean appendMode = cmd.hasOption("a");
            boolean statistics = cmd.hasOption("s");
            boolean fullStatistics = cmd.hasOption("f");

            List<String> inputFiles = cmd.getArgList();
            FilteringFiles filteringFiles = new FilteringFiles(inputFiles);
            filteringFiles.dataFilter();
            FillingFiles fillingFiles= new FillingFiles(
                    outputPath,
                    filePrefix,
                    appendMode,
                    statistics,
                    fullStatistics
            );
            fillingFiles.writeToFiles(
                    filteringFiles.getIntegerList(),
                    filteringFiles.getFloatList(),
                    filteringFiles.getStringList()
            );
        } catch (ParseException e) {
            System.err.println("Error parsing command line arguments: " + e.getMessage());
        }
    }
}