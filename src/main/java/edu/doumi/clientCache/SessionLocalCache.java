package edu.doumi.clientCache;

import edu.doumi.nettyIM.model.SessionEntity;

import java.util.HashMap;
import java.util.Map;

public class SessionLocalCache {
    private static final Map<String, SessionEntity> sessionCache = new HashMap<>();

    public static void putSession(SessionEntity session) {
        if (sessionCache.containsKey(session.getSessionID())) {
            return;
        }
        sessionCache.put(session.getSessionID(), session);
    }

    public static SessionEntity getSession(String sessionId) {
        return sessionCache.get(sessionId);
    }

    public static boolean remove(String sessionId) {
        return sessionCache.remove(sessionId) == null;
    }

    public static Map getSessionList(){
        return sessionCache;
    }
}
