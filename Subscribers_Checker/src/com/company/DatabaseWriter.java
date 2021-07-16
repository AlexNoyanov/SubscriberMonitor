package com.company;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

/**
            ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            This class is required to write all
            data to the following database

            By Alexander Noyanov
            March 28th 2020
            ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */


/*

    - Password to the database is in the mySQLpassword.txt file


    Queris to mySQL:
Creating simple sunbscribers table:
mysql> CREATE TABLE SimpleSubscribers(id INT PRIMARY KEY AUTO_INCREMENT, FullName VARCHAR(32),Subscribers INT, Date VARCHAR(64));

Adding values to this table:

 */

public class DatabaseWriter {

    private String databasePassword;        // Database password


    DatabaseWriter(){
        // Getting database password from file
        try {
            List<String> lines = Files.readAllLines(Paths.get("/Users/anoyanov/Work/SubscriberMonitor-Git/Subscribers_Checker/src/com/company/mySQLpassword.txt"));
            String sqlPassword = lines.get(0);          // MySQL password (Third one in the text file)
            databasePassword = sqlPassword;
            //System.out.println(databasePassword);
        }catch(Exception e){

        }

    }


    // To write basic data to this simple table
     public void writeDataSimple(String userName, String date, int subsNumber){
         //try {
             //Class.forName("com.mysql.jdbc.Driver");     // To set up timezone: SET GLOBAL time_zone = '+3:00';
             try {
                 //Class.forName("com.mysql.jdbc.Driver");     // To set up timezone: SET GLOBAL time_zone = '+3:00';
                 //Connection con = DriverManager.getConnection("jdbc:mysql://localhost/?user=root&password="+databasePassword);
                  //jdbc:mysql://localhost/db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Moscow
                // Statement stmt = con.createStatement();

                 Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Subscribers ?useUnicode=true&serverTimezone=UTC", "root", databasePassword); // Connecting to MySQL:
                 Statement stmt = con.createStatement();
                 String request = "INSERT INTO SimpleSubscribers(FullName,Subscribers,Date) values('"+ userName + "'," + subsNumber + ",'" + date + "'" + ");";

                 System.out.println("MySQL REQUEST:");
                 System.out.println(request);

                 int rs = stmt.executeUpdate(request);

                 con.close();

             } catch (Exception e) {
                 e.printStackTrace();
             }

         //} catch (ClassNotFoundException e) {
         //    e.printStackTrace();
         //}

     }

    public void sendMessageToDatabase(LocalDateTime date, String userMessage, long chatID) {
        try {
            Class.forName("com.mysql.jdbc.Driver");     // To set up timezone: SET GLOBAL time_zone = '+3:00';
            List<String> lines = Files.readAllLines(Paths.get("/Users/anoyanov/Work/TBot/src/main/java/com/alexn/botInfo.txt"));
            String sqlPassword = lines.get(2);          // MySQL password (Third one in the text file)
            Connection con = DriverManager.getConnection(

                    "jdbc:mysql://localhost/TelegramBot", "root", sqlPassword);
            // jdbc:mysql://localhost/db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Moscow
            Statement stmt = con.createStatement();
            String request = "INSERT INTO userMessages(Date,ChatID,Message) values('";
            request = request.concat(String.valueOf(date));
            request = request.concat("', ");
            request = request.concat( String.valueOf(chatID));
            request = request.concat(",'");
            request = request.concat(userMessage);
            request = request.concat("');");

            System.out.println("MySQL REQUEST:");
            System.out.println(request);

            int rs = stmt.executeUpdate(request);

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
