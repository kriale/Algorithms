package edu.kriale.algorithms.sem5.task6.graph.impl;

import edu.kriale.algorithms.sem5.task6.graph.WeightedGraph;
import edu.kriale.algorithms.sem5.task6.graph.exception.NoSuchVertexException;

import java.util.Collections;
import java.util.Formatter;
import java.util.LinkedList;
import java.util.List;

public class AdjacencyMatrixWeightedGraph<V, E> extends WeightedGraph<V, E> {
    private static final String VERTEX_FORMATTER_PATTERN = "%4.4s";
    private static final String EDGE_FORMATTER_PATTERN = "%4.4s";
    private static final String PRINTED_COLUMN_DELIMITER = "|";
    private static final String PRINTED_ROW_DELIMITER = "\n";
    private static final String PRINTED_EMPTY = " ";
    private static final String PRINTED_EMPTY_EDGE_VALUE = "-";
    private static final String PRINTED_NOT_EMPTY_EDGE_VALUE = "1";
    private static final String GRAPH_IS_EMPTY_MESSAGE = "[empty]";

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
        if (oneVertexIndex != twoVertexIndex) {
            adjacencyMatrix.get(oneVertexIndex).set(twoVertexIndex, data);
        }
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
    public boolean isEmpty() {
        return vertexesData.isEmpty();
    }

    @Override
    public String print() {
        if (isEmpty()) {
            return GRAPH_IS_EMPTY_MESSAGE;
        } else {
            return printNotEmptyGraph();
        }
    }

    private String printNotEmptyGraph() {
        return printVertexes() + printRowDelimiter() + printIncidenceMatrix();
    }

    private String printVertexes() {
        StringBuilder builder = new StringBuilder();
        builder.append("Веса вершин:").append(printRowDelimiter());
        for (int i = 0; i < vertexesData.size(); i++) {
            builder.append(printVertexIndex(i)).append(printColumnDelimiter());
        }
        builder.append(printRowDelimiter());
        vertexesData.forEach(v -> builder.append(printVertex(v)).append(printColumnDelimiter()));
        return builder.toString();
    }

    private String printIncidenceMatrix() {
        StringBuilder builder = new StringBuilder();
        builder.append("Веса ребер:").append(printRowDelimiter());
        builder.append(printIncidenceMatrixHeader());
        for (int i = 0; i < adjacencyMatrix.size(); i++) {
            builder.append(printRowDelimiter()).append(printVertexIndex(i));
            adjacencyMatrix.get(i).forEach(e -> builder.append(printColumnDelimiter()).append(printEdge(e)));
        }
        return builder.toString();
    }

    private String printIncidenceMatrixHeader() {
        StringBuilder builder = new StringBuilder()
                .append(new Formatter().format(VERTEX_FORMATTER_PATTERN, PRINTED_EMPTY).toString());
        for (int i = 0; i < adjacencyMatrix.size(); i++) {
            builder.append(printColumnDelimiter()).append(printVertexIndex(i));
        }
        return builder.toString();
    }

    private String printVertex(V vertex) {
        return new Formatter().format(VERTEX_FORMATTER_PATTERN, vertex.toString()).toString();
    }

    private String printVertexIndex(int vertexIndex) {
        return new Formatter().format(VERTEX_FORMATTER_PATTERN, String.valueOf(vertexIndex)).toString();
    }

    private String printEdge(E edge) {
        if (edge != null) {
            return printNotNullEdge(edge);
        } else {
            return new Formatter().format(EDGE_FORMATTER_PATTERN, PRINTED_EMPTY_EDGE_VALUE).toString();
        }
    }

    private String printNotNullEdge(E edge) {
        return new Formatter().format(EDGE_FORMATTER_PATTERN, edge.toString()).toString();
    }

    private String printColumnDelimiter() {
         return PRINTED_COLUMN_DELIMITER;
    }

    private String printRowDelimiter() {
        return PRINTED_ROW_DELIMITER;
    }

    @Override
    public String printWithoutWeights() {
        if (isEmpty()) {
            return GRAPH_IS_EMPTY_MESSAGE;
        } else {
            return printNotEmptyGraphWithoutWeights();
        }
    }

    private String printNotEmptyGraphWithoutWeights() {
        return printIncidenceMatrixWithoutWeights();
    }

    private String printIncidenceMatrixWithoutWeights() {
        StringBuilder builder = new StringBuilder();
        builder.append(printIncidenceMatrixHeader());
        for (int i = 0; i < adjacencyMatrix.size(); i++) {
            builder.append(printRowDelimiter()).append(printVertexIndex(i));
            adjacencyMatrix.get(i).forEach(e -> builder.append(printColumnDelimiter())
                    .append(printEdgeWithoutWeights(e)));
        }
        return builder.toString();
    }

    private String printEdgeWithoutWeights(E edge) {
        if (edge != null) {
            return new Formatter().format(EDGE_FORMATTER_PATTERN, PRINTED_NOT_EMPTY_EDGE_VALUE).toString();
        } else {
            return new Formatter().format(EDGE_FORMATTER_PATTERN, PRINTED_EMPTY_EDGE_VALUE).toString();
        }
    }

    @Override
    public int getVertexNumber() {
        return vertexesData.size();
    }

    @Override
    public List<Integer> getAdjacentVertexListFor(int vertexNumber) {
        List<Integer> adjacentVertexList = new LinkedList<>();
        for (int i = 0; i < vertexesData.size(); i++) {
            if (isAdjacent(vertexNumber, i)) {
                adjacentVertexList.add(i);
            }
        }
        return adjacentVertexList;
    }

    @Override
    public boolean isAdjacent(int vertexOneIndex, int vertexTwoIndex) {
        return vertexOneIndex != vertexTwoIndex
                && (adjacencyMatrix.get(vertexOneIndex).get(vertexTwoIndex) != null
                || adjacencyMatrix.get(vertexTwoIndex).get(vertexOneIndex) != null);
    }
}
