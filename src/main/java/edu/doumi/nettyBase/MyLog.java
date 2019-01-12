package edu.doumi.nettyBase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyLog {
    private static String date;
    private static String path = "";
    public static void info(String msg){
        updateDate();
        System.out.println(date+"----:"+msg);
    }
    private static void updateDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date = sdf.format(new Date(System.currentTimeMillis()));
    }
}
