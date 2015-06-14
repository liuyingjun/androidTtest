package com.utils;

public class CommonFunction {
    public static String getVideoCurrPlayTime(String time) {
        String []timeArr = time.split("/");
        return timeArr[0].trim();
    }

    public static String getVideoTotalTime(String time) {
        String []timeArr = time.split("/");
        return timeArr[1].trim();
    }

    public static int getVideoCurrPlayTimeInSec(String time) {
        String str = getVideoCurrPlayTime(time);
        String []sec = str.split(":");
        switch(sec.length) {
            case 2:
                return Integer.parseInt(sec[0].trim())*60 +Integer.parseInt(sec[1].trim());
            case 3:
                return Integer.parseInt(sec[0].trim())*3600 + Integer.parseInt(sec[1].trim())*60 +Integer.parseInt(sec[2].trim());
        }
        return 0;
    }

    public static int getVideoTotalTimeInSec(String time) {
        String str = getVideoTotalTime(time);
        String []sec = str.split(":");
        switch(sec.length) {
            case 2:
                return Integer.parseInt(sec[0].trim())*60 +Integer.parseInt(sec[1].trim());
            case 3:
                return Integer.parseInt(sec[0].trim())*3600 + Integer.parseInt(sec[1].trim())*60 +Integer.parseInt(sec[2].trim());
        }
        return 0;
    }
}
