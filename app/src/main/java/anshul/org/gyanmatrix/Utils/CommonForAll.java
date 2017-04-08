package anshul.org.gyanmatrix.Utils;

import java.util.Date;


public class CommonForAll {
    static long currentTime =System.currentTimeMillis();
    public static String getDate(String timeStampString) {

        if (timeStampString == null) {
            return "NA";
        }

        if (isInteger(timeStampString)) {

            long timeStamp = Long.parseLong(timeStampString) * 1000;
            try {

                return getDate(new Date(timeStamp));
            } catch (Exception ex) {
                return "xx";
            }
        } else {
            return "xx";
        }
    }

    public static String getDate(Date netDate) {


            long diff =currentTime- netDate.getTime()  ;
            int diffMinute = (int)(diff / ((double) 1000 * 60 ));
            if(diffMinute > 60){
                int diffHour = (int)(diffMinute / 60);
                if(diffHour >24){
                    int diffdays = (int)(diffMinute / 24);
                    return diffdays+" days back";
                }
                return diffHour+" hours back";
            }
            return  diffMinute+" minutes back";

    }

    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }


}
