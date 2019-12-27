package edu.kriale.algorithms.sem5.task10;

import edu.kriale.algorithms.sem5.task10.graph.BipartiteWeightedGraph;

import java.util.Random;

public class ArrangingPolyhedronsAlgorithmDemo {
    private static final int N = 4;
    private static final int K = 6;
    private static final int MAX_VERTEX_VALUE = 1000;
    private static final int MAX_EDGE_VALUE = 1000;

    private static Random random = new Random();


    public static void main(String[] args) {
        BipartiteWeightedGraph<Integer, Integer> graph = new BipartiteWeightedGraph<>();
        // first share
        for (int i = 0; i < N; i++) {
            graph.addVertex(1);
        }

        // second share
        for (int i = 0; i < N; i++) {
            graph.addVertex(1);
        }

        // edges
        graph.addEdge(0, 4, 1);
        graph.addEdge(1, 5, 1);
        graph.addEdge(1, 6, 1);
        graph.addEdge(1, 7, 1);
        graph.addEdge(2, 5, 1);
        graph.addEdge(2, 6, 1);
        graph.addEdge(2, 7, 1);
        graph.addEdge(3, 6, 1);
        graph.addEdge(3, 5, 1);

        System.out.println("Graph: ");
        System.out.println(graph);
        System.out.println();

        System.out.println("Ответ (многогранник - тип наклейки): ");
        KunAlgorithm<Integer, Integer> algorithm = new KunAlgorithm<>(graph);
        algorithm.execute();
    }
}
