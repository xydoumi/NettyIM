package edu.doumi.NettyBase.common;

import edu.doumi.nettyIM.model.SessionEntity;
import edu.doumi.nettyIM.model.UserInfo;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class CommonInfo {
     public static final String CHARSET = "UTF-8";
     public static final Map<String, UserInfo> userInfoLists = new HashMap<String, UserInfo>();

     public static Map<String, Channel> channelHandlerMap = new ConcurrentHashMap<String, Channel>();

    public static Map<String, SessionEntity> sessionMap = new ConcurrentHashMap<String, SessionEntity>();
    {
        userInfoLists.put("162341297", new UserInfo(
                UUID.randomUUID().toString(), "162341297", "123456", "魏无羡"
        ));
        userInfoLists.put("162341299", new UserInfo(
                UUID.randomUUID().toString(), "162341299", "123456", "蓝忘机"
        ));
    }
}
