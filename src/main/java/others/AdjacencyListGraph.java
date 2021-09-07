package others;

import java.util.*;

public class AdjacencyListGraph {

    private int numNodes;
    private int numEdges;
    private boolean isDirected;
    private Map<Integer, Set<Integer>> adjacencyList;

    public AdjacencyListGraph(int[][] edgeList, int numNodes, boolean isDirected){
        assert edgeList != null && edgeList.length > 0 && Arrays.stream(edgeList).allMatch(edge->edge.length == 2);
        this.isDirected = isDirected;
        this.numNodes = numNodes;
        this.numEdges = 0;
        buildAdjacencyListFromEdgeList(edgeList);
    }

    private void buildAdjacencyListFromEdgeList(int[][] edgeList){
        adjacencyList = new HashMap<>();
        for(int[] edge : edgeList){
            addEdge(edge[0], edge[1]);
            if(!isDirected){
                addEdge(edge[1], edge[0]);
            }
        }
    }

    public boolean isDirected() {
        return isDirected;
    }

    public boolean hasEdge(int source, int dest){
        if(adjacencyList.containsKey(source)){
            return adjacencyList.get(source).contains(dest);
        } else {
            return false;
        }
    }

    public int getNumEdges(){
        return numEdges;
    }

    public int getNumNodes(){
        return numNodes;
    }


    /* The following two methods do nothing if the node and edge are already there. It's an unweighted graph. */
    public AdjacencyListGraph addNode(int node){
        if(!adjacencyList.containsKey(node)){
            adjacencyList.put(node, new HashSet<>(Collections.emptySet()));
            numNodes++;
        } else{
            throw new RuntimeException("Node already in graph");
        }
        return this;
    }

    public AdjacencyListGraph addEdge(int source, int dest){
        if(adjacencyList.containsKey(source) ){
            boolean edgeWasAlreadyInGraph = adjacencyList.get(source).contains(dest);
            adjacencyList.get(source).add(dest);        // This will add it even if it is already in the mapped set.
            if(!edgeWasAlreadyInGraph){
                numEdges++;
            }
        } else {
            adjacencyList.put(source, new HashSet<>(Collections.singleton(dest)));
            numEdges++;
        }
         if(!isDirected && source != dest){
             isDirected = true;
             addEdge(dest, source);
             isDirected = false;
         }
         return  this;
    }

//    public AdjacencyListGraph addEdge(int source, int dest){
//        if(adjacencyList.containsKey(source) && adjacencyList.containsKey(dest)){
//            boolean edgeWasAlreadyInGraph = adjacencyList.get(source).contains(dest);
//            adjacencyList.get(source).add(dest);        // This will add it even if it already were in the graph.
//            if(!edgeWasAlreadyInGraph){
//                numEdges++;
//            }
//            return this;
//        } else {
//            throw new RuntimeException("Both nodes should already be in graph.");
//        }
//    }

    /* Main method with test cases */

    public static void main(String[] args){
        System.out.println("Beginning...");
        int[][] edges = { {1, 1}, {1, 2}, {2, 3}, {3, 4}, {4, 2}};
        AdjacencyListGraph directedGraph = new AdjacencyListGraph(edges, 4, true);
        satisfyOrThrow(directedGraph.isDirected(), "Graph is directed.");
        satisfyOrThrow(directedGraph.getNumNodes() == 4, "Expecting 4 nodes.");
        satisfyOrThrow(directedGraph.getNumEdges() == 5, "Expecting 5 edges.");
        satisfyOrThrow(Arrays.stream(edges).allMatch(edge->directedGraph.hasEdge(edge[0], edge[1])), "All inserted edges should be found in graph");
        directedGraph.addNode(8).addNode(12).addNode(10).addEdge(8, 10).addEdge(1, 8);
        satisfyOrThrow(directedGraph.getNumNodes() == 7, "Expecting 7 nodes.");
        satisfyOrThrow(directedGraph.getNumEdges() == 7, "Expecting 7 edges.");
        System.out.println("Finishing...");

    }

    private static void satisfyOrThrow(boolean condition, String message){
        if(!condition){
            throw new AssertionError(message);
        } else{
            System.out.println(message);
        }
    }
}
