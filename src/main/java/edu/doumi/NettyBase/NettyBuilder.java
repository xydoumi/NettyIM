package edu.doumi.NettyBase;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyBuilder{
    private HandlerRegister handlerRegisterDefault;
    public static NettyBuilder INSTANCE = new NettyBuilder();
    public ServerBootstrap nettyServiceBuilder(Integer port){
        initRegister(null);
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        serverBootstrap
                .group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    protected void initChannel(NioSocketChannel ch) {
                        ch.pipeline().addLast();
                        // TODO 后续自动配置，难点需要保证顺序handler顺序
                        // handlerRegisterDefault.registerHandler(ch);
                    }
                });
        bind(serverBootstrap,port);

        return serverBootstrap;
    }

    public void nettyClientBuilder(){

    }

    // 服务器NIO socket绑定
    public static void bind(final ServerBootstrap s,final int port){
        s.bind(port).addListener(future -> {
                    if(future.isSuccess()){
                        MyLog.info("服务器启动————————————————");
                    }else{
                        MyLog.info("端口"+port+"启动失败");
                        bind(s,port+1);
                    }
                }
        );
    }

    /**
     * 初始化handler注册器
     */
    private void initRegister(HandlerRegister handlerRegister){
        if(handlerRegister == null){
            this.handlerRegisterDefault = new SpringHandlerRegister();
        }
        this.handlerRegisterDefault = handlerRegister;
    }
}
