package edu.doumi.nettyBase;

import edu.doumi.nettyBase.common.DirectiveCoder;
import edu.doumi.nettyBase.common.Spliter;
import edu.doumi.nettyIM.channelHandler.IMHandler;
import edu.doumi.nettyIM.channelHandler.clientHandler.ClientLoginHandler;
import edu.doumi.nettyIM.service.clientService.ClientBoundary;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyBuilder {
    private static final int MAX_CONNECT = 10;

    public static NettyBuilder INSTANCE = new NettyBuilder();

    public ServerBootstrap nettyServiceBuilder(Integer port) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        try {
            serverBootstrap
                    .group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {
                        protected void initChannel(NioSocketChannel ch) {
                            ch.pipeline().addLast(new Spliter());
                            ch.pipeline().addLast(DirectiveCoder.Builder.build());
                            ch.pipeline().addLast(IMHandler.Builder.build(null));
                        }
                    });
        } catch (Exception e) {
            MyLog.info(e.getMessage());
        }
        bind(serverBootstrap, port);

        return serverBootstrap;
    }

    public void nettyClientBuilder(final int port, ClientBoundary clientBoundary) {
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                .group(workGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        ch.pipeline().addLast(new Spliter());
                        ch.pipeline().addLast(DirectiveCoder.Builder.build());
                        //ch.pipeline().addLast(IMHandler.Builder.build(new ClientSpringHandlerRegister()));

                        ch.pipeline().addLast(new ClientLoginHandler(clientBoundary));
                    }
                });
        connect(bootstrap, "127.0.0.1", port, 1, clientBoundary);
    }

    // 服务器NIO socket绑定
    private static void bind(final ServerBootstrap s, final int port) {
        s.bind(port).addListener(future -> {
                    if (future.isSuccess()) {
                        Channel channel = ((ChannelFuture)future).channel();
                        ChannelPipeline pipeline = channel.pipeline();
                        MyLog.info("服务器启动————————————————");
                    } else {
                        MyLog.info("端口" + port + "启动失败");
                        bind(s, port + 1);
                    }
                }
        );
    }

    private static void connect(final Bootstrap bootstrap, final String host, final int port, final int connectNum, ClientBoundary clientBoundary) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                MyLog.info("连接成功!");
                Channel channel = ((ChannelFuture) future).channel();
                clientBoundary.open(channel);
            } else {
                MyLog.info("第" + connectNum + "连接失败，开始重连");
                if (connectNum > MAX_CONNECT) return;
                connect(bootstrap, host, port, connectNum + 1, clientBoundary);
            }
        });
    }

}
