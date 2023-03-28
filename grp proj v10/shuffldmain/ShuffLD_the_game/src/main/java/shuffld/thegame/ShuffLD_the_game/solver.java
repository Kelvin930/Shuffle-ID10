
package shuffld.thegame.ShuffLD_the_game;

import java.util.Arrays;

public class solver {
    public static void main(String[] args) {
        char[][] normalArray = {
                {'A', 'B', 'C', 'D', 'E'},
                {'F', 'G', 'H', 'I', 'J'},
                {'K', 'L', 'M', 'N', 'O'},
                {'P', 'Q', 'R', 'S', 'T'},
                {'U', 'V', 'W', 'X', 'Y'}
        };

        char[][] scrambledArray = {
            {'K', 'G', 'C', 'N', 'E'},
            {'F', 'B', 'W', 'I', 'T'},
            {'U', 'Q', 'H', 'X', 'Y'},
            {'P', 'L', 'M', 'D', 'O'},
            {'A', 'V', 'R', 'S', 'J'}
        };

        solveScrambledArray(normalArray, scrambledArray);
    }

    public static void solveScrambledArray(char[][] normalArray, char[][] scrambledArray) {
        int numRows = normalArray.length;
        int numCols = normalArray[0].length;
        int turnCount = 0;

        // Create a copy of the scrambled array to work on
        char[][] workingArray = new char[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            workingArray[i] = Arrays.copyOf(scrambledArray[i], numCols);
        }

        // Brute-force method to solve the scrambled array
        boolean solved = false;
        while (!solved) {
            solved = true;
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numCols; j++) {
                    if (workingArray[i][j] != normalArray[i][j]) {
                        // Find the row where the correct character is located
                        int correctRow = -1;
                        for (int k = 0; k < numRows; k++) {
                            if (workingArray[k][j] == normalArray[i][j] && k != i) {
                                correctRow = k;
                                break;
                            }
                        }

                        // If the correct character is not in the same column, skip this move
                        if (correctRow == -1) {
                            continue;
                        }

                        // Swap the characters in the same column
                        char temp = workingArray[i][j];
                        workingArray[i][j] = workingArray[correctRow][j];
                        workingArray[correctRow][j] = temp;
                        turnCount++;

                        // Print the updated array
                        for (int k = 0; k < numRows; k++) {
                            System.out.println(Arrays.toString(workingArray[k]));
                        }
                        System.out.println();
                        System.out.println(turnCount);

                        solved = false;
                    }
                }
            }
        }
    }
}
