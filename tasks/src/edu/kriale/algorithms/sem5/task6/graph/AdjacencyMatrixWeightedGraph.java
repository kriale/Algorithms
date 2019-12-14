package edu.kriale.algorithms.sem5.task6.graph;

import java.io.PrintStream;
import java.util.Collections;
import java.util.LinkedList;

public class AdjacencyMatrixWeightedGraph<V, E> extends WeightedGraph<V, E> {
    private static final String VERTEX_FORMATTER_PATTERN = "%5.7s";
    private static final String EDGE_FORMATTER_PATTERN = "%5.7s";
    private static final String PRINTED_COLUMN_DELIMITER = "|";
    private static final String PRINTED_ROW_DELIMITER = "\n";
    private static final String PRINTED_EMPTY = " ";

    private LinkedList<V> vertexesData = new LinkedList<>();
    private LinkedList<LinkedList<E>> adjacencyMatrix = new LinkedList<>();


    public AdjacencyMatrixWeightedGraph() {
    }

    public AdjacencyMatrixWeightedGraph(AdjacencyMatrixWeightedGraph<V, E> other) {
        Collections.copy(this.vertexesData, other.vertexesData);
        Collections.copy(this.adjacencyMatrix, other.adjacencyMatrix);
    }


    @Override
    public int addVertex(V data) {
        vertexesData.add(data);
        adjacencyMatrix.forEach(e -> e.add(null));
        addNewIncidenceMatrixRow();
        return vertexesData.size() - 1;
    }

    private void addNewIncidenceMatrixRow() {
        LinkedList<E> newVertexRow = new LinkedList<>();
        for (V ignored : vertexesData) {
            newVertexRow.add(null);
        }
        adjacencyMatrix.add(newVertexRow);
    }

    @Override
    public void removeVertex(int index) throws NoSuchVertexException {
        acceptVertexIndex(index);
        vertexesData.remove(index);
        adjacencyMatrix.remove(index);
        adjacencyMatrix.forEach(e -> e.remove(index));
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
        adjacencyMatrix.get(oneVertexIndex).set(twoVertexIndex, data);
    }

    @Override
    public void removeEdge(int oneVertexIndex, int twoVertexIndex) {
        acceptVertexIndex(oneVertexIndex);
        acceptVertexIndex(twoVertexIndex);
        adjacencyMatrix.get(oneVertexIndex).set(twoVertexIndex, null);
    }

    @Override
    public boolean isEdge(int oneVertexIndex, int twoVertexIndex) {
        return adjacencyMatrix.get(oneVertexIndex).get(twoVertexIndex) != null;
    }

    @Override
    public E getEdgeData(int oneVertexIndex, int twoVertexIndex) {
        acceptVertexIndex(oneVertexIndex);
        acceptVertexIndex(twoVertexIndex);
        return adjacencyMatrix.get(oneVertexIndex).get(twoVertexIndex);
    }

    @Override
    public void print(PrintStream ps) {
        printVertexes();
        printRowDelimiter();
        printIncidenceMatrix();
    }

    private void printVertexes() {
        for (int i = 0; i < vertexesData.size(); i++) {
            printVertexIndex(i);
        }
        printRowDelimiter();
        vertexesData.forEach(v -> {
            printVertex(v);
            printColumnDelimiter();
        });
    }

    private void printIncidenceMatrix() {
        printIncidenceMatrixHeader();
        for (int i = 0; i < adjacencyMatrix.size(); i++) {
            printRowDelimiter();
            printVertexIndex(i);
            adjacencyMatrix.get(i).forEach(e -> {
                printColumnDelimiter();
                printEdge(e);
            });
        }
    }

    private void printIncidenceMatrixHeader() {
        System.out.printf(VERTEX_FORMATTER_PATTERN, PRINTED_EMPTY);
        for (int i = 0; i < adjacencyMatrix.size(); i++) {
            printColumnDelimiter();
            printVertexIndex(i);
        }
    }

    private void printVertex(V vertex) {
        System.out.printf(VERTEX_FORMATTER_PATTERN, vertex.toString());
    }

    private void printVertexIndex(int vertexIndex) {
        System.out.printf(VERTEX_FORMATTER_PATTERN, String.valueOf(vertexIndex));
    }

    private void printEdge(E edge) {
        System.out.printf(EDGE_FORMATTER_PATTERN, edge.toString());
    }

    private void printColumnDelimiter() {
        System.out.println(PRINTED_COLUMN_DELIMITER);
    }

    private void printRowDelimiter() {
        System.out.println(PRINTED_ROW_DELIMITER);
    }
}
