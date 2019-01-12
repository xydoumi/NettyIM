package edu.doumi.nettyBase;

import edu.doumi.nettyBase.common.directive.DirectiveType;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 *  无状态ChannelHandler注册中心
 */
public interface HandlerRegister {
    // 注册无状态ChannelHandler
    void registerHandler();
    // 根据指令选取对应Channel
    SimpleChannelInboundHandler switchHandler(DirectiveType directive);
}
