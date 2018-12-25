package edu.doumi.nettyIM.model;

import lombok.Data;

@Data
public class UserInfo {
    private String UID;
    private String username;
    private String password;
    private String nickname;
    public UserInfo(String UID, String username, String password, String nickname){
        this.UID = UID;
        this.username = username;
        this.nickname = nickname;
        this.password = password;
    }
}
