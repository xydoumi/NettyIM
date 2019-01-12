package edu.doumi.nettyBase.common;

import edu.doumi.nettyIM.model.UserInfo;
import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class CommonInfo {
    //
    public static final String CHARSET = "UTF-8";
    // 用户注册信息
    public static final Map<String, UserInfo> userInfoLists = new HashMap<String, UserInfo>();

    // 用户channelId与username映射关系
    public static final Map<String, String> channelToUserMap = new ConcurrentHashMap();

    // username 对应channel
    public static Map<String, Channel> channelHandlerMap = new ConcurrentHashMap<String, Channel>();



    static {
        userInfoLists.put("162341297", new UserInfo(
                UUID.randomUUID().toString(), "162341297", "123456", "魏无羡"
        ));
        userInfoLists.put("162341299", new UserInfo(
                UUID.randomUUID().toString(), "162341299", "123456", "蓝忘机"
        ));
    }
}
