package shuffld.thegame.ShuffLD_the_game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accountController")
public class accountController {

    //public boolean workPlease = false;

    // public String username = "";
    // public String password = "";

    public int indatabaseWin = 0;
    public int indatabasePlayed = 0;
    public int indatabasewordGuessed = 0;

    public int respondDepender; // 0 = wrong password or username 1 = endGame 2 = drawbox 3= no match
    public int insertOrNot; // 0 = inserted 1= username already existed
    private static Connection connection;

    public String achievementGoingBackToFrontEnd;

    private static void connect() {
   
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(
                        "jdbc:mysql://containers-us-west-185.railway.app:7960", "root",
                        "zt8DLFJYuSFVP3XntvUt");
            } catch (Exception e) {
                e.printStackTrace();
            }
      
    }

    @PostMapping("/saveUsername")
    public ResponseEntity<String> saveUsername(@RequestBody String sendUsername) {
        // Process the IP address received from the client
        System.out.println("Received username: " + sendUsername);

        boolean trigger = false;
        String username = "";
        String password = "";
        respondDepender = 3;
        String[] breakWord = sendUsername.split("");
        for (int i = 17; i < breakWord.length - 2; i++) {
            if (!breakWord[i].equals("^") && trigger == false) {
                username += breakWord[i];
            } else if (breakWord[i].equals("^")) {
                trigger = true;
            } else {
                password += breakWord[i];
            }
        }
        if (username != null) {
            initial(username);
        }

        trigger = false;
        System.out.println("username: " + username);
        System.out.println("password: " + password);

     
            connect();
       

        try {

            PreparedStatement matchQuery = connection
                    .prepareStatement(
                            "SELECT password,complete from railway.`accountSystem` WHERE userName = ?");
            matchQuery.setString(1, username);
            ResultSet matchSet = matchQuery.executeQuery();

            while (matchSet.next()) {
                if (password.equals(matchSet.getString(1))) {

                    boolean y = matchSet.getBoolean(2);
                    if (y == true) {

                        respondDepender = 1;
                        complete();
                        System.out.println("1");
                    } else if (y == false) {
                        respondDepender = 2;
                        complete();
                        System.out.println("2");
                    }
                } else {

                    respondDepender = 0;
                    complete();
                    System.out.println(matchSet.getString(1));
                    System.out.println("wrong password");
                } // use int the determine what to send back
            }
            if (respondDepender == 3) {
                System.out.println("wrong username");
            }

            username = "";
            password = "";

        } catch (Exception e) {

            e.printStackTrace();
        }

        // Return a response to the client
        return ResponseEntity.ok("username saved successfully");
    }

    @PostMapping("/saveRegUsername")
    public ResponseEntity<String> saveRegUsername(@RequestBody String sendRegUsername) {
        // Process the IP address received from the client
        System.out.println("Received username: " + sendRegUsername);

        int depender = 0;
        insertOrNot = 0;
        String[] split = sendRegUsername.split("");
        String insertUsername = "";
        String insertPassword = "";
        String insertEmail = "";

       
            connect();
  

        for (int i = 20; i < split.length - 2; i++) {

            if (split[i].equals("^")) {

                depender++;
            }

            if (depender == 0 && !split[i].equals("^")) {
                insertUsername += split[i];
            } else if (depender == 1 && !split[i].equals("^")) {
                insertPassword += split[i];
            } else if (depender == 2 && !split[i].equals("^")) {
                insertEmail += split[i];
            } else {
                System.out.println("waste character");
            }
        }

        System.out.println(insertUsername);
        System.out.println(insertPassword);
        System.out.println(insertEmail);

        // check if the register name excist or not
        try {

            PreparedStatement matchQuery = connection
                    .prepareStatement(
                            "SELECT userName from railway.`accountSystem`");
            ResultSet matchSet = matchQuery.executeQuery();

            while (matchSet.next()) {
                if (matchSet.getString(1).equals(insertUsername)) {

                    insertOrNot = 1;
                    System.out.print("username already exist");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // inserting data into database to create a new player
        if (insertOrNot == 0) {
            try {

                PreparedStatement insertRegQuery = connection
                        .prepareStatement(
                                "INSERT INTO railway.`accountSystem` (userName, password, complete, emailAddress) VALUES(?,?,?,?)");
                insertRegQuery.setString(1, insertUsername);
                insertRegQuery.setString(2, insertPassword);
                insertRegQuery.setBoolean(3, false);
                insertRegQuery.setString(4, insertEmail);
                insertRegQuery.execute();

                System.out.println("inserted");

            } catch (Exception e) {

                e.printStackTrace();
            }

            try {

                PreparedStatement insertRegQuery = connection
                        .prepareStatement(
                                "INSERT INTO railway.`playerStreak` (userName, played, win, wordGuessed) VALUES(?,?,?,?)");
                insertRegQuery.setString(1, insertUsername);
                insertRegQuery.setInt(2, 0);
                insertRegQuery.setInt(3, 0);
                insertRegQuery.setInt(4, 0);
                insertRegQuery.execute();

                System.out.println("inserted");

            } catch (Exception e) {

                e.printStackTrace();
            }
        }
        // Return a response to the client
        return ResponseEntity.ok("username saved successfully");
    }

    @GetMapping(value = { "/complete" })
    @ResponseBody
    public int complete() {
        return respondDepender;
    }

    @GetMapping(value = { "/registration" })
    @ResponseBody
    public int registration() {
        return insertOrNot;
    }

    @GetMapping(value = { "/initial" })
    @ResponseBody
    public String initial(String usernames) {
        boolean workPlease = false;
        if (workPlease == false) {

           
                connect();
          

            try {

                PreparedStatement matchQuery = connection
                        .prepareStatement(
                                "SELECT played, win, wordGuessed from railway.`playerStreak` WHERE userName = ?");
                matchQuery.setString(1, usernames);
                ResultSet matchSet = matchQuery.executeQuery();

                while (matchSet.next()) {
                    indatabasePlayed = matchSet.getInt(1);
                    indatabaseWin = matchSet.getInt(2);
                    indatabasewordGuessed = matchSet.getInt(3);
                }

            } catch (Exception e) {

                e.printStackTrace();
            }

            workPlease = true;

            System.out.println("first");
            System.out.println(usernames);
            System.out.println("first");
            System.out.println(Integer.toString(indatabasePlayed) + "^"
                    + Integer.toString(indatabaseWin) + "^"
                    + Integer.toString(indatabasewordGuessed));

        }
        // Return a response to the client
        return Integer.toString(indatabasePlayed) + "^"
                + Integer.toString(indatabaseWin) + "^"
                + Integer.toString(indatabasewordGuessed);
    }

    @PostMapping("/completedTheGame")
    public ResponseEntity<String> updateComplete(@RequestBody String ultimateUsername) {
        // Process the IP address received from the client
        System.out.println("Received username: " + ultimateUsername);

        String[] split = ultimateUsername.split("");
        String matchUsername = "";
        for (int i = 6; i < split.length - 2; i++) {
            matchUsername += split[i];
        }

        System.out.println("username for update is: " + matchUsername);

       
            connect();
       
        try {

            PreparedStatement updateQuery = connection
                    .prepareStatement(
                            "UPDATE railway.`accountSystem` SET complete = ? WHERE userName =?");
            updateQuery.setBoolean(1, true);
            updateQuery.setString(2, matchUsername);

            updateQuery.executeUpdate();

            System.out.println("worked");

        } catch (Exception e) {
            e.printStackTrace();
        }
        // Return a response to the client
        return ResponseEntity.ok("username saved successfully");
    }

    @PostMapping("/updatePlayerAchievement")
    public ResponseEntity<String> updatePlayerAchievement(@RequestBody String streak) {
        // Process the IP address received from the client
        System.out.println("Received streak numbers:  " + streak);

        String[] split = streak.split("");
        int depender = 0;
        String wonOrNot = "";
        String totalWordGuess = "";
        String matchUsername = "";
        int databaseWin = 0;
        int databasePlayed = 0;
        int databasewordGuessed = 0;

        for (int i = 11; i < split.length - 2; i++) {

            if (depender == 0 && !split[i].equals("^")) {

                wonOrNot += split[i];
            }

            if (depender == 1 && !split[i].equals("^")) {

                totalWordGuess += split[i];
            }

            if (depender == 2 && !split[i].equals("^")) {
                matchUsername += split[i];
            }

            if (split[i].equals("^")) {
                depender++;
            }
        }

        System.out.println("wonOrNot: " + Integer.parseInt(wonOrNot));
        System.out.println("totalWordGuesss: " + Integer.parseInt(totalWordGuess));

       
            connect();

        try {

            PreparedStatement matchQuery = connection
                    .prepareStatement(
                            "SELECT played, win, wordGuessed from railway.`playerStreak` WHERE userName = ?");
            matchQuery.setString(1, matchUsername);
            ResultSet matchSet = matchQuery.executeQuery();

            while (matchSet.next()) {
                databasePlayed = matchSet.getInt(1);
                databaseWin = matchSet.getInt(2);
                databasewordGuessed = matchSet.getInt(3);
            }

            System.out.println("databaseplayed: " + databasePlayed);
            System.out.println("databaseWin: " + databaseWin);
            System.out.println("databasewordGuess: " + databasewordGuessed);

        } catch (Exception e) {

            e.printStackTrace();
        }

        try {

            PreparedStatement updateStatsQuery = connection
                    .prepareStatement(
                            "UPDATE railway.`playerStreak` SET played = ? , win = ?, wordGuessed = ?  WHERE userName =?");
            updateStatsQuery.setInt(1, databasePlayed + 1);
            updateStatsQuery.setInt(2, databaseWin + Integer.parseInt(wonOrNot));
            updateStatsQuery.setInt(3, databasewordGuessed + Integer.parseInt(totalWordGuess));
            updateStatsQuery.setString(4, matchUsername);

            updateStatsQuery.executeUpdate();

            achievementGoingBackToFrontEnd = Integer.toString(databasePlayed + 1) + "^"
                    + Integer.toString(databaseWin + Integer.parseInt(wonOrNot)) + "^"
                    + Integer.toString(databasewordGuessed + Integer.parseInt(totalWordGuess));
            updateFrontEndAchievement();
            System.out.println(achievementGoingBackToFrontEnd);

            System.out.println("last worked");

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Return a response to the client
        return ResponseEntity.ok("username saved successfully");
    }

    @GetMapping(value = { "/updateFrontEndAchievement" })
    @ResponseBody
    public String updateFrontEndAchievement() {
        return achievementGoingBackToFrontEnd;
    }

    @GetMapping(value = { "/updateFrontEndAchievementAtStart" })
    @ResponseBody
    public String getInitialFrontEndAchievement() {
        return achievementGoingBackToFrontEnd;
    }
}