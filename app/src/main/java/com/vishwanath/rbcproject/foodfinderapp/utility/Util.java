package com.vishwanath.rbcproject.foodfinderapp.utility;

import android.Manifest;
import android.content.Context;

import com.vishwanath.rbcproject.foodfinderapp.model.Open;

import java.util.Date;

import androidx.core.content.ContextCompat;

import static com.vishwanath.rbcproject.foodfinderapp.utility.Constants.PERMISSION_GRANTED;

public class Util {

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }


    public static boolean isLocationPermissionPresent(Context context) {
        return (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PERMISSION_GRANTED);
    }

    public static boolean isCallPermissionPresent(Context context) {
        return (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) == PERMISSION_GRANTED);
    }

    public static String getDay(int dayInInt) {
        String day = "";
        switch (dayInInt) {
            case 0:
                day = "Sunday";
                break;
            case 1:
                day = "Monday";
                break;
            case 2:
                day = "Tuesday";
                break;
            case 3:
                day = "Wednesday";
                break;
            case 4:
                day = "Thursday";
                break;
            case 5:
                day = "Friday";
                break;
            default:
                day = "Saturday";
        }
        return day;
    }

    public static  String getOpenHours(Open openHours) {
        String str = "";

        if (openHours.getDay()!= null && openHours.getEnd() != null){
            str = Util.getDay(openHours.getDay()) + openHours.getEnd();
        }

        Date date = new Date();
        int day = date.getDay();

        if (day == openHours.getDay()){
            str = "Today";
        }

        return String.format("Open till %s %s", getTimeAMPM(openHours.getEnd()), str);
    }

    // TODO if time permits
    public static String getTimeAMPM(String endTime){
        int timeInInt = Integer.parseInt(endTime);
        return endTime;
    }

}
