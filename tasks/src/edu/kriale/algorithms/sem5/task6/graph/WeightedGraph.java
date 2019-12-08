package edu.kriale.algorithms.sem5.task6.graph;

import java.io.PrintStream;

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

    public abstract void print(PrintStream ps);


    public class Vertex {
        V data;
    }
}
