package edu.doumi.nettyIM.channelHandler.serviceHandler;

import edu.doumi.nettyBase.common.directive.LoginDirective;
import edu.doumi.nettyIM.service.ServiceResponse;
import edu.doumi.nettyIM.service.serviceInterface.Login;
import edu.doumi.nettyIM.service.serviceInterface.MessageService;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@ChannelHandler.Sharable
@Controller
public  class LoginHandler extends SimpleChannelInboundHandler<LoginDirective>{

    @Autowired
    private Login login;
    @Autowired
    private MessageService messageService;

    @Override
    protected void channelRead0(ChannelHandlerContext cxt, LoginDirective loginDirective) throws Exception {
        String channleId = cxt.channel().id().toString();
        // 已经登陆
        if(login.hasLogin(channleId)) {
            cxt.writeAndFlush(messageService.createServiceResponse(ServiceResponse.HAS_LOGIN));
        }
        if(login.login(loginDirective.getUsername(), loginDirective.getPassword(), cxt)){
            cxt.writeAndFlush(messageService.createServiceResponse(ServiceResponse.LOGIN_SUCCESS));
        }else{
            cxt.writeAndFlush(messageService.createServiceResponse(ServiceResponse.LOGIN_FAIL));
        }
    }
}
