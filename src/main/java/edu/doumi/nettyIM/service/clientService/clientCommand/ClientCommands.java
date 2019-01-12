package edu.doumi.nettyIM.service.clientService.clientCommand;

public enum ClientCommands {
    LOGIN("login");

    private String command;
    ClientCommands(String command) {
        this.command = command;
    }

    public String getCommand(){
        return command;
    }

    public static ClientCommands getCommandType(String command){
        for(ClientCommands cmd: values()){
            if(cmd.getCommand().equals(command)) {
                return cmd;
            }
        }
        return null;
    }
}
