package classes_graph;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Graph {

    private final boolean isDirected;
    private final Map<String, Set<String>> adjList;

    public Graph(boolean isDirected) {
        this.isDirected = isDirected;
        this.adjList = new HashMap<>();
    }

    public void addVertex(String vertex) {
        adjList.putIfAbsent(vertex, new HashSet<>());
    }

    public void addEdge(String v1, String v2) {
        addVertex(v1);
        addVertex(v2);
        adjList.get(v1).add(v2);

        if (!isDirected) {
            adjList.get(v2).add(v1);
        }
    }
    
    public boolean hasVertex(String vertex) {
        return adjList.containsKey(vertex);
    }

    public boolean hasEdge(String from, String to) {
        if (!hasVertex(from) || !hasVertex(to)) return false;

        if (isDirected) {
            return adjList.get(from).contains(to);
        } else {
            return adjList.get(from).contains(to) || adjList.get(to).contains(from);
        }
    }

    public void saveAdjacencyMatrixToFile() {
        String filename = isDirected ? "matrizDigraph.txt" : "matrizGraph.txt";

        List<String> vertices = new ArrayList<>(adjList.keySet());
        Collections.sort(vertices);

        int maxLength = vertices.stream().mapToInt(String::length).max().orElse(0);
        int width = maxLength + 2;

        try (FileWriter writer = new FileWriter("src/matriz/" + filename)) {
            writer.write(String.format("%-" + width + "s", " "));
            for (String v : vertices) {
                writer.write(String.format("%-" + width + "s", v));
            }
            writer.write("\n");

            for (String from : vertices) {
                writer.write(String.format("%-" + width + "s", from));
                for (String to : vertices) {
                    int val = adjList.get(from).contains(to) ? 1 : 0;
                    writer.write(String.format("%-" + width + "d", val));
                }
                writer.write("\n");
            }

            System.out.println("Matriz de adjacência salva em: src/matriz/" + filename);
        } catch (IOException e) {
            System.err.println("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }

    public Map<String, Set<String>> getAdjList() {
        return adjList;
    }

    public boolean isDirected() {
        return isDirected;
    }
}
