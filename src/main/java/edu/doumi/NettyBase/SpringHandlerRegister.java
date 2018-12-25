package edu.doumi.NettyBase;

import edu.doumi.NettyBase.utils.*;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import org.springframework.context.ApplicationContext;
import java.util.Map;

public class SpringHandlerRegister implements HandlerRegister{
    public void registerHandler(Channel channel){
        ApplicationContext cxt = SpringUtil.getApplicationContext();
        Map<String, ChannelHandler> handlerMap = cxt.getBeansOfType(ChannelHandler.class);

    }
}
