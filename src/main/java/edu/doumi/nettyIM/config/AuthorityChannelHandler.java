package edu.doumi.nettyIM.config;

import edu.doumi.nettyBase.common.directive.MessageDirective;
import edu.doumi.nettyIM.service.ServiceResponse;
import edu.doumi.nettyIM.service.serviceInterface.Login;
import edu.doumi.nettyIM.service.serviceInterface.MessageService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 *  判定权限
 */
@Configuration
public class AuthorityChannelHandler extends SimpleChannelInboundHandler {

    @Autowired
    private Login login;

    @Autowired
    private MessageService messageService;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object o) throws Exception {
        String channelId = ctx.channel().id().toString();
        if(login.hasLogin(channelId)){
            super.channelRead(ctx, o);
        }else{
            MessageDirective message = messageService.createServiceResponse(ServiceResponse.NOT_AUTHORITY);
            ctx.writeAndFlush(message);
        }
    }
}
