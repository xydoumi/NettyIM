import edu.doumi.NettyBase.MyLog;
import edu.doumi.nettyIM.service.serviceInterface.Login;
import io.netty.channel.ChannelHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;

public class Test {
    @org.junit.Test
    public void test(){
       /* ApplicationContext ctx = new ClassPathXmlApplicationContext("application.xml");
        Map<String,ChannelHandler> handlerMap = ctx.getBeansOfType(ChannelHandler.class);
        Object result = handlerMap;*/


    }

    public static void main(String[] args){
        ApplicationContext ap1 = new ClassPathXmlApplicationContext("application.xml");
        ApplicationContext ap2 = new ClassPathXmlApplicationContext("application.xml");

        ap2.
        Login login1 = (Login) ap1.getBean("Login");
        Login login2 = (Login) ap1.getBean("Login");
        System.out.println(login1 = login2);

    }
}
