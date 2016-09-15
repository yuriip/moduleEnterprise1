package ua.goit.java.metrics;

import ua.goit.java.enums.TypeOperation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;

public class SetOperation implements CollectionOperation {
    private final double TIME_IN_MICROSECONDS = 1000;
    private int volumeData;
    private int numberMeasurements;
    private Set<Integer> set;

    public SetOperation(int volumeData, int numberMeasurements, Set<Integer> set) {
        this.volumeData = volumeData;
        this.numberMeasurements = numberMeasurements;
        this.set = set;
    }

    private void populateSet() {
        set.clear();
        for (int j = 0; j < volumeData; j++) {
            set.add(j);
        }
    }

    public Double calculateTimeOperatiion(TypeOperation typeOperation) {
        validateList();

        populateSet();

        long startMeasurement = System.nanoTime();

        processOperation(typeOperation);

        long endMeasurement = System.nanoTime();

        Long diff = endMeasurement - startMeasurement;

        return new BigDecimal(diff.doubleValue() / numberMeasurements / TIME_IN_MICROSECONDS).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    private void validateList() {
        if (set == null) {
            throw new IllegalArgumentException();
        }
    }

    private void processOperation(TypeOperation typeOperation) {
        for (int i = 0; i < numberMeasurements; i++) {
            switch (typeOperation) {
                case ADD:
                    set.add(i);
                    break;
                case REMOVE:
                    set.remove(i);
                    break;
                case CONTAINS:
                    set.contains(i);
                    break;
                case POPULATE:
                    populateSet();
                    break;
            }
        }
    }
}
