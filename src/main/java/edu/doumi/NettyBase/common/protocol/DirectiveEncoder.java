package edu.doumi.NettyBase.common.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class DirectiveEncoder extends MessageToByteEncoder<DirectivePrototype> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, DirectivePrototype directivePrototype, ByteBuf byteBuf) throws Exception {
        ProtocolHandler.JSONINSTANCE.writeToBuf(byteBuf, directivePrototype);
    }
}
