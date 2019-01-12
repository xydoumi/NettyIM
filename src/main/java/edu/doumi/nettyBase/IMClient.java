package edu.doumi.nettyBase;

import edu.doumi.nettyIM.service.clientService.ClientBoundary;

public class IMClient {
    private static final int MAX_CONNECT =10;
    private static String host = "127.0.0.1";
    private static int port = 8000;
    private static final ClientBoundary clientBoundary = new ClientBoundary();
    public static void main(String[] args){
        NettyBuilder.INSTANCE.nettyClientBuilder(port, clientBoundary);
    }



}
