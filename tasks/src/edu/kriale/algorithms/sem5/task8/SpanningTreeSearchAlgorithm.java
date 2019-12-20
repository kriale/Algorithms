package edu.kriale.algorithms.sem5.task8;

import edu.kriale.algorithms.sem5.task6.graph.WeightedGraph;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class SpanningTreeSearchAlgorithm<V, E> {
    private static final boolean IS_MARKED = true;
    private static final boolean IS_UNMARKED = false;
    private static final int TAB_SPACES_NUMBER = 4;
    private static final int MIN_SPACES_NUMBER = 1;
    private static final String PRINTED_TREES_DELIMITER = "\n-----------\n";

    private WeightedGraph<V, E> graph;
    private LinkedList<Integer> stack = new LinkedList<>();
    private ArrayList<Boolean> marks;


    public SpanningTreeSearchAlgorithm(WeightedGraph<V, E> graph) {
        this.graph = graph;
        prepareMarks();
    }

    private void prepareMarks() {
        this.marks = new ArrayList<>(graph.getVertexNumber());
        for (int i = 0; i < graph.getVertexNumber(); i++) {
            marks.add(IS_UNMARKED);
        }
    }

    public String printAll() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < marks.size(); i++) {
            if (marks.get(i).equals(IS_UNMARKED)) {
                builder.append(printAnyFrom(i)).append(PRINTED_TREES_DELIMITER);
            }
        }
        return builder.toString();
    }


    public String printAnyFrom(int vertexIndex) {
        StringBuilder builder = new StringBuilder();
        stack.push(vertexIndex);
        builder.append(printVertex(vertexIndex, 0));
        marks.set(vertexIndex, IS_MARKED);
        while (!stack.isEmpty()) {
            List<Integer> unmarkedAdjacentVertexList = graph.getAdjacentVertexListFor(stack.getFirst())
                    .stream()
                    .filter(e -> marks.get(e).equals(IS_UNMARKED))
                    .collect(Collectors.toList());
            if (unmarkedAdjacentVertexList.isEmpty()) {
                stack.pop();
            } else {
                marks.set(unmarkedAdjacentVertexList.get(0), IS_MARKED);
                builder.append(printVertex(unmarkedAdjacentVertexList.get(0), stack.size()));
                stack.push(unmarkedAdjacentVertexList.get(0));
            }
        }
        return builder.toString();
    }

    private String printVertex(int vertexNumber, int depth) {
        return new Formatter()
                .format("%" + (depth * TAB_SPACES_NUMBER + MIN_SPACES_NUMBER) + "d\n", vertexNumber)
                .toString();
    }
}
