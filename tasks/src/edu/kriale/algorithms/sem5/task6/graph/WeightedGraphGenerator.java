package edu.kriale.algorithms.sem5.task6.graph;

import edu.kriale.algorithms.sem5.task6.graph.impl.AdjacencyMatrixWeightedGraph;

import java.util.Random;

public class WeightedGraphGenerator {
    private static final int MAX_VERTEX_VALUE = 99;
    private static final int MAX_EDGE_VALUE = 99;
    private static Random random = new Random();

    public static WeightedGraph<Integer, Integer> generateWithIntegerValues(int vertexNumber, int edgeNumber) {
        WeightedGraph<Integer, Integer> graph = new AdjacencyMatrixWeightedGraph<>(); // stub
        for (int i = 0; i < vertexNumber; i++) {
            graph.addVertex(random.nextInt(MAX_VERTEX_VALUE));
        }
        for (int i = 0; i < edgeNumber; i++) {
            graph.addEdge(random.nextInt(vertexNumber),
                    random.nextInt(vertexNumber), random.nextInt(MAX_EDGE_VALUE));
        }
        return graph;
    }
}
