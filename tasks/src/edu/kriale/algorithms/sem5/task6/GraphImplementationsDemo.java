package edu.kriale.algorithms.sem5.task6;

import edu.kriale.algorithms.sem5.task6.graph.impl.AdjacencyListWeightedGraph;
import edu.kriale.algorithms.sem5.task6.graph.impl.AdjacencyMatrixWeightedGraph;
import edu.kriale.algorithms.sem5.task6.graph.WeightedGraph;

import java.util.Random;

public class GraphImplementationsDemo {
    private static final int MAX_VERTEX_VALUE = 1000;
    private static final int MAX_EDGE_VALUE = 1000;
    private static Random random = new Random();

    public static void main(String[] args) {
        WeightedGraph<Integer, Integer> graph1 = new AdjacencyListWeightedGraph<>();
        System.out.println(graph1);
        System.out.println();

        for (int i = 0; i < 6; i++) {
            graph1.addVertex(random.nextInt(MAX_VERTEX_VALUE));
        }
        System.out.println(graph1);
        System.out.println();

        graph1.removeVertex(0);
        System.out.println(graph1);
        System.out.println();

        graph1.addEdge(0, 3, random.nextInt(MAX_EDGE_VALUE));
        graph1.addEdge(4, 2, random.nextInt(MAX_EDGE_VALUE));
        graph1.addEdge(2, 4, random.nextInt(MAX_EDGE_VALUE));
        System.out.println(graph1);
        System.out.println();

        graph1.removeEdge(4, 2);
        System.out.println(graph1);
        System.out.println();
    }
}
