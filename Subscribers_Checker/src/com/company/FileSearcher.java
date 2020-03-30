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

    // To save file here instead of convering it every time:
    String fileString;

    // To convert File to string:
    private String ToString(String fileName){
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
        String _fileString = contentBuilder.toString();                           // File converting to string
        fileString = _fileString;
        return fileString;
    }

    // To find index of the beginning in the text file converted to string:
    private int toFindIndex(String fileName, String toFindstr){
        //String toFind = "{\"count\":";                                       // To find number of subscribers
        String fileString = ToString(fileName);                                 // Getting all file as String
        // Searching number of subscribers in the string:
        int intIndex = fileString.indexOf(toFindstr);
        if(intIndex == - 1) {
            System.out.println(toFindstr + " not found");
            return -1;                                                          // If not found -1
        } else {
            System.out.println("Found "+ toFindstr +" at index " + intIndex);  // Find index of the end of the parameter name
            return intIndex;                                                    // Return index of the beginning
        }
    }


    // To get value from the index in the string:
    private String GetFromSearch(String toIgnoreStr, int index, char toStop){
        String value = "";
        for(int i = index + toIgnoreStr.length(); fileString.charAt(i) != toStop; i++){
            //subscribersNumber.concat(String.valueOf(fileString.charAt(i)));
            value = value+ fileString.charAt(i);
        }
        return value;
    }

    // Searching for subscribers in the file:
    public int subscribersNumber(String fileName){

        String toFind = "{\"count\":";              // To find number of subscribers
        //String filePath = "/Users/anoyanov/Work/SubscriberMonitor-Git/Subscribers_Checker/src/com/company/" + fileName +".txt";

        String subscribersNumberString = "";                                                // String where result printing
        int intIndex = toFindIndex(fileName, toFind);                                       // Index of the beginning
        subscribersNumberString = GetFromSearch(toFind, intIndex,'}');                          // Ignoring toFind and getting value
        System.out.println("Number of Subscribers = " + subscribersNumberString);

        int numberOfsubscribers = Integer.parseInt(subscribersNumberString);

        return numberOfsubscribers;
    }

    // Search for a full name description:
    public String FullName(String fileName) {
        // Find in the file:"full_name":
        String toFind = "\"full_name\":";                      // To find number of subscribers
        String fullName = "";

        int intIndex = toFindIndex(fileName, toFind);        // Index of the beginning of the full name;
//        for (int i = intIndex + toFind.length(); fileString.charAt(i) != '}'; i++) {
////            //subscribersNumber.concat(String.valueOf(fileString.charAt(i)));
////            fullName = fullName + fileString.charAt(i);
////        }
        fullName = GetFromSearch(toFind, intIndex,',');

        System.out.println("Full Name = " + fullName);
        return fullName;
    }


}
