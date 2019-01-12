package edu.doumi.nettyIM.service.clientService.clientCommand;

public class ClientCommandContext {
    public static void exec(ClientCommandStrategy command){
        command.exec();
    }
}
