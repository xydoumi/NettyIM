package edu.doumi.nettyIM.service.clientService.clientCommand;

import edu.doumi.clientCache.SessionLocalCache;
import edu.doumi.nettyBase.utils.SpringUtil;
import edu.doumi.nettyIM.model.SessionEntity;
import edu.doumi.nettyIM.service.clientService.ClientBoundary;
import edu.doumi.nettyIM.service.clientService.clientInterface.ClientLoginService;
import io.netty.channel.Channel;

import java.text.MessageFormat;
import java.util.Map;
import java.util.Scanner;

public class LoginClientCommandStrategy extends AbstraceClientCommandStrategy {

    private ClientLoginService clientLoginService;

    {
        clientLoginService = (ClientLoginService)SpringUtil.getBean("clientLoginServiceImpl");
    }

    public LoginClientCommandStrategy(ClientBoundary clientBoundary, Channel channel) {
        this.clientBoundary = clientBoundary;
        this.channel = channel;
    }

    @Override
    protected void exec(ClientBoundary clientBoundary, Channel channel) {
        if(clientBoundary.hasLogin()) {
            Map<String, SessionEntity> sessionList = SessionLocalCache.getSessionList();
            if(sessionList.size()>0){
                clientBoundary.consolePrint(clientBoundary.getUsername() +"已登陆，选择已存在会话愉快的聊天吧");
                for(SessionEntity session:sessionList.values()){
                    String[] userList = session.getUserList();
                    int count = userList.length;
                    MessageFormat format = new MessageFormat("sessionid:{1} {2}[{3}]");
                    String out = format.format(session.getSessionID(), String.valueOf(count), userList.toString());
                    clientBoundary.consolePrint(out);
                }
            }else {
                clientBoundary.consolePrint(clientBoundary.getUsername() +"没一个能聊的，再来一个[:new your firend name]");
            }
            // 未登陆，进行登陆
        } else {

            clientBoundary.consolePrint("请输入用户名、密码用逗号隔开 eg-> username,password");
            Scanner s = new Scanner(System.in);
            while (s.hasNext()) {
                String input = s.nextLine();
                if(!validInput(input)) {
                    clientBoundary.consolePrint("输入格式错误 eg-> username,password");
                    continue;
                }
                String[] user = input.split(",");
                clientLoginService.loginRequest(channel,user[0], user[1]);
                break;
            }
        }
    }

    private boolean validInput(String input) {
        if(input == null || "".equals(input)){
            return false;
        }
        String[] user = input.split(",");
        if(user.length != 2) {
            return false;
        }
        return true;
    }
}
