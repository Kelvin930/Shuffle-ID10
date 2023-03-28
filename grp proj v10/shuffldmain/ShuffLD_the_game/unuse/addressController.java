package shuffld.thegame.ShuffLD_the_game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/addressController")
public class addressController {

    public String userIp;
    public String databaseIP;
    public String[] breakIp;
    public String justNoIp = "";
    public boolean trigger = false;

    public boolean sender = false;
    public ArrayList<String> listOfIp = new ArrayList<>();


    public String userIpTwo;
  
    public String[] breakIpTwo;
    public String justNoIpTwo = "";

    private static Connection connection;

    private static void connect() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(
                        "jdbc:mysql://bdvzifhmniroo0sjysfr-mysql.services.clever-cloud.com:3306", "uv4scusnkh5wzjyp",
                        "vSx6PkXuncaASuh4MmKa");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @PostMapping("/save-ip-address")
    public ResponseEntity<String> saveIpAddress(@RequestBody String ipAddress) {
        // Process the IP address received from the client
        System.out.println("Received IP address: " + ipAddress);
        userIp = ipAddress;

        breakIp = userIp.split("");
        for (int i = 14; i < breakIp.length - 2; i++) {
            if (!breakIp[i].equals(".")) {
                justNoIp += breakIp[i];
            }
        }

        if (connection == null) {
            connect();
        }

        try {
            PreparedStatement ipQuery = connection
                    .prepareStatement("SELECT Complete from bdvzifhmniroo0sjysfr.`checkPlayer` WHERE ipAddr = ?");
            ipQuery.setString(1, justNoIp);
            ResultSet ipList = ipQuery.executeQuery();

            while(ipList.next()){
                System.out.println(ipList.getBoolean(1));
                if (trigger == ipList.getBoolean(1)){
                    trigger = true;
                    complete();
                }
                else{
                    trigger = true;
                    sender = true;
                    System.out.println(sender);
                    complete();
                }

            }
            if(!ipList.next() && trigger == false) {
                PreparedStatement insertIpQuery = connection.prepareStatement(
                        "INSERT INTO bdvzifhmniroo0sjysfr.checkPlayer (ipAddr, Complete) VALUES(?,?)");
                insertIpQuery.setString(1, justNoIp);
                insertIpQuery.setBoolean(2, false);
                insertIpQuery.execute();
                complete();
            }
            

        } catch (Exception e) {

            e.printStackTrace();
        }

        // Return a response to the client
        return ResponseEntity.ok("IP address saved successfully");
    }

    @GetMapping(value = { "/complete" })
    @ResponseBody
    public boolean complete() {
       
        return sender;
    }


    @PostMapping("/doneForTheDay")
    public ResponseEntity<String> saveForSecoundTime(@RequestBody String ipAddress) {
        // Process the boolean received from the client
        System.out.println("Received bool: " + ipAddress);
            
        userIpTwo = ipAddress;

        breakIpTwo = userIp.split("");
        for (int i = 14; i < breakIp.length - 2; i++) {
            if (!breakIp[i].equals(".")) {
                justNoIpTwo += breakIp[i];
            }
        }

        if (connection == null) {
            connect();
        }

        try {
            PreparedStatement UpdateQuery = connection
                    .prepareStatement("UPDATE bdvzifhmniroo0sjysfr.`checkPlayer` set Complete = ? WHERE ipAddr = ?");
            UpdateQuery.setBoolean(1, true);
            UpdateQuery.setString(2, justNoIpTwo);
            UpdateQuery.executeUpdate();
            System.out.println("updated");         

        } catch (Exception e) {

            e.printStackTrace();
        }

        // Return a response to the client
        return ResponseEntity.ok("bool saved successfully");
    }


}
