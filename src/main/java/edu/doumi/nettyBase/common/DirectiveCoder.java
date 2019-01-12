package edu.doumi.nettyBase.common;

import edu.doumi.nettyBase.common.directive.IMDirective;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.List;

@ChannelHandler.Sharable
public class DirectiveCoder extends MessageToMessageCodec<ByteBuf, IMDirective> {

    public DirectiveCoder() {
    }

    public static class Builder {
        private static DirectiveCoder instance = new DirectiveCoder();

        public static DirectiveCoder build() {
            return instance;
        }
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, IMDirective directive, List<Object> list) throws Exception {
        ByteBuf byteBuf = ctx.channel().alloc().ioBuffer();
        ProtocolHandler.JSON_INSTANCE.writeToBuf(byteBuf, directive);
        list.add(byteBuf);
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
         list.add(ProtocolHandler.JSON_INSTANCE.readBuf(byteBuf));
    }
}
