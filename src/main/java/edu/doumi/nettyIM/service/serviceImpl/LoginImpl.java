package edu.doumi.nettyIM.service.serviceImpl;

import edu.doumi.NettyBase.common.CommonInfo;
import edu.doumi.nettyIM.service.serviceInterface.Login;
import io.netty.channel.Channel;
import org.springframework.stereotype.Service;

@Service
public class LoginImpl implements Login {
    @Override
    public boolean login(String username, String password, Channel channel) {
        if(isValid(username)){
            if(password.equals(CommonInfo.userInfoLists.get(username).getPassword())){
                CommonInfo.channelHandlerMap.put(username, channel);
                return true;
            }
        }
        return false;
    }

    @Override
    public void logout(String username) {
        if(CommonInfo.userInfoLists.containsKey(username))
            CommonInfo.channelHandlerMap.remove(username);
    }

    @Override
    public boolean hasLogin(String channelId) {
        return CommonInfo.channelHandlerMap.containsKey(channelId);
    }

    @Override
    public boolean isValid(String username) {
        return CommonInfo.userInfoLists.containsKey(username);
    }
}
