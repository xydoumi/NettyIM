package edu.doumi.nettyBase;

import edu.doumi.nettyBase.common.directive.DirectiveType;
import edu.doumi.nettyIM.channelHandler.serviceHandler.LoginHandler;
import edu.doumi.nettyIM.channelHandler.serviceHandler.SessionHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.context.ApplicationContext;

import java.util.Map;

public class ServiceSpringHandlerRegister extends AbstractSpringHandlerRegister{

    @Override
    public void handlerRuler(ApplicationContext cxt, Map<String, SimpleChannelInboundHandler> handlerMap){
        for(ChannelHandler handler: handlerMap.values()){
            if(handler instanceof LoginHandler){
                handlerStore.put(DirectiveType.LOGIN, cxt.getBean(LoginHandler.class));
            }else if(handler instanceof SessionHandler){
                handlerStore.put(DirectiveType.MESSAGE, cxt.getBean(SessionHandler.class));
            }
        }
    }


}
