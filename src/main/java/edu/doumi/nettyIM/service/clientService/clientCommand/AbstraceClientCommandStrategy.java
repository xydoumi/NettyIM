package edu.doumi.nettyIM.service.clientService.clientCommand;

import edu.doumi.nettyIM.service.clientService.ClientBoundary;

import io.netty.channel.Channel;

abstract  class AbstraceClientCommandStrategy implements ClientCommandStrategy {
    protected ClientBoundary clientBoundary;
    protected Channel channel;

    @Override
    public void exec() {
        exec(clientBoundary, channel);
    }

    abstract protected void exec(ClientBoundary clientBoundary, Channel channel);
}
