package edu.kriale.algorithms.sem5.task3;

public class MathOperationDemo {
    private static final int ITERATION_NUMBER = 10_000_000;
    private static final int MIN_POWER = 0;
    private static final int MAX_POWER = 30;
    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 5;
    private static final String OUTPUT_MESSAGE = "Среднее время работы алгоритма вычисления для степени %d: %d наносекунд";

    public static void main(String[] args) {
        for (int currentPower = MIN_POWER; currentPower < MAX_POWER; currentPower++) {
            long commonTime = 0;
            long start = System.nanoTime(); // start experiment
            for (int j = 0; j <= ITERATION_NUMBER; j++) {
                double number = generateDouble(MIN_VALUE, MAX_VALUE);
                double result = MathOperations.power(number, currentPower);
            }
            long end = System.nanoTime(); // end experiment
            commonTime += (end - start);
            System.out.println(String.format(OUTPUT_MESSAGE,
                    currentPower,
                    commonTime / ITERATION_NUMBER));
        }
    }

    private static double generateDouble(double minValue, double maxValue) {
        return Math.round(Math.random() * (maxValue - minValue) + minValue);
    }
}
