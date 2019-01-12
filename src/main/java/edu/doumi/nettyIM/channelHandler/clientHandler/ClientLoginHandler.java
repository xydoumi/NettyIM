package edu.doumi.nettyIM.channelHandler.clientHandler;

import edu.doumi.nettyBase.common.ChatType;
import edu.doumi.nettyBase.common.directive.MessageDirective;
import edu.doumi.nettyBase.utils.SpringUtil;
import edu.doumi.nettyIM.model.ServiceResponseEntity;
import edu.doumi.nettyIM.model.SessionEntity;
import edu.doumi.nettyIM.service.ServiceResponse;
import edu.doumi.nettyIM.service.clientService.ClientBoundary;
import edu.doumi.nettyIM.service.clientService.clientInterface.ClientLoginService;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.stereotype.Controller;

@ChannelHandler.Sharable
@Controller
public class ClientLoginHandler extends SimpleChannelInboundHandler<MessageDirective> {

    private ClientLoginService clientLoginService;
    private ClientBoundary boundary;

    {
        clientLoginService = (ClientLoginService)SpringUtil.getBean("clientLoginServiceImpl");
    }
    public ClientLoginHandler(ClientBoundary boundary){
        super();
        this.boundary = boundary;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageDirective messageDirective) throws Exception {
        SessionEntity session = messageDirective.getSessionEntity();
        if(!session.getChatType().equals(ChatType.LOGIN_NOTIFY)) {
            super.channelRead(channelHandlerContext, messageDirective);
            return;
        }
        ServiceResponseEntity rsp = clientLoginService.loginResponse(messageDirective);
        if(rsp == null) {
            super.channelRead(channelHandlerContext, messageDirective);
            return;
        }
        if(rsp.getStatus().equals(ServiceResponse.SERVICE_SUCCESS)) {
            boundary.consolePrint("---------" + rsp.getContext() + "-----------");
        }else {
            boundary.consolePrint("---------" + rsp.getContext() + "-----------");
        }
    }
}
