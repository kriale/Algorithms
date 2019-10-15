package edu.kriale.algorithms.sem5.task4;

public class ShellSortingAlgorithm implements SortingAlgorithm {
    @Override
    public void sort(int[] array) {
        for (int inc = array.length / 2; inc > 0; inc = inc / 2) {
            for (int step = 0; step < inc; step++) {
                insertionSort(array, step, inc);
            }
        }
    }

    private void insertionSort(int[] array, int start, int inc)  {
        int tmp;
        for (int i = start; i < array.length - 1; i += inc) {
            for (int j = Math.min(i + inc, array.length - 1); j >= inc; j -= inc) {
                if (array[j - inc] > array[j]) {
                    tmp = array[j];
                    array[j] = array[j - inc];
                    array[j - inc] = tmp;
                } else {
                    break;
                }
            }
        }
    }
}
