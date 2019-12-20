package edu.kriale.algorithms.sem5.task6.graph;

import edu.kriale.algorithms.sem5.task6.graph.exception.NoSuchEdgeException;
import edu.kriale.algorithms.sem5.task6.graph.exception.NoSuchVertexException;

import java.util.List;

public abstract class WeightedGraph<V, E> {
    public WeightedGraph() {
    }


    public abstract int addVertex(V data);

    public abstract void removeVertex(int index) throws NoSuchVertexException;

    public abstract V getVertexData(int index) throws NoSuchVertexException;

    protected abstract int getLastVertexIndex();

    public abstract void addEdge(int oneVertexIndex, int twoVertexIndex, E data) throws NoSuchVertexException;

    public abstract void removeEdge(int oneVertexIndex, int twoVertexIndex) throws NoSuchEdgeException;

    public abstract boolean isEdge(int oneVertexIndex, int twoVertexIndex) throws NoSuchVertexException;

    public abstract E getEdgeData(int oneVertexIndex, int twoVertexIndex) throws NoSuchEdgeException;

    public abstract boolean isEmpty();

    public abstract String print();

    public abstract String printWithoutWeights();

    public abstract int getVertexNumber();

    public abstract List<Integer> getAdjacentVertexListFor(int vertexNumber);

    public abstract boolean isAdjacent(int vertexOneIndex, int vertexTwoIndex);

    @Override
    public String toString() {
        return print();
    }
}
