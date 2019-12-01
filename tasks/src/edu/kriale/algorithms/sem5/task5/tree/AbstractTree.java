package edu.kriale.algorithms.sem5.task5.tree;

import java.util.Optional;
import java.util.function.Consumer;

public interface AbstractTree {
    void insert(int newKey) throws SuchKeyIsExistException;

    void print();

    void symmetricForeach(Consumer<BinaryTree.Node> action);

    Optional<Integer> searchByProximityFromAbove(int key);
}
