package edu.doumi.nettyIM.service.clientService.clientInterface;


import edu.doumi.nettyBase.common.directive.MessageDirective;
import edu.doumi.nettyIM.model.ServiceResponseEntity;
import io.netty.channel.Channel;

public interface ClientLoginService {
    void loginRequest(Channel channel, String username, String password);
    ServiceResponseEntity loginResponse(MessageDirective messageDirective);
}
