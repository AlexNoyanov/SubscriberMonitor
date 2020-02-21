//
//  SubscriberMonitor.hpp
//  
//
//  Created by Alex Noyanov on 05.08.19.
//

#ifndef SubscriberMonitor_hpp
#define SubscriberMonitor_hpp

#include <stdio.h>
#include <vector>
#include <string>
#include <map>

#endif /* SubscriberMonitor_hpp */

/*
 === Subscriber monitor project ===
 Version 1.5
 
 The idea here is to rewrite the project
 Subscriber Monitor to make it compatible with every Screen.
 To make it the Monitor class will be created.
 The realization of the method .ShowSubscribers(); will be created different
 for each of the screens.
 First version will created for I2C LCD 20x4 screen
 
 The Monitor then can become a universal monitor showing everything what you want
 
    === Version 1.5 ===
 Method GetSubscribersInstagram(string userName); saving number of subscribers for user userName to the vector
 The method printSubscribersInstagramLCD2004(); printing subscribers for the user userName on the I2C LCD 20x4
 
 */

// The main class itself:
class Monitor(){
private:
    vector<int> subscribersInsragram;  // Numbers of subscribers in different social medias (Instagram, Twitter, Facebook)
    vector<int> subscribersTwitter;
    vector<int> subscribersFacebook;
    string screenType;                 // Type of the monitor screen used in this monitor
    vector<string> Names;              // Names of the users you want to monitoring
    
    map<string,int> InstagramSubscribers;
    
public:
    Monitor(string screenName){
        screenType = screenName;
    }
    void begin(string userName);                                      // Seting up the monitor for the screen
    void addUser(string userName);                                    // Adding new user to the monitor
    void removeUser(string userName);
    int  GetSubscribersInstagram(string userName);                    // Get number of subscribers in the Instagram for user userName
    void updateSubscribersInstagram();                                // Updating statistics of subscribers on Instagram for everybody
    void printSubscribersInstagramLCD2004(string userName);           // Print Instagram subscribers on the screen
    void printSubscribers(string userName);
    
}
