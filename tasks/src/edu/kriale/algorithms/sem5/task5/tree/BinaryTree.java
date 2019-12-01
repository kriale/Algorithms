package edu.kriale.algorithms.sem5.task5.tree;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.util.Optional;
import java.util.function.Consumer;

public class BinaryTree implements AbstractTree {
    private static final int TAB_SPACES_NUMBER = 4;
    private static final int MIN_SPACES_NUMBER = 1;
    private static final String EMPTY_TREE_STRING = "[пустое]";

    private Node root;


    @Override
    public void insert(int newKey) {
        if (root != null) {
            insertTo(root, newKey);
        } else {
            root = new Node(newKey);
        }
    }

    private void insertTo(Node node, int newKey) {
        if (node.key == newKey) {
//            ignore
//            throw new SuchKeyIsExistException();
        } else if (newKey < node.key) {
            node.left = insertToNullableNode(node.left, newKey);
        } else if (newKey > node.key) {
            node.right = insertToNullableNode(node.right, newKey);
        }
    }

    private Node insertToNullableNode(@Nullable Node node, int newKey) {
        if (node != null) {
            insertTo(node, newKey);
        } else {
            node = new Node(newKey);
        }
        return node;
    }

    @Override
    public void print() {
        if (root != null) {
            print(root, 0);
        } else {
            System.out.println(EMPTY_TREE_STRING);
        }
    }

    private void print(Node node, int level) {
        printKey(node.key, level);
        printNodeIfNotNull(node.left, level + 1);
        printNodeIfNotNull(node.right, level + 1);
    }

    private void printNodeIfNotNull(Node node, int level) {
        if (node != null) {
            print(node, level);
        }
    }

    private void printKey(int key, int level) {
        System.out.printf("%" + (level * TAB_SPACES_NUMBER + MIN_SPACES_NUMBER) + "d\n", key);
    }


    @Override
    public void symmetricForeach(Consumer<Node> action) {
        if (root != null) {
            symmetricForeach(root, action);
        }
    }

    private void symmetricForeach(Node node, Consumer<Node> action) {
        acceptForNotNullNode(node.left, action);
        action.accept(node);
        acceptForNotNullNode(node.right, action);
    }

    private void acceptForNotNullNode(Node node, Consumer<Node> action) {
        if (node != null) {
            symmetricForeach(node, action);
        }
    }


    @Override
    public Optional<Integer> searchByProximityFromAbove(int key) {
        if (root != null) {
            return searchByProximityFromAboveFromNode(root, key);
        } else {
            return Optional.empty();
        }
    }

    private Optional<Integer> searchByProximityFromAboveFromNode(@NotNull Node node, int key) {
        if (node.key == key) {
            return Optional.of(node.key);
        } else if (key > node.key) {
            if (node.right != null) {
                return searchByProximityFromAboveFromNode(node.right, key);
            } else {
                return Optional.empty();
            }
        } else {
            if (node.left != null) {
                Optional<Integer> result = searchByProximityFromAboveFromNode(node.left, key);
                if (result.isPresent()) {
                    return result;
                } else {
                    return Optional.of(node.key);
                }
            } else {
                return Optional.of(node.key);
            }
        }
    }



    public class Node {
        int key;
        Node left;
        Node right;

        private Node(int key) {
            this.key = key;
        }

        public int getKey() {
            return key;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }
}
