package shuffld.thegame.ShuffLD_the_game;
import java.util.*;

import shuffld.ShuffLDModel;

public class mm2174Digger {
    public static int diggerBot(char[][] array1, char[][] array2) {
        int moves = 0;
        
        for (int i = 0; i < array1.length; i++) {
            for (int j = 0; j < array1[0].length; j++) {
                if (array1[i][j] != array2[i][j]) {
                    int targetRow = findSwap(array1[i][j], array2, i, j);
                    swapRows(array1, i, targetRow, j);
                    moves++;

                    if (moves == 20) 
                    {
                        System.out.println("Moves have gone above 20");
                        System.exit(0);
                    }
                }
            }
        }
        
        return moves;
    }
    
    private static int findSwap(char target, char[][] array, int currentRow, int currentColumn) {
        for (int i = currentRow; i < array.length; i++) {
            if (array[i][currentColumn] == target) {
                return i;
            }
        }
        
        // Should never get here, as we assume the arrays have the same letters
        return -1;
    }
    
    private static void swapRows(char[][] array, int row1, int row2, int column) {
        char temp = array[row1][column];
        System.out.println(array[row1][column] + " swaps with " + array[row2][column]);
        array[row1][column] = array[row2][column];
        array[row2][column] = temp;
        
    }
    
    public static void main(String[] args) {
        char[][] array1 = {{'a', 'b', 'c', 'd', 'e'},
                           {'f', 'g', 'h', 'i', 'j'},
                           {'k', 'l', 'm', 'n', 'o'},
                           {'p', 'q', 'r', 's', 't'},
                           {'u', 'v', 'w', 'x', 'y'}};
        
        char[][] array2 = {{'f', 'g', 'h', 'i', 'j'},
                           {'a', 'b', 'c', 'd', 'e'},
                           {'p', 'q', 'r', 's', 't'},
                           {'k', 'l', 'm', 'n', 'o'},
                           {'u', 'v', 'w', 'x', 'y'}};
        
        int moves = diggerBot(array1, array2);
        System.out.println("Moves: " + moves);
        
        
    }
}
