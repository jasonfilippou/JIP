package others;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

public class AdjacencyListGraphTwo {

  public Map<Integer, Set<Integer>> createAdjacencyList(int[][] edgeList){
    final Map<Integer, Set<Integer>> adjacencyList = new HashMap<>();
    for (int[] edge : edgeList) {
      if (adjacencyList.containsKey(edge[0])) {
        adjacencyList.get(edge[0]).add(edge[1]);
      } else {
        adjacencyList.put(edge[0], new HashSet<>(Collections.singleton(edge[1])));
      }
    }
    return adjacencyList;
  }

  public Set<Integer> neighbors(int node, Map<Integer, Set<Integer>> adjacencyList) {
    return adjacencyList.getOrDefault(node, Collections.emptySet());
  }

  public void breadthFirstSearch(int node, Map<Integer, Set<Integer>> adjacencyList) {
    if(!adjacencyList.containsKey(node)){
      return;
    }
    // We will need to maintain a set of visited nodes
    // in case there are cycles in the graph
    Set<Integer> visited = new HashSet<>();
    Queue<Integer> queue = new LinkedList<>();
    queue.add(node);
    while(!queue.isEmpty()){
      int current = queue.poll();
      if(!visited.contains(current)) {
        System.out.println("Visiting node: " + current);
        visited.add(current);
      }
      Set<Integer> neighbors = adjacencyList.getOrDefault(current, Collections.emptySet());
      queue.addAll(neighbors.stream().filter(neighbor -> !visited.contains(neighbor)).collect(Collectors.toSet()));
    }
  }

  public void depthFirstSearch(int node, Map<Integer, Set<Integer>> adjacencyList, Set<Integer> visited) {
    System.out.println("Visiting node: " + node);
    visited.add(node);
    Set<Integer> neighbors = adjacencyList.getOrDefault(node, Collections.emptySet());
    for(Integer neighbor : neighbors){
      if(!visited.contains(neighbor)) {
        depthFirstSearch(neighbor, adjacencyList, visited);
      }
    }
  }

  public static void main(String[] args) {
    AdjacencyListGraphTwo graph = new AdjacencyListGraphTwo();
    int[][] edgeList = {
      {0, 1}, {1, 3}, {0, 2}, {2, 3},
      {3, 0}, {3, 1}, {3, 3}, {1, 4}
    };
    Map<Integer, Set<Integer>> adjacencyList = graph.createAdjacencyList(edgeList);
    System.out.println("BFS:");
    graph.breadthFirstSearch(0, adjacencyList);
    System.out.println("DFS:");
    graph.depthFirstSearch(0, adjacencyList, new HashSet<>());
  }
}
