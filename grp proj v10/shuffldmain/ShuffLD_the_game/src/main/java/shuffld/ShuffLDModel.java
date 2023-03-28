package shuffld;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.*;


public class ShuffLDModel {
    private static Connection connection;
    private static String[] words = new String[5];
    
    private static LocalDateTime todaysWords = LocalDateTime.MIN;

    private static void connect()
    {
        if(connection == null)
        {
            try
            {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(
                        "jdbc:mysql://bdvzifhmniroo0sjysfr-mysql.services.clever-cloud.com:3306", "uv4scusnkh5wzjyp",
                        "vSx6PkXuncaASuh4MmKa");
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String[] getWords() {
        if (connection == null) {
            connect();
        }
        //Sets a time to make sure the DB takes new words each day
        LocalDateTime now = LocalDateTime.now();
        if (todaysWords.isBefore(now.minusDays(1))) {
            todaysWords = now;
            try {
                //Selects 5 random words from the database, and puts them into the String words array, which then the word Randomiser can work 
                PreparedStatement wordGame;
                String wordGameStatement = "SELECT words FROM bdvzifhmniroo0sjysfr.`User` ORDER BY RAND() LIMIT 5;";
                wordGame = connection.prepareStatement(wordGameStatement);
                ResultSet rs = wordGame.executeQuery();
                for (int i = 0; i < 5; i++)
                {
                    rs.next();
                    words[i] = rs.getString(1);
                }
                System.out.println(Arrays.toString(words));
            } catch( Exception e) {
                e.printStackTrace();
            }
        }
        return words;
    }

}

