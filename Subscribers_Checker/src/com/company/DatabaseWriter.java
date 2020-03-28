package com.company;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        // Reading password to the database
        StringBuilder contentBuilder = new StringBuilder();
        String dataPath = "/Users/anoyanov/Work/SubscriberMonitor-Git/Subscribers_Checker/src/com/company/mySQLpassword.txt";
        try (Stream<String> stream = Files.lines( Paths.get(dataPath), StandardCharsets.UTF_8)){
            //stream.forEach(s -> contentBuilder.append(s).append("\n"));
            System.out.println(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    // To write basic data to this simple table
     public void writeDataSimple(String userName, String date, int subsNumber){

     }

}
