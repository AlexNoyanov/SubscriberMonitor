package com.company;


/**
        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
             This class is required to search for
             data in the .txt file with
             downloaded dump of Instagram page
             query

             By Alexander Noyanov
             March 28th 2020
         ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileSearcher {

    // Searching for subscribers in the file:
    public int subscribersNumber(String fileName){

        String toFind = "{\"count\":";              // To find number of subscribers
        String filePath = "/Users/anoyanov/Work/SubscriberMonitor-Git/Subscribers_Checker/src/com/company/" + fileName +".txt";

        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8))
        {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        String fileString = contentBuilder.toString();                      // File converting to string

        // Searching number of subscribers in the string:
        int intIndex = fileString.indexOf(toFind);
        if(intIndex == - 1) {
            System.out.println(toFind + " not found");
        } else {
            System.out.println("Found "+ toFind +" at index " + intIndex);  // Find index of the end of the parameter name
        }
        //trying to find number of subscribers index:
//        for(int i = intIndex + toFind.length(); i< intIndex+toFind.length() + 4; i++){
//            System.out.print("[");
//            System.out.print(i);
//            System.out.print("] = ");
//            System.out.println(fileString.charAt(i));
//        }

        String subscribersNumberString = "";                                // String where result printing

        for(int i = intIndex + toFind.length(); fileString.charAt(i) != '}'; i++){
            //subscribersNumber.concat(String.valueOf(fileString.charAt(i)));
            subscribersNumberString = subscribersNumberString + fileString.charAt(i);
        }
        System.out.println("Number of Subscribers = " + subscribersNumberString);

        int numberOfsubscribers = Integer.parseInt(subscribersNumberString);

        return numberOfsubscribers;
    }

}
