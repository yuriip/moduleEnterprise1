package ua.goit.java;

import ua.goit.java.enums.VolumeData;
import ua.goit.java.metrics.CalculateTime;
import ua.goit.java.util.Converter;
import ua.goit.java.util.TextFileProcessor;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        final int numberMeasurements = 100;
        final String filePath = "d:/resultTables.txt";

        CalculateTime calculateTime = new CalculateTime();
        Converter converter = new Converter();

        LinkedHashMap<String, List<String>> resultTable = null;
        List<List<String>> resultTables = new ArrayList<>();

        for (VolumeData volumeData : VolumeData.values()) {
            resultTable = calculateTime.getResultTable(volumeData, numberMeasurements);
            resultTables.add(converter.convertMapToString(resultTable));
        }

        for (List<String> list : resultTables) {
            for (String s : list) {
                System.out.println(s);
            }
            System.out.println();
        }

        TextFileProcessor textFileProcessor = new TextFileProcessor();
        textFileProcessor.saveText(filePath, resultTables);
    }
}
