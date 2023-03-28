package shuffld.thegame.ShuffLD_the_game;
import java.lang.Object;
public class lock
{
    public static void main(String[] args) 
    {
        char[][] grid = new char[5][5];

        grid[0][0] = 'j';
        grid[0][1] = 'p';
        grid[0][2] = 't';
        grid[0][3] = 'n';
        grid[0][4] = 'h';
        grid[1][0] = 's';
        grid[1][1] = 'i';
        grid[1][2] = 'i';
        grid[1][3] = 'l';
        grid[1][4] = 'c';
        grid[2][0] = 'b';
        grid[2][1] = 'p';
        grid[2][2] = 'o';
        grid[2][3] = 'i';
        grid[2][4] = 't';
        grid[3][0] = 'a';
        grid[3][1] = 'o';
        grid[3][2] = 'm';
        grid[3][3] = 'n';
        grid[3][4] = 'k';
        grid[4][0] = 'm';
        grid[4][1] = 'o';
        grid[4][2] = 'a';
        grid[4][3] = 't';
        grid[4][4] = 'y';
        
        for (int a = 0; a <= 4; a++) 
        {

            for (int b = 0; b <= 4; b++) 
            {

                for (int c = 0; c <= 4; c++) 
                {

                    for (int d = 0; d <= 4; d++) 
                    {

                        for (int e = 0; e <= 4; e++) 
                        {

                            String s = grid[a][0] + "" + grid[b][1] + "" + grid[c][2] + "" +  grid[d][3] + "" + grid[e][4];
                            System.out.println(s);
                        }

                    }

                }

            }

        }

        

    }

}
