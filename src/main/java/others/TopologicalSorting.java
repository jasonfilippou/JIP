package others;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

public class TopologicalSorting {

  private static int[] toplogicalSorting(List<List<Integer>> adjacencyList, int numNodes){
    int[] inDegrees = calculateInDegrees(adjacencyList, numNodes);
    int[] ordering = new int[numNodes];
    int idx = 0;
    Queue<Integer> queue = new LinkedList<>();
    for(int i = 0; i < numNodes; i++){
      if(inDegrees[i] == 0){
        queue.offer(i);
      }
    }
    while(!queue.isEmpty()){
      int node = queue.poll();
      ordering[idx++] = node;
      for(int neighbor : adjacencyList.get(node)){
        inDegrees[neighbor]--;
        if(inDegrees[neighbor] == 0){
          queue.offer(neighbor);
        }
      }
    }
    return idx != numNodes ? null : ordering;
  }

  private static int[] calculateInDegrees(List<List<Integer>> adjacencyList, int numNodes){
    int[] inDegrees = new int[numNodes];
    for(int i = 0; i < numNodes; i++){
      for(int node: adjacencyList.get(i)){
        inDegrees[node]++;
      }
    }
    return inDegrees;
  }

  public static void main(String[] args) {
    List<List<List<Integer>>> adjacencyLists = Arrays.asList(
      // First two ones from GFG
      Arrays.asList(
          Collections.emptyList(), Collections.emptyList(), Collections.singletonList(3),
          Collections.singletonList(1), Arrays.asList(0, 1), Arrays.asList(0, 2)
      ),
      Arrays.asList(
          Collections.singletonList(1), Collections.singletonList(2) , Collections.emptyList(),
          Arrays.asList(2, 4), Collections.emptyList()
      ),
      // Next two ones mine, last one has cycle
      Arrays.asList(
        Collections.singletonList(6), Arrays.asList(2, 3), Collections.emptyList(),
        Collections.emptyList(), Collections.singletonList(2), Collections.singletonList(6),
        Collections.singletonList(1)
      ),
      Arrays.asList(
          Arrays.asList(5, 6), Arrays.asList(2, 3), Collections.emptyList(),
          Collections.singletonList(0), Collections.singletonList(5), Collections.emptyList(),
          Collections.singletonList(1)
      )
    );
    for(List<List<Integer>> adjacencyList : adjacencyLists){
      int[] topologicalSorting = toplogicalSorting(adjacencyList, adjacencyList.size());
      System.out.println(Arrays.toString(topologicalSorting));
    }
  }
}
