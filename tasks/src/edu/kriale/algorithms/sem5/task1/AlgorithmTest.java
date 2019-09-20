package edu.kriale.algorithms.sem5.task1;

public class AlgorithmTest {
    private static final int ITERATION_NUMBER = 5_000_000;
    private static final int MIN_SIZE = 5;
    private static final int MAX_SIZE = 10_000;
    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 100;
    private static final int SIZE_SAMPLE_NUMBER = 6;

    public static void main(String[] args) {
        int sizeStep = (MAX_SIZE - MIN_SIZE) / (SIZE_SAMPLE_NUMBER - 1);
        int currentSize = MIN_SIZE;
        SquareMatrixCreator creator = new SquareMatrixCreator(MIN_VALUE, MAX_VALUE);
        for (int i = 0; i < SIZE_SAMPLE_NUMBER; i++) {
            // generation random matrices
            int[][] matrix = creator.createRandomDiagonal(currentSize);

            long start = System.nanoTime(); // start experiment
            for (int j = 0; j < ITERATION_NUMBER; j++) {
                long result = SquareMatrixOperations.getProductOfDiagonalElements(matrix);
            }
            long end = System.nanoTime(); // end experiment

            System.out.println(String.format("Среднее время работы алгоритма для матрицы " +
                            "размера %dx%d: %d наносекунд",
                    currentSize, currentSize, (end - start) / ITERATION_NUMBER));
            currentSize += sizeStep;
        }
    }
}
