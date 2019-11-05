/***********************************************
 * File Name: EchoServer
 * @author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 04 11 2019 下午 5:08
 ***********************************************/

package com.duochuang.server;

import com.duochuang.handler.EchoServerhandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class EchoServer {
    private final int port=8090;

    public EchoServer() {
    }

    public static void main(String[] args) throws InterruptedException {
        new EchoServer().start();
    }

    public void start() throws InterruptedException {
        final EchoServerhandler serverhandler=new EchoServerhandler();
        EventLoopGroup group=new NioEventLoopGroup();
        try {
        ServerBootstrap serverBootstrap=new ServerBootstrap();
        serverBootstrap.group(group)
                .channel(NioServerSocketChannel.class)
                .localAddress(new InetSocketAddress("127.0.0.1",port))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(serverhandler);
                    }
                });
        ChannelFuture channelFuture = serverBootstrap.bind().sync();

            channelFuture.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }

    }
}
