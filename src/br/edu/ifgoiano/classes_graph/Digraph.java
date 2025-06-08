package br.edu.ifgoiano.classes_graph;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Digraph {

    public void addVertex(String vertex, Map<String, Set<String>> adjList) {
        adjList.putIfAbsent(vertex, new HashSet<>());
    }

    public void addEdge(String from, String to, Map<String, Set<String>> adjList) {
        addVertex(from, adjList);
        addVertex(to, adjList);
        adjList.get(from).add(to); // Only one direction
    }

    public boolean hasVertex(String vertex, Map<String, Set<String>> adjList) {
        return adjList.containsKey(vertex);
    }

    public boolean hasEdge(String from, String to, Map<String, Set<String>> adjList) {
        return hasVertex(from, adjList) && adjList.get(from).contains(to);
    }

    public void saveAdjacencyMatrixToFile(Map<String, Set<String>> adjList) {
        String filename = "matrizDigraph.txt";

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

