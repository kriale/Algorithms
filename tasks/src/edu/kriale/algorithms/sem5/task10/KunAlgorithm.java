package edu.kriale.algorithms.sem5.task10;

import edu.kriale.algorithms.sem5.task10.graph.BipartiteWeightedGraph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class KunAlgorithm<V, E> {
    private static final boolean IS_MARKED = true;
    private static final boolean IS_UNMARKED = false;

    private BipartiteWeightedGraph<V, E> graph;
    private LinkedList<Integer> stack = new LinkedList<>();
    private ArrayList<Boolean> marks;
    private int[] mt;
    private boolean[] used;


    public KunAlgorithm(BipartiteWeightedGraph<V, E> graph) {
        this.graph = graph;
        this.mt = new int[graph.getVertexNumber()];
        this.used = new boolean[graph.getVertexNumber()];
        prepareMarks();
        cleanMt();
        cleanUsed();
    }

    public void execute() {
        cleanMt();
        for (int i = 0; i < graph.getFirstShareVertexNumber(); i++) {
            cleanUsed();
            tryKuhn(i);
        }
        printAnswer();
    }

    private void printAnswer() {
        for (int i = 0; i < graph.getVertexNumber(); i++)
            if (mt[i] != -1)
                System.out.printf ("%d %d\n", mt[i], i);
    }

    private void cleanMt() {
        for (int i = 0; i < mt.length; i++) {
            mt[i] = -1;
        }
    }

    private void cleanUsed() {
        for (int i = 0; i < used.length; i++) {
            used[i] = false;
        }
    }

    private boolean tryKuhn(int vertexIndex) {
        if (used[vertexIndex]) {
            return false;
        }
        used[vertexIndex] = true;
        List<Integer> adjacencyVertexList = graph.getAdjacentVertexListFor(vertexIndex);
        for (Integer adjacencyVertex : adjacencyVertexList) {
            int to = adjacencyVertex;
            if (mt[to] == -1 || tryKuhn(mt[to])) {
                mt[to] = vertexIndex;
                return true;
            }
        }
        return false;
    }

    private void prepareMarks() {
        this.marks = new ArrayList<>(graph.getVertexNumber());
        for (int i = 0; i < graph.getVertexNumber(); i++) {
            marks.add(IS_UNMARKED);
        }
    }
}
