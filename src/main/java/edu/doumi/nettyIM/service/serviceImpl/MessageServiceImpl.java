package edu.doumi.nettyIM.service.serviceImpl;

import edu.doumi.nettyBase.MessageCahceQueue;
import edu.doumi.nettyBase.MyLog;
import edu.doumi.nettyBase.common.ChatType;
import edu.doumi.nettyBase.common.CommonInfo;
import edu.doumi.nettyBase.common.directive.MessageDirective;
import edu.doumi.nettyBase.exception.SessionException;
import edu.doumi.nettyBase.exception.SessionExceptionMessage;
import edu.doumi.nettyBase.utils.StringUtil;
import edu.doumi.nettyIM.model.MessageEntity;
import edu.doumi.nettyIM.model.SessionEntity;
import edu.doumi.nettyIM.service.ServiceResponse;
import edu.doumi.nettyIM.service.clientService.clientInterface.SessionService;
import edu.doumi.nettyIM.service.serviceInterface.MessageService;
import edu.doumi.nettyIM.service.serviceInterface.UserService;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Stream;

@Component
public class MessageServiceImpl implements MessageService {
    @Autowired
    private UserService userService;

    @Override
    public boolean ClientToService(ChannelHandlerContext cxt, MessageDirective messageDirective) {
        return false;
    }

    @Override
    public boolean ServiceToClient(ChannelHandlerContext sendCxt, MessageDirective messageDirective) {
        Channel sendChannel = sendCxt.channel();
        return ServiceToClient(sendChannel, messageDirective);
    }

    @Override
    public boolean ServiceToClient(Channel sendChannel, MessageDirective messageDirective) {
        // 查看会话信息
        SessionEntity sessionEntity = messageDirective.getSessionEntity();
        String currentUser = userService.currentUsername(sendChannel);
        try {
            // 如果是单聊
            if (sessionEntity.getChatType().equals(ChatType.PRIVATE_CHAT)) {
                String toUser = Arrays.stream(sessionEntity.getUserList()).filter(user ->
                        !user.equals(currentUser)
                ).findAny().get();

                sendClientMessage(toUser, messageDirective);
                sendClientMessage(currentUser, createServiceResponse(ServiceResponse.MSG_SEND_FAIL));
                // 多聊
            } else {
                Stream toUsers = Arrays.stream(sessionEntity.getUserList()).filter((user) ->
                        !user.equals(currentUser));
                toUsers.forEach((toUser) -> {
                    sendClientMessage((String) toUser, messageDirective);
                });
                sendClientMessage(currentUser, createServiceResponse(ServiceResponse.MSG_SEND_FAIL));
            }
        } catch (Exception e) {
            // 通知发送失败的消息
            ServiceToClient(currentUser, createServiceResponse(ServiceResponse.MSG_SEND_FAIL));
            e = new SessionException(SessionExceptionMessage.NO_ENOUGH_USER.getMesssage());
            MyLog.info(e.getMessage());
        }
        return true;
    }

    @Override
    public boolean ServiceToClient(String username, MessageDirective messageDirective) {
        return sendClientMessage(username, messageDirective);
    }

    @Override
    public MessageDirective createServiceResponse(ServiceResponse serviceResponse) {
        MessageDirective messageDirective = new MessageDirective();

        SessionEntity session = new SessionEntity();
        session.setSessionID(SessionService.SYSTEM_SESSION_ID);
        // 服务器消息类型
        session.setChatType(getCurrentType(serviceResponse));
        // 服务结果
        session.setServiceState(serviceResponse.getStatus());

        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setContext(serviceResponse.getInfo());
        messageEntity.setSendTime(StringUtil.getCurrentDate());
        messageEntity.setSessionId(SessionService.SYSTEM_SESSION_ID);
        messageEntity.setUsername(SessionService.SYSTEM_SESSION_NICK);

        messageDirective.setSessionEntity(session);
        messageDirective.setMessageEntity(messageEntity);
        return messageDirective;
    }

    private ChatType getCurrentType(ServiceResponse serviceResponse){
        switch (serviceResponse.getCode()) {
            case 1:
                return ChatType.LOGIN_NOTIFY;
            case 2:
                return ChatType.SEND_NOTIFY;
            default:
                return ChatType.SYSTEM_MESSAGE;
        }
    }


    private boolean sendClientMessage(String toUser, MessageDirective messageDirective) {
        try {
            Channel sendChannel = CommonInfo.channelHandlerMap.get(toUser);
            // 不在线缓存进队列
            if (sendChannel == null) {
                MessageCahceQueue.insertMessage(toUser, messageDirective);
            }
            sendChannel.writeAndFlush(messageDirective).addListener((future) -> {
                if (!future.isSuccess()) {
                    throw new RuntimeException(toUser + "消息发送失败发送失败");
                }
            });
        } catch (Exception e) {
            MyLog.info(e.getMessage());
            return false;
        }
        return true;
    }

}
