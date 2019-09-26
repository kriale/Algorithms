package edu.kriale.algorithms.sem5.task2;

import java.util.Arrays;

public class SearchAlgorithmExperiment {
    private static final int ITERATION_NUMBER = 5_000_000;
    private static final int MIN_SIZE = 5;
    private static final int MAX_SIZE = 100;
    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 100;
    private static final int SIZE_SAMPLE_NUMBER = 6;

    public static void main(String[] args) {
        int sizeStep = (MAX_SIZE - MIN_SIZE) / (SIZE_SAMPLE_NUMBER - 1);
        int currentSize = MIN_SIZE;
        SortedArrayGenerator generator = new SortedArrayGenerator(MIN_VALUE, MAX_VALUE);
        for (int i = 0; i < SIZE_SAMPLE_NUMBER; i++) {

//            Arrays.stream(sortedArray).forEach(e -> System.out.print(e + ", "));
//            System.out.println();
//            for (int j = 0; j < 10; j++) {
//                int number = (int)Math.round(Math.random() * (MAX_VALUE - MIN_VALUE) + MIN_VALUE);
//                System.out.println(j+1 + ") Number: " + number);
//                int result = InterpolationSearch.indexOf(sortedArray, number);
//                System.out.println("Result: " + result);
//            }

            long commonTime = 0;
            for (int j = 0; j < ITERATION_NUMBER; j++) {
                int[] sortedArray = generator.generate(currentSize);
                long start = System.nanoTime(); // start experiment
                int result = InterpolationSearch.indexOf(sortedArray,
                        (int)Math.round(Math.random() * (MAX_VALUE - MIN_VALUE) + MIN_VALUE));
                long end = System.nanoTime(); // end experiment
                commonTime += (end - start);
            }

            System.out.println(String.format("Среднее время работы алгоритма поиска для массива " +
                            "размера %d: %d наносекунд",
                    currentSize, commonTime / ITERATION_NUMBER));

            currentSize += sizeStep;
        }
    }
}
