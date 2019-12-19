package edu.kriale.algorithms.sem5.task7;

import edu.kriale.algorithms.sem5.task6.graph.WeightedGraph;
import edu.kriale.algorithms.sem5.task6.graph.WeightedGraphGenerator;

public class DepthFirstSearchAlgorithmDemo {
    private static final int MAX_VERTEX_NUMBER = 6;
    private static final int MAX_EDGE_NUMBER = 30;

    public static void main(String[] args) {
        WeightedGraph<Integer, Integer> graph = WeightedGraphGenerator
                .generateWithIntegerValues(MAX_VERTEX_NUMBER, MAX_EDGE_NUMBER);
        System.out.println("Граф: ");
        System.out.println(graph.print());
        System.out.println();

        int startVertex = 0;
        System.out.println("Поиск в глубину, начиная с вершины " + startVertex + ": ");
        (new DepthFirstSearchAlgorithm<>(graph)).execute(startVertex, System.out::println);
    }
}
