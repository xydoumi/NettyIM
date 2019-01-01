package edu.doumi.nettyIM.config;

import edu.doumi.nettyIM.service.serviceInterface.Login;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
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
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object o) throws Exception {
        String channelId = ctx.channel().id().toString();
        if(login.hasLogin(channelId)){

        }else{

        }
    }
}
