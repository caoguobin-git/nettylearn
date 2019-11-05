/***********************************************
 * File Name: TimeServer
 * @author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 05 11 2019 下午 12:19
 ***********************************************/

package com.duochuang.netty.server;

import com.duochuang.netty.handler.TimeServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TimeServer {
    public void bind(int port){
        EventLoopGroup bossGroup=new NioEventLoopGroup();
        EventLoopGroup workerGroup=new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap=new ServerBootstrap();
            serverBootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,1024)
                    .childHandler(new ChildChannelHandler());
            ChannelFuture f =serverBootstrap.bind(port).sync();
            f.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }


    }

    private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            ch.pipeline().addLast(new TimeServerHandler());
        }
    }

    public static void main(String[] args) {
        int port=8080;
        new TimeServer().bind(port);
    }
}
