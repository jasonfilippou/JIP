package others;
import java.util.*;
/**
 * A masked connected - components problem. We are given a list of lists called dependenceList. DependenceList(i) = j
 * tells us that tasks i and j are connected. Each family of connected tasks can be handled by a single worker.
 * Return the minimum number of workers that we need to finish all tasks.
 *
 *
 */
public class NumberOfWorkers
{

	public static int getMinimumNumberOfWorkers(final List<List<Integer>> dependencies, final int numNodes)
	{
		final List<List<Integer>> adjacencyList = createAdjacencyList(dependencies, numNodes);
		return numConnectedComponents(adjacencyList, numNodes);
	}

	private static List<List<Integer>> createAdjacencyList(final List<List<Integer>> dependencies, final int numNodes)
	{
		final List<List<Integer>> retVal = new ArrayList<>(numNodes);           // Pre-allocate for efficiency.
		for(int i = 0; i < numNodes; i++)
		{
			retVal.add(new ArrayList<>());
		}
		for(final List<Integer> dependency : dependencies)
		{
			// No duplicates in the list are assumed, and we won't check for that. But we *will*
			// check for the inner list to be a pairwise one.
			assert dependency.size() == 2 : "We were expecting a list of size 2 here.";
			retVal.get(dependency.get(0) - 1).add(dependency.get(1) - 1);       // jobs are 1-indexed
			retVal.get(dependency.get(1) - 1).add(dependency.get(0) - 1);
		}
		return retVal;
	}

	private static Integer numConnectedComponents(final List<List<Integer>> adjacencyList, final int numNodes)
	{
		int numConnectedComponents = 0;
		final Set<Integer> visitedNodes = new HashSet<>();
		for(int node = 0; node < numNodes; node++)
		{
			if(!visitedNodes.contains(node))
			{
				visitedNodes.add(node);
				dfs(node, adjacencyList, visitedNodes);
				numConnectedComponents++;
			}
		}
		return numConnectedComponents;
	}

	private static void dfs(final int rootNode, final List<List<Integer>> adjacencyList, final Set<Integer> visitedNodes)
	{
		for(Integer neighbor : adjacencyList.get(rootNode))
		{
			if(!visitedNodes.contains(neighbor))
			{
				visitedNodes.add(neighbor);
				dfs(neighbor, adjacencyList, visitedNodes);
			}
		}
	}

	public static void main(String[] args)
	{
		System.out.println(getMinimumNumberOfWorkers(Arrays.asList(Arrays.asList(1, 2), Arrays.asList(2, 3), Arrays.asList(1, 3),
		                                                           Arrays.asList(5, 4), Arrays.asList(5, 3)), 5));  // Expecting 1
		System.out.println(getMinimumNumberOfWorkers( Arrays.asList(Arrays.asList(1, 2), Arrays.asList(2, 3), Arrays.asList(1, 3), Arrays.asList(5, 4)), 5)); // Expecting 2
		System.out.println(getMinimumNumberOfWorkers(Arrays.asList(Arrays.asList(1, 2), Arrays.asList(2, 5), Arrays.asList(4, 3), Arrays.asList(6, 7), Arrays.asList(8, 7),
		                                                           Arrays.asList(6, 8)), 8)); // Expecting 3
	}
}
