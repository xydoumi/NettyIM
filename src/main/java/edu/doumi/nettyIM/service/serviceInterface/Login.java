package edu.doumi.nettyIM.service.serviceInterface;

import io.netty.channel.ChannelHandlerContext;

public interface Login {
    public boolean login(String username, String password, ChannelHandlerContext ctx);
    public void logout(String channelId, String username);
    public boolean hasLogin(String channelId);
    public boolean isValid(String username);
}
