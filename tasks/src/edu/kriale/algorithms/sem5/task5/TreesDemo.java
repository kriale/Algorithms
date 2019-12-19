package edu.kriale.algorithms.sem5.task5;

import edu.kriale.algorithms.sem5.task5.tree.BTree;
import edu.kriale.algorithms.sem5.task5.tree.BinaryTree;

import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

public class TreesDemo {
    private static final int KEYS_ARRAY_SIZE = 20;
    private static final int KEY_MIN_VALUE = 0;
    private static final int KEY_MAX_VALUE = 200;

    public static void main(String[] args) {
        int userKey;
        int[] array;

        array = generateRandomArray(KEYS_ARRAY_SIZE, KEY_MIN_VALUE, KEY_MAX_VALUE);
        System.out.println("\nМАССИВ КЛЮЧЕЙ:");
        System.out.println(Arrays.toString(array));

//        Arrays.sort(array);
//        System.out.println("\nОТСОРТИРОВАННЫЙ МАССИВ КЛЮЧЕЙ:");
//        System.out.println(Arrays.toString(array));

        System.out.println("\nБИНАРНОЕ ДЕРЕВО");
        System.out.println("Начальное бинарное дерево: ");
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.print();

        binaryTree.insert(array[array.length / 2]);
        for (int key : array) {
            binaryTree.insert(key);
        }
        System.out.println("Построенное бинарное дерево: ");
        binaryTree.print();

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Введите искомый ключ: ");
            userKey = scanner.nextInt();
            Optional<Integer> optionalProximityKey = binaryTree.searchByProximityFromAbove(userKey);
            if (optionalProximityKey.isPresent()) {
                System.out.println("Ближайший сверху ключ: " + optionalProximityKey.get());
            } else {
                System.out.println("В дереве отсутствует ближайший сверху ключ");
            }
        }


        System.out.println("\nB+ ДЕРЕВО");
        System.out.println("Начальное B+ дерево: ");
        BTree bTree = new BTree();
        bTree.print();


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
