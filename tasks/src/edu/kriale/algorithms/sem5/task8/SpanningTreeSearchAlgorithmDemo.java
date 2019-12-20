package edu.kriale.algorithms.sem5.task8;

import edu.kriale.algorithms.sem5.task6.graph.WeightedGraph;
import edu.kriale.algorithms.sem5.task6.graph.WeightedGraphGenerator;

public class SpanningTreeSearchAlgorithmDemo {
    private static final int MAX_VERTEX_NUMBER = 6;
    private static final int MAX_EDGE_NUMBER = 10;

    public static void main(String[] args) {
        WeightedGraph<Integer, Integer> graph = WeightedGraphGenerator
                .generateWithIntegerValues(MAX_VERTEX_NUMBER, MAX_EDGE_NUMBER);
        System.out.println("Граф: ");
        System.out.println(graph.printWithoutWeights());
        System.out.println();

        System.out.println("Остовные деревья: ");
        System.out.println((new SpanningTreeSearchAlgorithm<>(graph)).printAll());
    }
}
