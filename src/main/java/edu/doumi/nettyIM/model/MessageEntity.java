package edu.doumi.nettyIM.model;

import lombok.Data;

@Data
public class MessageEntity {
    private String messageId;
    private String sessionId;
    private String username;
    private String sendTime;
    private String context;
}
