package edu.doumi.nettyIM.model;

import lombok.Data;

import java.util.Date;

@Data
public class MessageEntity {
    private String sessionID;
    private Date sendTime;
    private String context;
}
