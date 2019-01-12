package edu.doumi.nettyBase;

import edu.doumi.nettyBase.common.directive.DirectiveType;
import edu.doumi.nettyBase.exception.ServiceException;
import edu.doumi.nettyBase.utils.SpringUtil;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;

abstract class AbstractSpringHandlerRegister implements HandlerRegister {
    protected Map<DirectiveType, SimpleChannelInboundHandler> handlerStore = new HashMap<>();

    @Override
    public void registerHandler() {
        ApplicationContext cxt = SpringUtil.getApplicationContext();
        Map<String, SimpleChannelInboundHandler> handlerMap = cxt.getBeansOfType(SimpleChannelInboundHandler.class);
        handlerRuler(cxt, handlerMap);
    }

    @Override
    public SimpleChannelInboundHandler switchHandler(DirectiveType directive) {
        if(!handlerStore.containsKey(directive)) {
            MyLog.info(ServiceException.NO_THIS_SERVICE);
            throw new ServiceException(ServiceException.NO_THIS_SERVICE);
        }
        return handlerStore.get(directive);
    }

    abstract protected void handlerRuler(ApplicationContext cxt, Map<String, SimpleChannelInboundHandler> handlerMap);
}
