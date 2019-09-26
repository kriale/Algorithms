package edu.kriale.algorithms.sem5.task2;

public class InterpolationSearch {
    public static int indexOf(final int[] sortedNumbers, int number) {
        int leftBoarder = 0;
        int rightBoarder = sortedNumbers.length - 1;
        int middle;
        int leftBoarderNumber;
        int rightBoarderNumber;
        while ((leftBoarderNumber = sortedNumbers[leftBoarder]) < number
                && (rightBoarderNumber = sortedNumbers[rightBoarder]) > number) {
            middle = leftBoarder + (number - leftBoarderNumber) * (rightBoarder - leftBoarder)
                    / (rightBoarderNumber - leftBoarderNumber);
            int middleNumber = sortedNumbers[middle];
            if (middleNumber > number) {
                leftBoarder = middle + 1;
            } else if (middleNumber < number) {
                if (middle > 0) {
                    rightBoarder = middle - 1;
                } else {
                    rightBoarder = 0;
                    break;
                }
            } else {
                return middle;
            }
        }
        if (sortedNumbers[leftBoarder] == number) {
            return leftBoarder;
        } else if (sortedNumbers[rightBoarder] == number) {
            return rightBoarder;
        } else {
            return -1;
        }
    }
}
