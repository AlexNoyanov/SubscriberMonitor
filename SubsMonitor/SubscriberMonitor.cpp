//
//  SubscriberMonitor.cpp
//
//  
//
//  Created by Alex Noyanov on 05.08.19.
//

#include "SubscriberMonitor.hpp"
#include <stdio.h>
#include <vector>
#include <string>

void BeginLCD2004I2C();
void OLEDBegin();

void Monitor::addUser(string userName){
    Names.pushBack(userName);           // Adding userName to the vector
}

void Monitor::removeUser(string userName){
    // Removing this user from the list
    vector<string> newNames;
    for(auto str:Names){
        if(str == userName){
            // Deleting this name
        }else{
            newNames.pushBack(str);     // Copying this name
        }
        Names.clear();
        Names = newNames;
        newNames.clear();
    }
}


void Monitor::begin(string userName)
{
    addUser(userName);                  // Adding user to the list
    
    // Now setting up the the following screen in the monitor
    if(screenName == "LCD2004I2C"){
        BeginLCD2004I2C();
    }
    
    if(screenName == "OLED1302I2C"){
        OLEDBegin();
    }
        
    
}

int Monitor::GetSubscribersInstagram(string userName){  // Updating statistics of subscribers on Instagram for everybody
    int k = 0;
    for(auto s:Names){
        if(s == userName){
            break;
        }else{
            k++;
        }
    }
    
    return subscribersInstagram[k];
}


BeginLCD2004I2C()
{
    // Setting up the LCD screen
    
}

OLEDBegin()
{
   // Setting up the OLED screen
    
}
