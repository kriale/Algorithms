package edu.kriale.algorithms.sem5.task3;

public class MathOperations {
    public static double power(double number, int degree) {
        if (degree > 1) {
            return power(number, degree - 1) * number;
        } else if (degree == 1) {
            return number;
        } else if (degree == 0) {
            return 1;
        } else {
            throw new ArithmeticException("Negative degree is not available");
        }
    }
}
