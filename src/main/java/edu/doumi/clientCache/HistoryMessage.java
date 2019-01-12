package edu.doumi.clientCache;

import edu.doumi.nettyBase.MyLog;
import edu.doumi.nettyIM.model.MessageEntity;

import java.util.*;

public class HistoryMessage {
    private static final Map<String, List<MessageEntity>> historyMessageRepository = new HashMap<>();

    // 添加历史记录
    public static void addHistory(String sessionId, MessageEntity message){
        if(historyMessageRepository.containsKey(sessionId)) {
            List sessionHistory = historyMessageRepository.get(sessionId);
            if(sessionHistory == null) {
                sessionHistory = Arrays.asList();
            }
            sessionHistory.add(message);
        } else {
            List newSessionHistory = Arrays.asList(message);
            historyMessageRepository.put(sessionId, newSessionHistory);
        }
    }
    // 清空历史记录
    public static void clear(String sessionId){
        if(!historyMessageRepository.containsKey(sessionId)){
            return;
        }
        historyMessageRepository.remove(sessionId);
    }
    // 显示历史记录
    public static void showHistory(String sessionId, String self) {
        List<MessageEntity> historyList = historyMessageRepository.get(sessionId);
        for(MessageEntity message: historyList) {
            // TODO 区别自己与他人
            showStrategy(message);
        }
    }

    private static void showStrategy(MessageEntity message){
        MyLog.info(message.getSendTime() + " " + message.getUsername() + " \n" + message.getContext());
    }
}
