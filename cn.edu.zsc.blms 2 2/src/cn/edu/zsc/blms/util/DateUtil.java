package cn.edu.zsc.blms.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static final SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat timeFormat =new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


    public static Date parse(String source) throws ParseException {
        return dateFormat.parse(source);
    }

    public static String format(Date date){

        return dateFormat.format(date);
    }

    public static String formatTime(Date time){
        return timeFormat.format(time);
    }
}
