package leetcode;

import lombok.NonNull;
import org.javatuples.Pair;

import java.util.*;

import static leetcode.NumberOfIslands.Traversal.BFS;
import static leetcode.NumberOfIslands.Traversal.DFS;

/**
 * <a href="https://leetcode.com/problems/number-of-islands/">Problem 200</a>
 */
public class NumberOfIslands {

    enum Traversal {
        BFS, DFS;
    }

    private final HashSet<Pair<Integer, Integer>> visitedCells = new HashSet<>();
    public static final char LAND = '1';
    public static final char WATER = '0';

    public int numIslands(@NonNull char[][] grid) {
        assert grid.length > 0 && binaryInput(grid) :  "Need a non- empty binary array as input";
       // return numIslands(grid, BFS);
        return numIslands(grid, DFS);
    }

    private int numIslands(char [][] grid, Traversal traversal){
        int numIslands = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                if (islandCell(i, j, grid) && !visitedCells.contains(new Pair<>(i, j))){
                    if(traversal == BFS) {
                        bfs(i, j, grid);
                    } else{
                        dfs(i, j, grid);
                    }
                    numIslands++;
                }
            }
        }
        visitedCells.clear();
        return numIslands;
    }


    /* Breadth - first search implementation. */
    private void bfs(int iRoot, int jRoot, char[][] grid){
        Deque<Pair<Integer, Integer>> visitationQueue = new ArrayDeque<>();
        visitationQueue.addLast(new Pair<>(iRoot, jRoot));
        while(!visitationQueue.isEmpty()){
            Pair<Integer, Integer> next = visitationQueue.pollFirst();
            visitedCells.add(next);
            int i = next.getValue0();
            int j = next.getValue1();
            // Go clockwise starting with 12 o'clock
            if(withinBounds(i - 1, j, grid) && islandCell(i - 1, j, grid) && !visitedCells.contains(new Pair<>(i - 1, j))){
                visitationQueue.addLast(new Pair<>(i -1, j));
            }
            if(withinBounds(i, j + 1, grid) && islandCell(i, j + 1, grid) && !visitedCells.contains(new Pair<>(i, j + 1))){
                visitationQueue.addLast(new Pair<>(i , j+1));
            }
            if(withinBounds(i + 1, j, grid) && islandCell(i + 1, j, grid) && !visitedCells.contains(new Pair<>(i + 1, j))){
                visitationQueue.addLast(new Pair<>(i + 1, j));
            }
            if(withinBounds(i , j - 1, grid) && islandCell(i , j - 1, grid) && !visitedCells.contains(new Pair<>(i, j - 1))){
                visitationQueue.addLast(new Pair<>(i, j - 1));
            }
        }
    }


    /* Depth - first search implementation. */
    private void dfs(int i, int j, char[][] grid){
        if(withinBounds(i, j, grid) && islandCell(i, j, grid) && !visitedCells.contains(new Pair<>(i, j))){
            visitedCells.add(new Pair<>(i, j));
            // Clockwise rotation of neighbors.
            dfs(i - 1, j, grid);
            dfs(i , j + 1, grid);
            dfs(i + 1, j, grid);
            dfs(i , j - 1, grid);
        }
    }
    /* Utilities */

    private boolean binaryInput(char[][] grid){
        return Arrays.stream(grid).allMatch(row->new String(row).chars().mapToObj(i->(char)i).allMatch(c-> c == '0' || c == '1'));
    }

    private boolean withinBounds(int i, int j, char[][] grid){
        assert grid.length > 0 : "withinBounds() method: the grid was empty.";
        return i >= 0 && i < grid.length && j >= 0 && j < grid[0].length;
    }

    private boolean islandCell(int i, int j, char[][] grid){
        assert withinBounds(i, j, grid) : "islandCell() method: parameters i=" + i + " and j = " + j + " were inappropriate.";
        return grid[i][j] == LAND;
    }

    private boolean waterCell(int i, int j, char[][] grid){
        assert withinBounds(i, j, grid) : "waterCell() method: parameters i=" + i + " and j = " + j + " were inappropriate.";
        return !islandCell(i, j, grid);          // Since at this point we have assured our input is ok.
    }

    // Main method with several test cases.
    public static void main(String[] args){
        char[][][] grids = {
                new char[][]{   // Expected num islands: 0
                        new char[]{WATER, WATER},
                        new char[]{WATER, WATER},
                },
                new char[][]{   // Expected num islands:  1
                        new char[]{LAND, WATER},
                        new char[]{WATER, WATER},
                },
                new char[][]{   // Expected num islands: 1
                        new char[]{WATER, LAND},
                        new char[]{WATER, WATER},
                },
                new char[][]{   // Expected num islands: 1
                        new char[]{WATER, WATER},
                        new char[]{WATER, LAND},
                },
                new char[][]{   // Expected num islands: 1
                        new char[]{WATER, WATER},
                        new char[]{LAND, WATER},
                },
                new char[][]{   // Expected num islands: 1
                        new char[]{LAND, LAND, WATER},
                        new char[]{LAND, WATER, WATER},
                        new char[]{LAND, LAND, WATER},
                },
                new char[][]{   // Expected num islands:  2
                        new char[]{LAND, LAND, WATER},
                        new char[]{LAND, WATER, LAND},
                        new char[]{LAND, LAND, WATER},
                },
                new char[][]{   // Expected num islands: 3
                        new char[]{LAND, LAND, WATER, LAND},
                        new char[]{LAND, WATER, WATER, LAND},
                        new char[]{WATER, LAND, WATER, WATER},
                },
                new char[][]{   // Expected num islands: 4
                        new char[]{LAND, WATER, LAND, WATER},
                        new char[]{WATER, LAND, WATER, LAND},
                        new char[]{WATER, LAND, LAND, WATER},
                },
                new char[][]{   // Expected num islands: 1
                        new char[]{LAND, LAND, LAND, LAND, LAND},
                        new char[]{LAND, LAND, LAND, LAND, LAND},
                        new char[]{LAND, LAND, LAND, LAND, LAND},
                },
                new char[][]{   // Expected num islands: 2
                        new char[]{LAND, LAND, LAND, LAND, LAND},
                        new char[]{LAND, LAND, LAND, LAND, WATER},
                        new char[]{LAND, LAND, LAND, WATER, LAND},
                },
                new char[][]{   // Expected num islands: 5
                        new char[]{WATER, WATER, LAND, WATER, WATER, WATER, WATER, WATER, LAND, LAND},
                        new char[]{WATER, LAND, LAND, LAND, WATER, WATER, WATER, WATER, LAND, LAND},
                        new char[]{WATER, WATER, LAND, WATER, LAND, WATER, WATER, WATER, WATER, LAND},
                        new char[]{WATER, WATER, LAND, WATER, LAND, WATER, WATER, WATER, WATER, WATER},
                        new char[]{WATER, LAND, LAND, LAND, WATER, WATER, WATER, WATER, LAND, LAND},
                        new char[]{WATER, WATER, LAND, WATER, WATER, WATER, WATER, WATER, WATER, WATER},
                        new char[]{WATER, WATER, WATER, WATER, WATER, WATER, WATER, WATER, WATER, LAND},
                        new char[]{WATER, WATER, WATER, WATER, WATER, WATER, WATER, WATER, WATER, LAND},
                },
        };

        NumberOfIslands solution = new NumberOfIslands();
        for(char[][] grid: grids){
            System.out.println("---- \nGrid:\n" + gridStringifier(grid) + "has " + solution.numIslands(grid) + " islands.\n----");
        }
    }


    private static String gridStringifier(char[][] grid){
        StringBuilder stringBuilder = new StringBuilder();
        for(char[] row: grid){
            stringBuilder.append(row).append('\n');
        }
        return stringBuilder.toString();
    }

}
