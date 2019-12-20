package edu.kriale.algorithms.sem5.task6.graph.impl;

import edu.kriale.algorithms.sem5.task6.graph.WeightedGraph;
import edu.kriale.algorithms.sem5.task6.graph.exception.NoSuchEdgeException;
import edu.kriale.algorithms.sem5.task6.graph.exception.NoSuchVertexException;

import java.util.*;

public class AdjacencyListWeightedGraph<V, E> extends WeightedGraph<V, E> {
    private static final String VERTEX_FORMATTER_PATTERN = "%4.6s";
    private static final String EDGE_FORMATTER_PATTERN = "%4.6s";
    private static final String PRINTED_COLUMN_DELIMITER = "|";
    private static final String PRINTED_ROW_DELIMITER = "\n";
    private static final String PRINTED_EMPTY = " ";
    private static final String PRINTED_EMPTY_EDGE_VALUE = "-";
    private static final String PRINTED_NOT_EMPTY_EDGE_VALUE = "1";
    private static final String GRAPH_IS_EMPTY_MESSAGE = "[empty]";

    private List<V> vertexesData = new ArrayList<>();
    private List<Map<Integer, E>> adjacencyLists = new ArrayList<>();


    public AdjacencyListWeightedGraph() {
    }

    public AdjacencyListWeightedGraph(AdjacencyListWeightedGraph<V, E> other) {
        Collections.copy(this.vertexesData, other.vertexesData);
        Collections.copy(this.adjacencyLists, other.adjacencyLists);
    }


    @Override
    public int addVertex(V data) {
        vertexesData.add(data);
        adjacencyLists.add(new HashMap<>());
        return vertexesData.size() - 1;
    }

    @Override
    public void removeVertex(int index) throws NoSuchVertexException {
        acceptVertexIndex(index);
        vertexesData.remove(index);
        adjacencyLists.remove(index);
        adjacencyLists.forEach(e -> e.remove(index));
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
            Map<Integer, E> list = adjacencyLists.get(oneVertexIndex);
            list.put(twoVertexIndex, data);
        }
    }

    @Override
    public void removeEdge(int oneVertexIndex, int twoVertexIndex) throws NoSuchEdgeException {
        acceptVertexIndex(oneVertexIndex);
        acceptVertexIndex(twoVertexIndex);
        adjacencyLists.get(oneVertexIndex).remove(twoVertexIndex);
    }

    @Override
    public boolean isEdge(int oneVertexIndex, int twoVertexIndex) throws NoSuchVertexException {
        return adjacencyLists.get(oneVertexIndex).containsKey(twoVertexIndex);
    }

    @Override
    public E getEdgeData(int oneVertexIndex, int twoVertexIndex) throws NoSuchEdgeException {
        return adjacencyLists.get(oneVertexIndex).get(twoVertexIndex);
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
        builder.append("Вершины:").append(printRowDelimiter());
        for (int i = 0; i < vertexesData.size(); i++) {
            builder.append(printVertexIndex(i)).append(printColumnDelimiter());
        }
        builder.append(printRowDelimiter());
        vertexesData.forEach(v -> builder.append(printVertex(v)).append(printColumnDelimiter()));
        return builder.toString();
    }

    private String printIncidenceMatrix() {
        StringBuilder builder = new StringBuilder();
        builder.append("Ребра:").append(printRowDelimiter());
        // builder.append(printIncidenceMatrixHeader());
        for (int i = 0; i < adjacencyLists.size(); i++) {
            builder.append(printVertexIndex(i));
            adjacencyLists.get(i).forEach((j, e) ->
                    builder.append(printColumnDelimiter()).append(printEdge(j, e)));
            builder.append(printRowDelimiter());
        }
        return builder.toString();
    }

    private String printIncidenceMatrixHeader() {
        StringBuilder builder = new StringBuilder()
                .append(new Formatter().format(VERTEX_FORMATTER_PATTERN, PRINTED_EMPTY).toString());
        for (int i = 0; i < adjacencyLists.size(); i++) {
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

    private String printEdge(int index, E edge) {
        if (edge != null) {
            return printNotNullEdge(index, edge);
        } else {
            return new Formatter().format(EDGE_FORMATTER_PATTERN, PRINTED_EMPTY_EDGE_VALUE).toString();
        }
    }

    private String printNotNullEdge(Integer index, E edge) {
        return new Formatter().format(EDGE_FORMATTER_PATTERN, index + "(" + edge.toString() + ")").toString();
    }

    private String printColumnDelimiter() {
        return PRINTED_COLUMN_DELIMITER;
    }

    private String printRowDelimiter() {
        return PRINTED_ROW_DELIMITER;
    }

    @Override
    public String printWithoutWeights() {
        return null;
    }

    @Override
    public int getVertexNumber() {
        return vertexesData.size();
    }

    @Override
    public List<Integer> getAdjacentVertexListFor(int vertexIndex) {
        return new ArrayList<>(adjacencyLists.get(vertexIndex).keySet());
    }

    @Override
    public boolean isAdjacent(int vertexOneIndex, int vertexTwoIndex) {
        return vertexOneIndex != vertexTwoIndex
                && (adjacencyLists.get(vertexOneIndex).containsKey(vertexOneIndex)
                || adjacencyLists.get(vertexTwoIndex).containsKey(vertexOneIndex));
    }
}
