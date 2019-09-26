package edu.kriale.algorithms.sem5.task2;

import java.util.Arrays;

public class SortedArrayGenerator {
    private int minValue;
    private int maxValue;

    public SortedArrayGenerator(int minValue, int maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public int[] generate(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int)Math.round(Math.random() * (maxValue - minValue) + minValue);
        }
        Arrays.sort(array);
        return array;
    }
}
