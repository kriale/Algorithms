package edu.kriale.algorithms.sem5.task1;

public class SquareMatrixOperations {
    public static long getProductOfDiagonalElements(int[][] matrix) {
        long product = 1;
        for (int i = 0; i < matrix.length; i++) {
            int element = matrix[i][i];
            if (element == 0) {
                return 0;
            }
            product *= element;
        }
        return product;
    }
}
