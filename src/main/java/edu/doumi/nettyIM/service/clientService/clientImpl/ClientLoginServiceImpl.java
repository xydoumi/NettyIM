package edu.doumi.nettyIM.service.clientService.clientImpl;

import edu.doumi.nettyBase.common.ChatType;
import edu.doumi.nettyBase.common.directive.IMDirective;
import edu.doumi.nettyBase.common.directive.LoginDirective;
import edu.doumi.nettyBase.common.directive.MessageDirective;
import edu.doumi.nettyBase.exception.ServiceException;
import edu.doumi.nettyIM.model.MessageEntity;
import edu.doumi.nettyIM.model.ServiceResponseEntity;
import edu.doumi.nettyIM.model.SessionEntity;
import edu.doumi.nettyIM.service.clientService.clientInterface.ClientLoginService;
import io.netty.channel.Channel;
import org.springframework.stereotype.Service;

@Service
public class ClientLoginServiceImpl implements ClientLoginService {
    @Override
    public void loginRequest(Channel channel, String username, String password) {
        // 构建登陆信息
        IMDirective loginDirective = new LoginDirective(username, password);
        // 发送
        channel.isActive();
        channel.writeAndFlush(loginDirective).addListener((future) -> {
            if (future.isSuccess()) {
                System.out.println("正在连接……");
            } else {
                System.out.println("网络不给力呀……");
            }
        });
    }

    @Override
    public ServiceResponseEntity loginResponse(MessageDirective messageDirective) {
        ServiceResponseEntity rsp = new ServiceResponseEntity();
        SessionEntity session = messageDirective.getSessionEntity();
        MessageEntity message = messageDirective.getMessageEntity();
        ChatType chatType = session.getChatType();
        if (!chatType.equals(ChatType.LOGIN_NOTIFY)) {
            throw new ServiceException(ServiceException.NO_CURRENCT_COMMAND);
        }
        rsp.setStatus(session.getServiceState());
        rsp.setContext(message.getContext());
        return rsp;
    }
}
