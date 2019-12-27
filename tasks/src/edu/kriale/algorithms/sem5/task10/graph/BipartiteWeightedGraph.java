package edu.kriale.algorithms.sem5.task10.graph;

import edu.kriale.algorithms.sem5.task6.graph.impl.AdjacencyMatrixWeightedGraph;

public class BipartiteWeightedGraph<V, E> extends AdjacencyMatrixWeightedGraph<V, E> {
    public int getFirstShareVertexNumber() {
        return this.getVertexNumber() / 2;
    }

    public int getSecondShareVertexNumber() {
        return this.getVertexNumber() - this.getVertexNumber() / 2;
    }
}
