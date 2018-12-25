package edu.doumi.nettyIM.service.serviceInterface;

import io.netty.channel.Channel;
import org.springframework.stereotype.Service;

public interface Login {
    public boolean login(String username, String password, Channel channel);
    public void logout(String username);
    public boolean hasLogin(String username);
    public boolean isValid(String username);
}
