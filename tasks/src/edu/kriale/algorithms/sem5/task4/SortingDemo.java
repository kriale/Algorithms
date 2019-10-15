package edu.kriale.algorithms.sem5.task4;

public class SortingDemo {
    private static final int ITERATION_NUMBER = 1_000;
    private static final int MIN_SIZE = 5;
    private static final int MAX_SIZE = 10_000;
    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 100_000;
    private static final int SIZE_SAMPLE_NUMBER = 6;

    public static void main(String[] args) {
        SortingAlgorithm[] algorithms = {new BubbleSortingAlgorithm(), new ShellSortingAlgorithm()};

        for (SortingAlgorithm algorithm : algorithms) {
            System.out.println(" > " + algorithm.getClass().getName() + ":");

            int sizeStep = (MAX_SIZE - MIN_SIZE) / (SIZE_SAMPLE_NUMBER - 1);
            int currentSize = MIN_SIZE;

            for (int i = 0; i < SIZE_SAMPLE_NUMBER; i++) {
                long commonTime = 0;
                for (int j = 0; j < ITERATION_NUMBER; j++) {
                    int[] array = generateRandomArray(currentSize, MIN_VALUE, MAX_VALUE);
                    long start = System.nanoTime(); // start experiment
                    algorithm.sort(array);
                    long end = System.nanoTime(); // end experiment
                    commonTime += (end - start);
                }

                System.out.println(String.format("Среднее время для массива " +
                                "размера %d: %d наносекунд",
                        currentSize, commonTime / ITERATION_NUMBER));

                currentSize += sizeStep;
            }
        }
    }

    private static int generateInt(int minValue, int maxValue) {
        return (int)Math.round(Math.random() * (maxValue - minValue) + minValue);
    }

    private static int[] generateRandomArray(int size, int minValue, int maxValue) {
        int[] array = new int[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = generateInt(minValue, maxValue);
        }
        return array;
    }
}
