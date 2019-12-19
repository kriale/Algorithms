package edu.kriale.algorithms.sem5.task5.tree;

import java.util.Optional;
import java.util.function.Consumer;

public class BPlusTree implements AbstractTree {
    private static final int DEFAULT_DEGREE = 5;

    private Node root;
    private int degree = DEFAULT_DEGREE;


    public BPlusTree(int[] elements) {

    }

    @Override
    public void insert(int newKey) throws SuchKeyIsExistException {

    }

    @Override
    public void print() {

    }

    @Override
    public void symmetricForeach(Consumer<BinaryTree.Node> action) {

    }

    @Override
    public Optional<Integer> searchByProximityFromAbove(int key) {
        return Optional.empty();
    }

    public class Node {
        public Integer nodeType;
        public Integer s, l;
        public Node small, middle, large, parent;

        public Node(Integer nodeType, Integer s, Integer l, Node parent) {
            this.nodeType = nodeType;
            this.s = s;
            this.l = l;
            this.small = null;
            this.middle = null;
            this.large = null;
            this.parent = parent;
        }
    }
}
