package edu.doumi.nettyBase;

import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.context.ApplicationContext;

import java.util.Map;

public class ClientSpringHandlerRegister extends AbstractSpringHandlerRegister {
    @Override
    protected void handlerRuler(ApplicationContext cxt, Map<String, SimpleChannelInboundHandler> handlerMap) {

    }
}
