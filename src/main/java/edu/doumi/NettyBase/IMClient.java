package edu.doumi.NettyBase;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.nio.charset.Charset;


public class IMClient {
    private static final int MAX_CONNECT =10;
    private static String host = "127.0.0.1";
    private static int port = 8000;
    private static String charset = "UTF-8";
    public static void main(String[] args){
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootStrap = new Bootstrap();
        bootStrap
                .group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch){
                        ch.pipeline().addLast(new MyEvenHandler());
                    }
                });
        connect(bootStrap, host, port, 1);
    }

    private static void connect(final Bootstrap bootstrap,final String host,final int port,final int connectNum){
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                MyLog.info("连接成功!");
            } else {
                MyLog.info("第"+connectNum+"连接失败，开始重连");
                if(connectNum > MAX_CONNECT) return;
                connect(bootstrap, host, port,connectNum+1);
            }
        });
    }

    static class MyEvenHandler extends ChannelInboundHandlerAdapter{
  /*      @Override
        public void channelActive(ChannelHandlerContext ctx){
            ByteBuf byteBuf = getBuf(ctx);
            ctx.channel().writeAndFlush(byteBuf);
        }*/
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg){
            ByteBuf byteBuf = (ByteBuf)msg;
            MyLog.info(byteBuf.toString(Charset.forName(charset)));
        }
        private ByteBuf getBuf(ChannelHandlerContext ctx){
            ByteBuf byteBuf = ctx.alloc().buffer();
            byte[] bytes = getBytes();
            byteBuf.writeBytes(bytes);
            return byteBuf;
        }

        private byte[] getBytes(){
            String msg = "hello doumi！";
            return getBytes(msg);
        }
        private byte[] getBytes(String msg){
            byte[] bytes = msg.getBytes(Charset.forName(charset));
            return bytes;
        }
    }
}
