package edu.doumi.nettyBase.exception;

public class ServiceException extends RuntimeException {
    public static final String NO_THIS_SERVICE = "此业务正在赶来。。。";
    public static final String NO_CURRENCT_COMMAND = "此命令无效";

    public ServiceException(){
        super();
    }

    public ServiceException(String exceptionMessage){
        super(exceptionMessage);
    }
}
