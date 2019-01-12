package edu.doumi.nettyIM.channelHandler.serviceHandler;

import edu.doumi.nettyBase.common.directive.MessageDirective;
import edu.doumi.nettyIM.model.MessageEntity;
import edu.doumi.nettyIM.model.SessionEntity;
import edu.doumi.nettyIM.service.serviceInterface.MessageService;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@ChannelHandler.Sharable
@Controller
public class SessionHandler extends SimpleChannelInboundHandler<MessageDirective> {
    @Autowired
    private MessageService messageService;

    @Override
    protected void channelRead0(ChannelHandlerContext cxt, MessageDirective messageDirective) {
        SessionEntity session = messageDirective.getSessionEntity();
        MessageEntity message = messageDirective.getMessageEntity();
        messageService.ServiceToClient(cxt, messageDirective);
    }
}
