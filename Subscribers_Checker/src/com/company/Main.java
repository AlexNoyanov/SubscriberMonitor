package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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


public class Main {

    public static void main(String[] args) {

        String replyText = "";

        try{
    
            final String USER_AGENT = "Mozilla/5.0";
            String serverPath = "https://www.instagram.com/alex_noyanov/";
            URL pwrOff = new URL(serverPath);
            HttpURLConnection yc = (HttpURLConnection) pwrOff.openConnection();
            yc.setRequestMethod("GET");
            yc.setRequestProperty("User-Agent", USER_AGENT);
            int responseCode = yc.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        yc.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();


                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                    System.out.println(inputLine);
                }
                in.close();
                // print result
                //System.out.println(response.toString());
            } else {
                System.out.println("GET request not worked");
            }
            replyText = "command executed ok. Turned ON";
    } catch (Exception e) {
        replyText = "error:" + e.getLocalizedMessage();
    }

        //List<String>page = new List<String>;
       // System.out.println(replyText);


    }
}
