package shuffld.thegame.ShuffLD_the_game;
import java.util.*;

import shuffld.ShuffLDModel;
public class Digger
{
    public char[][] shuffledArray = Word_randomiser.getShuffledWords();
    //public char[][] shuffledArray = {
            //{'1', 'e', '3', 'r', '1'},
            //{'s', 'o', '3', 'd', '5'},
            //{'5', '2', 'o', '2', 'd'},
            //{'w', '4', 'r', '4', 's'},
            //{'t', 'w', 's', 't', 's'}
    //};
    public String[] solved = ShuffLDModel.getWords();
    //public String[] solved = {"tests", "words", "12345", "sword", "54321"};

    public int swaps = 0;

    private char[] getSolvedCol(int column) {
        char[] col = new char[5];

        for (int i = 0; i < 5; i++) {
            col[i] = solved[i].charAt(column);
        }

        return col;
    }

    private char[] getShuffleCol(int column) {

        char[] col = new char[5];

        for (int i = 0; i < 5; i++) {
            col[i] = shuffledArray[i][column];
        }

        return col;
    }

    private void setColumn(int column, char[] data) {
        for (int i = 0; i < 5; i++) {
            shuffledArray[i][column] = data[i];
        }
    }

    public void solveColumn(int column) {
        List<Integer> availableIdx = new ArrayList<>();

        char[] correct = getSolvedCol(column);

        // Get the shuffled characters for this column
        char[] shuffled = getShuffleCol(column);
        for (int i = 0; i < 5; ++i) {
            availableIdx.add(i);
        }

        // Sort the characters
        Arrays.sort(shuffled);

        // Attempt to solve the column
        boolean isSolved = false;
        while (!isSolved) {
            // Lock in any characters that are in the correct place
            for (int i = availableIdx.size() - 1; i >= 0; --i) {
                int idx = availableIdx.get(i);
                if (shuffled[idx] == correct[idx]) {
                    availableIdx.remove(i);
                }
            }

            Collections.rotate(availableIdx, 1);

            // Don't do any swaps if already solved
            if (availableIdx.size() > 1) {
                char tmp = shuffled[availableIdx.get(0)];
                shuffled[availableIdx.get(0)] = shuffled[availableIdx.get(1)];
                shuffled[availableIdx.get(1)] = tmp;
                ++swaps;
            }

            // Check if the column is solved
            isSolved = Arrays.equals(shuffled, correct);
        }
        setColumn(column, shuffled);
    }

    public void Print(char[][] shuffArray)
    {
        String[] printShuff = new String[shuffArray.length];
        for (int i = 0; i < shuffArray.length; i++)
        {
            printShuff[i] = new String(shuffArray[i]);
        }
        System.out.println(Arrays.toString(shuffArray));
    }

    public int solve() {
        
     for (int i = 0; i<5; i++)
     {
        System.out.println(new String(shuffledArray[i]));

     }
     for (int i = 0; i< 5; i++)
     {
        solveColumn(i);
     }
     for (int i=0; i < 5; i++)
     {
        System.out.println(new String(shuffledArray[i]));
     }

        System.out.println("the DigGR took "+ swaps + " Swaps to solve");
        return swaps;
    }

    public static void main(String[] args) {
        Digger dig = new Digger();
        dig.solve();
        
        
    }

}