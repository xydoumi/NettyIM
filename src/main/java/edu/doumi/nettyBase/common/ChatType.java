package edu.doumi.nettyBase.common;

public enum ChatType {
    PRIVATE_CHAT(1, "私聊"),
    GROUP_CHAT(2, "群聊"),
    LOGIN_NOTIFY(3, "登陆通知"),
    SEND_NOTIFY(4, "发送通知"),
    SYSTEM_MESSAGE(0, "系统消息");
    private int key;
    private String value;

    ChatType(int key, String value){
        this.key = key;
        this.value = value;
    }


    public boolean equals(ChatType chatType){
        return this.key == chatType.key;
    }
}
