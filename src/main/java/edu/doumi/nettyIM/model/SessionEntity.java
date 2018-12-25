package edu.doumi.nettyIM.model;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class SessionEntity {
    private String sessionID;
    private String[] UID;
    private List<MessageEntity> msgDelayQueue;

    public void putMsgDelayQueue(MessageEntity msg){
        if(msgDelayQueue == null)
            msgDelayQueue = Arrays.asList(msg);
        else
            msgDelayQueue.add(msg);
    }
}
