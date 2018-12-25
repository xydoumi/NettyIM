/*package edu.doumi.nettyIM.channelHandler;

import edu.doumi.NettyBase.common.CommonInfo;
import edu.doumi.NettyBase.common.prototype.LoginDirective;
import edu.doumi.nettyIM.service.serviceInterface.Login;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.stereotype.Controller;

import java.nio.charset.Charset;

@Controller
public  class LoginHandler extends SimpleChannelInboundHandler<LoginDirective>{

    private Login login;
    @Override
    protected void channelRead0(ChannelHandlerContext cxt, LoginDirective loginDirective) throws Exception {
        if(login.login(loginDirective.getUsername(), loginDirective.getPassword(), cxt.channel())){
            cxt.channel().writeAndFlush();
        }
    }
}*/
