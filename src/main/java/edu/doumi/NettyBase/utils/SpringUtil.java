package edu.doumi.NettyBase.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtil {
    private static ApplicationContext applicationContext;
    public static ApplicationContext getApplicationContext(){
        if(applicationContext == null){
            synchronized(SpringUtil.class){
                if(applicationContext == null)
                    return new ClassPathXmlApplicationContext("application.xml");
            }
        }
        return applicationContext;
    }
}
