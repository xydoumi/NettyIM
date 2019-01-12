package edu.doumi.nettyIM.service.clientService.clientInterface;

import edu.doumi.nettyIM.model.SessionEntity;

import java.util.List;

public interface SessionService {
    // 系统sessionId
    String SYSTEM_SESSION_ID = "910209";
    // 系统nickname
    String SYSTEM_SESSION_NICK = "doumi";
    // 开始会话
    SessionEntity startSession(List<String> username);
    // 退出会话
    boolean exitSession(String username);
    // 保存会话
    void saveSession(SessionEntity session);
}
