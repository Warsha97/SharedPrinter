/** *********************************************************************
 * Author:    Warsha Kiringoda
 * UoW id:    w1697817
 * IIT id:    2017366
 ************************************************************************ */
package utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class MethodService {

    //Generates a random number for thread sleep function
public static long generateRandomSleepDuration(){
    Random rand = new Random();
    int randomNum = rand.nextInt((1000 - 1) + 1) + 1;
    return randomNum;
}

//Returns current time
public static String getTimeNow(){
    return new SimpleDateFormat("HH.mm.ss.SSS").format(new Date())+":";
}
}
