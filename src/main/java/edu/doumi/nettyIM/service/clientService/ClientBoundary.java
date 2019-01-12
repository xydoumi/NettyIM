package edu.doumi.nettyIM.service.clientService;

import edu.doumi.nettyIM.service.clientService.clientCommand.ClientCommandContext;
import edu.doumi.nettyIM.service.clientService.clientCommand.ClientCommandStrategy;
import edu.doumi.nettyIM.service.clientService.clientCommand.ClientCommands;
import edu.doumi.nettyIM.service.clientService.clientCommand.LoginClientCommandStrategy;
import io.netty.channel.Channel;
import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;

public class ClientBoundary {
    @Setter
    @Getter
    private volatile String username;
    private ClientCommandHandler clientCommandHandler = new ClientCommandHandler(this);

    public void open(Channel channel) {
        new Thread(() -> {
            while (!Thread.interrupted()) {
                Scanner s = new Scanner(System.in);
                try {
                    while (s.hasNext()) {
                        String command = s.nextLine();
                        if (command.trim().equals("")) {
                            continue;
                        }
                        if (!clientCommandHandler.vaildCommand(command)) {
                            clientCommandHandler.pointOutCommands();
                        } else {
                            Thread commandWorker = clientCommandHandler.exec(channel, command.substring(1));
                            commandWorker.start();
                            commandWorker.join();
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }).start();
    }

    public boolean hasLogin() {
        return username != null;
    }

    private class ClientCommandHandler {
        private ClientBoundary clientBoundary;

        ClientCommandHandler(ClientBoundary clientBoundary) {
            this.clientBoundary = clientBoundary;
        }

        boolean vaildCommand(String command) {
            return command.startsWith(":");
        }

        void pointOutCommands() {
            consolePrint("该命令无效，当前可用命令为\r\n" +
                    ":login\r\n" +
                    ":u\r\n" +
                    ":to [sessionId] eg-> :to 666666\r\n" +
                    ":new [username 以逗号分割] eg-> :new 162341\r\n");
        }

        Thread exec(final Channel channel, final String command) {
            ClientCommandStrategy commandStrategy = matchCommandStrategy(channel, command);
            Thread commandWorker = exec(commandStrategy);
            return commandWorker;
        }

        Thread exec(final ClientCommandStrategy clientCommandStrategy) {
            Thread commandWorker = new Thread(new Runnable() {
                @Override
                public void run() {
                    ClientCommandContext.exec(clientCommandStrategy);
                }
            });
            return commandWorker;
        }
    }

    private ClientCommandStrategy matchCommandStrategy(Channel channel, String command) {
        ClientCommands cmd = ClientCommands.getCommandType(command);
        switch (cmd) {
            case LOGIN:
                return new LoginClientCommandStrategy(this, channel);
        }
        return null;
    }

    public void consolePrint(String info) {
        System.out.println(info);
    }
}
