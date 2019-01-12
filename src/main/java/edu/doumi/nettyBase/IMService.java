package edu.doumi.nettyBase;

import io.netty.bootstrap.ServerBootstrap;

public class IMService {
    private static final int port = 8000;
    private static final String WELCOME_STR = "welcome to doumi space.";
    private static final String charset = "UTF-8";
    public static void main(String[] args){
        ServerBootstrap serverBootstrap = NettyBuilder.INSTANCE.nettyServiceBuilder(port);
    }



/*
    // 服务处理事件
    static class MyServerEventHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelActive(ChannelHandlerContext ctx){
            MyLog.info("服务启动发送信息：");
            ByteBuf byteBuf = getBuf(ctx);
            ctx.channel().writeAndFlush(byteBuf);
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg){
            MyLog.info("服务器读取信息：");
            ByteBuf byteBuf = (ByteBuf)msg;
            MyLog.info(byteBuf.toString(Charset.forName(charset)));
        }

        private ByteBuf getBuf(ChannelHandlerContext ctx){
            ByteBuf bytes = ctx.alloc().buffer();
            bytes.writeBytes(getBytes());
            return bytes;
        }

        private byte[] getBytes(){
            String msg = getMsg();
            return getBytes(msg);
        }

        private byte[] getBytes(String msg){
            byte[] bytes = msg.getBytes(Charset.forName(charset));
            return bytes;
        }

        private String getMsg(){
            return WELCOME_STR;
        }
    }
*/

}
