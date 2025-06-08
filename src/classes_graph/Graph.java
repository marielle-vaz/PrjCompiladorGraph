package classes_graph;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Graph {
    
    public static void addVertex(String vertex, Map<String, Set<String>> adjList) {
        adjList.putIfAbsent(vertex, new HashSet<>());
    }

    public static void addEdge(String v1, String v2, Map<String, Set<String>> adjList) {
        addVertex(v1, adjList);
        addVertex(v2, adjList);
        adjList.get(v1).add(v2);
        adjList.get(v2).add(v1);
    }

    public boolean hasVertex(String vertex, Map<String, Set<String>> adjList) {
        return adjList.containsKey(vertex);
    }

    public boolean hasEdge(String v1, String v2, Map<String, Set<String>> adjList) {
        return hasVertex(v1, adjList) && adjList.get(v1).contains(v2);
    }

    public void saveAdjacencyMatrixToFile(Map<String, Set<String>> adjList) {
        String filename = "matrizGraph.txt";

        List<String> vertices = new ArrayList<>(adjList.keySet());
        Collections.sort(vertices);

        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("  ");
            for (String v : vertices) {
                writer.write(v + " ");
            }
            writer.write("\n");

            for (String from : vertices) {
                writer.write(from + " ");
                for (String to : vertices) {
                    int val = adjList.get(from).contains(to) ? 1 : 0;
                    writer.write(val + " ");
                }
                writer.write("\n");
            }

            System.out.println("Matriz de adjacência salva em: " + filename);
        } catch (IOException e) {
            System.err.println("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }
}
