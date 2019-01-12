package edu.doumi.nettyIM.service.serviceImpl;

import edu.doumi.nettyBase.common.CommonInfo;
import edu.doumi.nettyIM.model.UserInfo;
import edu.doumi.nettyIM.service.serviceInterface.UserService;
import io.netty.channel.Channel;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    @Override
    public List<UserInfo> onlineUsers() {
        List<UserInfo> onlineUsers = Arrays.asList();
        if(onlineUsersCount() == 0) {
            return onlineUsers;
        }

        for(String username: CommonInfo.channelHandlerMap.keySet()) {
            onlineUsers.add(detailUser(username));
        }
        return onlineUsers;
    }

    @Override
    public int onlineUsersCount() {
        return CommonInfo.channelToUserMap.keySet().size();
    }

    @Override
    public boolean register(UserInfo userInfo) {
        return false;
    }

    @Override
    public boolean validUser(String username) {
        return CommonInfo.userInfoLists.containsKey(username);
    }

    @Override
    public UserInfo detailUser(String username) {
        if(CommonInfo.userInfoLists.containsKey(username)){
            for(String user: CommonInfo.userInfoLists.keySet()) {
                if(user.equals(username)) {
                    return CommonInfo.userInfoLists.get(user);
                }
            }
        }
        return null;
    }

    @Override
    public String currentUsername(Channel channel) {
        String channelId = channel.id().toString();
        return CommonInfo.channelToUserMap.get(channelId);
    }
}
