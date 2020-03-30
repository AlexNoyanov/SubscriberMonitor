package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


/**    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *
 *      Getting number of Instagram Subscribers for user
 *      All data will be saved to mySQL database
 *
 *      By Alexander Noyanov
 *      March 25th 2020
 *      ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *
 */


/*

    TODO:
    - Connect MySQL database                (DONE!)
    - Save data to the database             (Done!)
    - Get number of subscribers on the page (Done!)
    - Find number of subscribers on the page(Done!)

    On March 30 2020:
    - Find a list of all subscribers and subscriptions for user
    - Loading all subscribers for user to his own table
    - Analise number of subscribers
    - Find If your subscriptian subscribed on you too
    - Automatically update database every hour

 */


public class Main {

    public static void main(String[] args) {

        String replyText = "";
        int hours = 0;
        // ====== To update database every hour: ====
        //String userName = "alex_noyanov";                                              // Name of the user's page
        try {

            while(true) {

                System.out.print("Database update #");                                    // Printing number of updates
                System.out.println(hours);
                hours++;

                List<String> userNames = Files.readAllLines(Paths.get("/Users/anoyanov/Work/SubscriberMonitor-Git/Subscribers_Checker/src/com/company/names.txt")); // To load data about list of users
                for (String userName : userNames) {
                    try {
                        //PrintWriter writer = new PrintWriter("the-file-name.txt", "UTF-8");
                        // Creating text file for user:
                        PrintWriter out = new PrintWriter("/Users/anoyanov/Work/SubscriberMonitor-Git/Subscribers_Checker/src/com/company/" + userName + ".txt");             // To load page to the text file
                        final String USER_AGENT = "Mozilla/5.0";
                        String serverPath = "https://www.instagram.com/" + userName + "/?__a=1";
                        // Trying other method:
                        //String serverPath = "https://www.instagram.com/web/search/topsearch/?query={" + userName + "}";          // Server path to get JSON4
                        // Can't connect directly to the page:
                        // String serverPath = "https://www.instagram.com/"+userName+"/";          // Server path
                        URL pageUrl = new URL(serverPath);                                         // URL
                        HttpURLConnection yc = (HttpURLConnection) pageUrl.openConnection();
                        yc.setRequestMethod("GET");                                                // Setting method
                        yc.setRequestProperty("User-Agent", USER_AGENT);
                        int responseCode = yc.getResponseCode();
                        if (responseCode == HttpURLConnection.HTTP_OK) {                           // Sucess
                            BufferedReader in = new BufferedReader(new InputStreamReader(
                                    yc.getInputStream()));
                            String inputLine;
                            StringBuffer response = new StringBuffer();

                            while ((inputLine = in.readLine()) != null) {
                                response.append(inputLine);
                                System.out.println(inputLine);                                      // Printing HTML line to the terminal
                                out.println(inputLine);                                             // Saving to the text file
                            }
                            out.close();
                            in.close();

                        } else {
                            System.out.println("GET request not worked");                       // Printing errors in case of failure
                        }
                        replyText = "Page successfully downloaded!";
                    } catch (Exception e) {
                        replyText = "error:" + e.getLocalizedMessage();                             // Catching exceptions
                    }

                    // Now JSON with all user parameters saved to the text file, getting number of subscribers from this .txt file
                    FileSearcher myFSearcher = new FileSearcher();
                    int subsNumber = myFSearcher.subscribersNumber(userName);
                    System.out.println(myFSearcher.subscribersNumber(userName));

                    // Getting date for database request:
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    String currentDate = now.toString();


                    // Writing all data to the database with DatabaseWriter:
                    DatabaseWriter myWriter = new DatabaseWriter();
                    myWriter.writeDataSimple(userName, currentDate, subsNumber);

                }
                Thread.sleep(60*60 * 1000);                                      // Sleeping for one hour
            }
        } catch (Exception e) {
            replyText = "error:" + e.getLocalizedMessage();                             // Catching exceptions
        }
    }
}
