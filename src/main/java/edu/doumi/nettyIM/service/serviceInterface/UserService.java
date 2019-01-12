package edu.doumi.nettyIM.service.serviceInterface;

import edu.doumi.nettyIM.model.UserInfo;
import io.netty.channel.Channel;

import java.util.List;

public interface UserService {
    // 在线用户
    List<UserInfo> onlineUsers();

    // 在线用户数量
    int onlineUsersCount();

    // 注册用户
    boolean register(UserInfo userInfo);

    // 是否有效用户
    boolean validUser(String username);

    // 用户详细信息
    UserInfo detailUser(String username);

    // 当前用户用户名
    String currentUsername(Channel channel);

}
