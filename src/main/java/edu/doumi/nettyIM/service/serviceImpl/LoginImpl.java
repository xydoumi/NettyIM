package edu.doumi.nettyIM.service.serviceImpl;

import edu.doumi.nettyBase.MessageCahceQueue;
import edu.doumi.nettyBase.common.CommonInfo;
import edu.doumi.nettyBase.common.directive.MessageDirective;
import edu.doumi.nettyIM.model.UserInfo;
import edu.doumi.nettyIM.service.ServiceResponse;
import edu.doumi.nettyIM.service.serviceInterface.Login;
import edu.doumi.nettyIM.service.serviceInterface.MessageService;
import edu.doumi.nettyIM.service.serviceInterface.UserService;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginImpl implements Login {
    @Autowired
    private UserService userService;
    @Autowired
    private MessageService messageService;

    @Override
    public boolean login(String username, String password, ChannelHandlerContext ctx) {
        if(isValid(username)){
            if(checkPassword(username, password)){
                CommonInfo.channelToUserMap.put(ctx.channel().id().toString(), username);
                CommonInfo.channelHandlerMap.put(username, ctx.channel());
                // 查看缓存消息有无积压
                MessageDirective sendMessage = MessageCahceQueue.pollMessage(username);
                while(sendMessage != null) {
                    messageService.ServiceToClient(username, sendMessage);
                    sendMessage = MessageCahceQueue.pollMessage(username);
                }
                // 通知登陆成功
                messageService.ServiceToClient(username, messageService.createServiceResponse(ServiceResponse.LOGIN_SUCCESS));
                return true;
            }
        }
        messageService.ServiceToClient(username, messageService.createServiceResponse(ServiceResponse.LOGIN_FAIL));
        return false;
    }

    @Override
    public void logout(String channelId, String username) {
        if(userService.validUser(username)){
            CommonInfo.channelHandlerMap.remove(username);
            CommonInfo.channelToUserMap.remove(channelId);
        }
    }

    @Override
    public boolean hasLogin(String username) {
        return CommonInfo.channelHandlerMap.containsKey(username);
    }

    @Override
    public boolean isValid(String username) {
        return userService.validUser(username);
    }

    private boolean checkPassword(String username, String password){
        UserInfo userInfo = userService.detailUser(username);
        if(userInfo == null) {
            return false;
        }
        return password.equals(userInfo.getPassword());
    }
}
