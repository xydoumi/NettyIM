package edu.doumi.nettyIM.service;

public enum ServiceResponse {
    HAS_LOGIN(1, ServiceResponse.SERVICE_SUCCESS, "你已经在岸上了"),
    LOGIN_SUCCESS(1, ServiceResponse.SERVICE_SUCCESS,"登陆成功"),
    LOGIN_FAIL(1,ServiceResponse.SERVICE_FAIL,"用户名密码错误请重新登陆"),
    NOT_AUTHORITY(1, ServiceResponse.SERVICE_FAIL,"尚未登陆，请登陆后继续该操作"),

    MSG_SEND_SUCCESS(2, ServiceResponse.SERVICE_SUCCESS,"消息发送成功"),
    MSG_SEND_FAIL(2, ServiceResponse.SERVICE_FAIL,"发送失败");

    public static final String SERVICE_SUCCESS = "success";
    public static final String SERVICE_FAIL = "fail";

    private int code;
    private String status;
    private String info;

    ServiceResponse(int code, String status, String info) {
        this.code = code;
        this.status = status;
        this.info = info;
    }

    public int getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }

    public String getStatus() { return status; }
}
