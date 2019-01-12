package edu.doumi.nettyBase.exception;

public enum SessionExceptionMessage {
    NO_ENOUGH_USER("会话成员不足2人，无法构成会话");

    private String messsage;

    SessionExceptionMessage(String messsage) {
        this.messsage = messsage;
    }

    public String getMesssage() {
        return this.messsage;
    }
}
