package edu.doumi.nettyBase.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class StringUtil {
    public static String DateToString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd hh:mm:ss");
        return sdf.format(date);
    }

    public static String getCurrentDate() {
        Date currentDate = Calendar.getInstance().getTime();
        return DateToString(currentDate);
    }
}
