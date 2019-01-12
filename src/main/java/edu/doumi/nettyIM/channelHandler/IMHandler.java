package edu.doumi.nettyIM.channelHandler;

import edu.doumi.nettyBase.HandlerRegister;
import edu.doumi.nettyBase.ServiceSpringHandlerRegister;
import edu.doumi.nettyBase.common.directive.IMDirective;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 服务器无状态handler配置中心
 */
@ChannelHandler.Sharable
public class IMHandler extends SimpleChannelInboundHandler<IMDirective> {
    private HandlerRegister handlerRegisterDefault;

    private IMHandler(HandlerRegister handlerRegister) {
        initRegister(handlerRegister);
        handlerRegisterDefault.registerHandler();
    }

    public static class Builder {
        private static IMHandler SERVICE_INSTANCE;
        private static IMHandler CLIENT_INSTANCE;

        public static IMHandler build(HandlerRegister handlerRegister) {
            if(handlerRegister == null || handlerRegister instanceof ServiceSpringHandlerRegister) {
                return initIMHandler(SERVICE_INSTANCE, handlerRegister);
            } else {
                return initIMHandler(CLIENT_INSTANCE, handlerRegister);
            }
        }

        private static IMHandler initIMHandler(IMHandler handler, HandlerRegister handlerRegister){
            if(handler == null){
                synchronized (IMHandler.class){
                    if(handler == null) {
                        handler = new IMHandler(handlerRegister);
                    }
                }
            }
            return handler;
        }
    }

    /**
     * 初始化handler注册器
     */
    private void initRegister(HandlerRegister handlerRegister) {
        if (handlerRegister == null) {
            this.handlerRegisterDefault = new ServiceSpringHandlerRegister();
            return;
        }
        this.handlerRegisterDefault = handlerRegister;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, IMDirective directive) throws Exception {
        SimpleChannelInboundHandler serviceHandler = handlerRegisterDefault.switchHandler(directive.getDirectiveType());
        serviceHandler.channelRead(channelHandlerContext, directive);
    }
}
