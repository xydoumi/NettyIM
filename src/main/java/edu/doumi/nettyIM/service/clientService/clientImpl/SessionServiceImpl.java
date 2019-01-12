package edu.doumi.nettyIM.service.clientService.clientImpl;

import edu.doumi.clientCache.SessionLocalCache;
import edu.doumi.nettyBase.common.ChatType;
import edu.doumi.nettyBase.common.CommonInfo;
import edu.doumi.nettyBase.exception.SessionException;
import edu.doumi.nettyBase.exception.SessionExceptionMessage;
import edu.doumi.nettyIM.model.SessionEntity;
import edu.doumi.nettyIM.model.UserInfo;
import edu.doumi.nettyIM.service.clientService.clientInterface.SessionService;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

/**
 * 客户端会话存储
 */
@Service
public class SessionServiceImpl implements SessionService {
    // 客户端开启一个会话
    @Override
    public SessionEntity startSession(List<String> usernameList) {
        if(usernameList == null || usernameList.size() < 2)  {
            throw new SessionException(SessionExceptionMessage.NO_ENOUGH_USER.getMesssage());
        }

        SessionEntity sessionEntity = new SessionEntity();
        sessionEntity.setSessionID(createSessionId(usernameList));
        String[] userList = (String[])usernameList.stream().sorted().toArray(String[]::new);
        sessionEntity.setUserList(userList);
        if(userList.length == 2) {
            sessionEntity.setChatType(ChatType.PRIVATE_CHAT);
        } else {
            sessionEntity.setChatType(ChatType.GROUP_CHAT);
        }
        return sessionEntity;
    }


    @Override
    public boolean exitSession(String username) {
        return SessionLocalCache.remove(username);
    }

    @Override
    public void saveSession(SessionEntity session) {
        SessionLocalCache.putSession(session);
    }

    private boolean existSession(String username) {
        return false;
    }

    private String createSessionId(List<String> usernameList) {
        if (usernameList == null) {
            throw new SessionException(SessionExceptionMessage.NO_ENOUGH_USER.getMesssage());
        }
        if (usernameList.size() < 2) {
            throw new SessionException(SessionExceptionMessage.NO_ENOUGH_USER.getMesssage());
        }
        int var = 0;
        for (String username : usernameList) {
            if (CommonInfo.userInfoLists.containsKey(username)) {
                UserInfo userInfo = CommonInfo.userInfoLists.get(username);
                var = var | userInfo.getUID().hashCode();
            }
        }

        String sessionId = String.valueOf(var);
        if (usernameList.size() == 2) {
            return sessionId;
        } else {
            sessionId = sessionId + Calendar.getInstance().getTimeInMillis();
        }
        return sessionId;
    }
}
