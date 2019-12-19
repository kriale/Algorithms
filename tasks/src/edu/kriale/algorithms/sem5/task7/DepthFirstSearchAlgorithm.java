package edu.kriale.algorithms.sem5.task7;

import edu.kriale.algorithms.sem5.task6.graph.WeightedGraph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class DepthFirstSearchAlgorithm<V, E> {
    private static final boolean IS_MARKED = true;
    private static final boolean IS_UNMARKED = false;

    private WeightedGraph<V, E> graph;
    private LinkedList<Integer> stack = new LinkedList<>();
    private ArrayList<Boolean> marks;


    public DepthFirstSearchAlgorithm(WeightedGraph<V, E> graph) {
        this.graph = graph;
        prepareMarks();
    }

    private void prepareMarks() {
        this.marks = new ArrayList<>(graph.getVertexNumber());
        for (int i = 0; i < graph.getVertexNumber(); i++) {
            marks.add(IS_UNMARKED);
        }
    }


    public void execute(int vertexIndex, Consumer<Integer> action) {
        stack.clear();
        stack.push(vertexIndex);
        action.accept(vertexIndex);
        marks.set(vertexIndex, IS_MARKED);
        while (!stack.isEmpty()) {
            List<Integer> unmarkedAdjacentVertexList = graph.getAdjacentVertexListFor(stack.getLast())
                    .stream()
                    .filter(e -> marks.get(e).equals(IS_UNMARKED))
                    .collect(Collectors.toList());
            if (unmarkedAdjacentVertexList.isEmpty()) {
                stack.pop();
            } else {
                marks.set(unmarkedAdjacentVertexList.get(0), IS_MARKED);
                stack.push(unmarkedAdjacentVertexList.get(0));
                action.accept(unmarkedAdjacentVertexList.get(0));
            }
        }
    }
}
