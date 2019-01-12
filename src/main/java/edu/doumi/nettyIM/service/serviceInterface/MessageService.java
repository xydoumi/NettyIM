package edu.doumi.nettyIM.service.serviceInterface;

import edu.doumi.nettyBase.common.directive.MessageDirective;
import edu.doumi.nettyIM.service.ServiceResponse;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

/**
 * 消息推送
 */
public interface MessageService {
    // 客户端向服务端发消息
    boolean ClientToService(ChannelHandlerContext cxt, MessageDirective messageDirective);
    // 服务端向客户端发消息
    boolean ServiceToClient(ChannelHandlerContext sendCxt, MessageDirective messageDirective);
    // 服务端向客户端发消息
    boolean ServiceToClient(Channel sendChannel, MessageDirective messageDirective);
    // 服务端向客户端发消息
    boolean ServiceToClient(String username, MessageDirective messageDirective);

    MessageDirective createServiceResponse(ServiceResponse serviceResponse);

}
