package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Jason
 *
 * <a href = "https://leetcode.com/problems/valid-sudoku/">Valid sudoku (Leetcode #36)</a>
 */
public class ValidSudoku {

    public static boolean isValidSudoku(char[][] board) {
        // Assume board a 9 x 9 array

        // Unchecked assignments, but we can
        // live with them for this problem:
        Set<Character>[] rowSets = new HashSet[9];
        Set<Character>[] colSets = new HashSet[9];
        Set<Character>[] gridSets = new HashSet[9];

        initArray(rowSets);
        initArray(colSets);
        initArray(gridSets);

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(board[i][j] != '.'){ // Filled-in cell
                    if(!checkSet(board[i][j], rowSets[i])){
                        return false;
                    }
                    if(!checkSet(board[i][j], colSets[j])){
                        return false;
                    }
                    if(!checkSet(board[i][j], gridSets[mapToGrid(i, j)])){
                        return false;
                    }
                }

            }
        }
        return true;
    }

    private static void initArray(Set[] array){
        for(int i = 0; i < array.length; i++){
            array[i] = new HashSet<>();
        }
    }

    private static boolean checkSet(char element, Set<Character> set){
        if(set.contains(element)){
            return false;
        }
        set.add(element);
        return true;
    }

    private static int mapToGrid(int i, int j){
        return j / 3 + ( i / 3 ) * 3; // Note that / is DIV
    }

    public static void main(String[] args) {

    }
}
