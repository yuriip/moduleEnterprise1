package ua.goit.java.metrics;

import ua.goit.java.enums.TypeOperation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.ListIterator;


public class ListOperation implements CollectionOperation {
    private final double TIME_IN_MICROSECONDS = 1000;
    private int volumeData;
    private int numberMeasurements;
    private List<Integer> list;

    public ListOperation(int volumeData, int numberMeasurements, List<Integer> list) {
        this.volumeData = volumeData;
        this.numberMeasurements = numberMeasurements;
        this.list = list;
    }

    private void populateList() {
        list.clear();
        for (int j = 0; j < volumeData; j++) {
            list.add(j);
        }
    }

    public Double calculateTimeOperatiion(TypeOperation typeOperation) {
        validateList();

        populateList();

        long startMeasurement = System.nanoTime();

        processOperation(typeOperation);

        long endMeasurement = System.nanoTime();

        Long diff = endMeasurement - startMeasurement;

        return new BigDecimal(diff.doubleValue() / numberMeasurements / TIME_IN_MICROSECONDS).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    private void validateList() {
        if (list == null) {
            throw new IllegalArgumentException();
        }
    }

    private void processOperation(TypeOperation typeOperation) {
        for (int i = 0; i < numberMeasurements; i++) {
            ListIterator<Integer> listIterator = list.listIterator();
            switch (typeOperation) {
                case ADD:
                    list.add(i, i);
                    break;
                case GET:
                    list.get(i);
                    break;
                case REMOVE:
                    list.remove(i);
                    break;
                case CONTAINS:
                    list.contains(i);
                    break;
                case POPULATE:
                    populateList();
                    break;
                case ITERATOR_ADD:
                    if (listIterator.hasNext()) {
                        listIterator.add(i);
                        listIterator.next();
                    }
                    break;
                case ITERATOR_REMOVE:
                    if (listIterator.hasNext()) {
                        listIterator.next();
                        listIterator.remove();
                    }
                    break;
            }
        }
    }
}
