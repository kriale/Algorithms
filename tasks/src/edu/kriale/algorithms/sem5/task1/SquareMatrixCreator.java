package edu.kriale.algorithms.sem5.task1;

public class SquareMatrixCreator {
    private int minValue;
    private int maxValue;

    public SquareMatrixCreator(int minValue, int maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public int[][] createRandom(int size) {
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = (int)Math.round(Math.random() * (maxValue - minValue) + minValue);
            }
        }
        return matrix;
    }

    public int[][] createRandomDiagonal(int size) {
        int[][] matrix = new int[size][size];
        for (int j = 0; j < size; j++) {
            matrix[j][j] = (int)Math.round(Math.random() * (maxValue - minValue) + minValue);
        }
        return matrix;
    }
}
