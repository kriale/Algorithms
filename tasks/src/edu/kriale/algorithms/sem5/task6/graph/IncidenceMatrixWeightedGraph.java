package edu.kriale.algorithms.sem5.task6.graph;

import java.io.PrintStream;
import java.util.ArrayList;

public class IncidenceMatrixWeightedGraph<V, E> extends WeightedGraph<V, E> {
    private ArrayList<V> vertexesData = new ArrayList<>();
    private ArrayList<ArrayList<E>> incidenceMatrix = new ArrayList<>();


    public IncidenceMatrixWeightedGraph() {
    }


    @Override
    public int addVertex(V data) {
        vertexesData.add(data);
        incidenceMatrix.forEach(e -> e.add(null));
        addNewIncidenceMatrixRow();
        return vertexesData.size() - 1;
    }

    private void addNewIncidenceMatrixRow() {
        ArrayList<E> newVertexRow = new ArrayList<>();
        for (V ignored : vertexesData) {
            newVertexRow.add(null);
        }
        incidenceMatrix.add(newVertexRow);
    }

    @Override
    public void removeVertex(int index) throws NoSuchVertexException {
        acceptVertexIndex(index);
        vertexesData.remove(index);
        incidenceMatrix.remove(index);
        incidenceMatrix.forEach(e -> e.remove(index));
    }

    private void acceptVertexIndex(int index) throws NoSuchVertexException {
        if (index < 0 && index > getLastVertexIndex()) {
            throw new NoSuchVertexException("No vertex with index '" + index + "'");
        }
    }

    @Override
    public V getVertexData(int index) throws NoSuchVertexException {
        acceptVertexIndex(index);
        return vertexesData.get(index);
    }

    @Override
    protected int getLastVertexIndex() {
        return vertexesData.size() - 1;
    }

    @Override
    public void addEdge(int oneVertexIndex, int twoVertexIndex, E data) {
        acceptVertexIndex(oneVertexIndex);
        acceptVertexIndex(twoVertexIndex);
        incidenceMatrix.get(oneVertexIndex).set(twoVertexIndex, data);
    }

    @Override
    public void removeEdge(int oneVertexIndex, int twoVertexIndex) {
        acceptVertexIndex(oneVertexIndex);
        acceptVertexIndex(twoVertexIndex);
        incidenceMatrix.get(oneVertexIndex).set(twoVertexIndex, null);
    }

    @Override
    public boolean isEdge(int oneVertexIndex, int twoVertexIndex) {
        return incidenceMatrix.get(oneVertexIndex).get(twoVertexIndex) != null;
    }

    @Override
    public E getEdgeData(int oneVertexIndex, int twoVertexIndex) {
        acceptVertexIndex(oneVertexIndex);
        acceptVertexIndex(twoVertexIndex);
        return incidenceMatrix.get(oneVertexIndex).get(twoVertexIndex);
    }

    @Override
    public void print(PrintStream ps) {

    }
}
