package edu.doumi.NettyBase;

import io.netty.channel.Channel;

/**
 *  将ChannelHandler注册到pipeline
 */
public interface HandlerRegister {
    public void registerHandler(Channel channer);
}
