package ua.goit.java.metrics;

import ua.goit.java.enums.TypeCollection;
import ua.goit.java.enums.TypeOperation;
import ua.goit.java.enums.VolumeData;

import java.util.*;

public class CalculateTime {
    public LinkedHashMap<String, List<String>> getResultTable(VolumeData volumeData, int numberMeasurements) {
        List<String> resultColumn = new ArrayList<>();
        LinkedHashMap<String, List<String>> resultTable = new LinkedHashMap<>();

        for (TypeOperation typeOperation : TypeOperation.values()) {
            resultColumn.add(typeOperation.toString().toLowerCase().replace('_', '.'));
        }

        resultTable.put((volumeData.toString().replace('_', ' ') + ", time in microseconds"), resultColumn);


        for (TypeCollection typeCollection : TypeCollection.values()) {
            resultTable.put(typeCollection.toString().toLowerCase(), calculateTime(volumeData.getVolumeData(), numberMeasurements, typeCollection));
        }

        return resultTable;
    }

    private List calculateTime(int volumeData, int numberMeasurements, TypeCollection typeCollection) {
        List<String> result = new ArrayList<>();
        CollectionOperation collectionOperation;

        switch (typeCollection) {
            case ArrayList:
                collectionOperation = new ListOperation(volumeData, numberMeasurements, new ArrayList<Integer>());
                process(result, collectionOperation);
                break;
            case LinkedList:
                collectionOperation = new ListOperation(volumeData, numberMeasurements, new LinkedList<>());
                process(result, collectionOperation);
                break;
            case HashSet:
                collectionOperation = new SetOperation(volumeData, numberMeasurements, new HashSet<>());
                process(result, collectionOperation);
                result = result.subList(0, result.size() - 2);
                break;
            case TreeSet:
                collectionOperation = new SetOperation(volumeData, numberMeasurements, new TreeSet<>());
                process(result, collectionOperation);
                result = result.subList(0, result.size() - 2);
                break;
        }

        return result;
    }

    private void process(List<String> result, CollectionOperation collectionOperation) {
        for (TypeOperation typeOperation : TypeOperation.values()) {
            result.add(collectionOperation.calculateTimeOperatiion(typeOperation).toString());
        }
    }
}
