package edu.doumi.nettyBase;

import edu.doumi.nettyBase.common.directive.MessageDirective;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MessageCahceQueue {
    private static final Map<String, Queue<MessageDirective>> sendMessageQueue = new ConcurrentHashMap<>();

    public static boolean insertMessage(String username, MessageDirective messageDirective) {
        try {
            // 有积压消息
            if (hasNotSendMessage(username)) {
                Queue existList = sendMessageQueue.get(username);
                existList.offer(messageDirective);
            } else {
                Queue newList = new ConcurrentLinkedQueue();
                newList.offer(messageDirective);
                sendMessageQueue.put(username, newList);
            }
        } catch (Exception e) {
            MyLog.info(e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean hasNotSendMessage(String username) {
        boolean hasMessage = sendMessageQueue.containsKey(username);
        if(!hasMessage) {
            return false;
        }
        int size = sendMessageQueue.get(username).size();
        if(size == 0) {
            sendMessageQueue.remove(username);
            return false;
        }
        return true;
    }

    public static MessageDirective pollMessage(String username){
        if(hasNotSendMessage(username)) {
            return sendMessageQueue.get(username).poll();
        }else {
            return null;
        }
    }
}
