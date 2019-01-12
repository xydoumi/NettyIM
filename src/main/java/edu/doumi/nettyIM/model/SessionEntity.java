package edu.doumi.nettyIM.model;

import edu.doumi.nettyBase.common.ChatType;
import lombok.Data;

@Data
public class SessionEntity {
    private String sessionID;
    private ChatType chatType;
    private String serviceState;
    private String[] userList;
}
