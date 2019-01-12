package edu.doumi.nettyBase.exception;

public class SessionException extends RuntimeException{

    public SessionException(){
        super();
    }

    public SessionException(String exceptionMessage){
        super(exceptionMessage);
    }
}
