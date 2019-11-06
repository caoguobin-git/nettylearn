/***********************************************
 * File Name: ChatServer
 * @author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 06 11 2019 上午 9:25
 ***********************************************/

package com.duochuang.websocket.server;

import com.duochuang.websocket.initializer.ChatServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.ImmediateEventExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

@Slf4j
@Component
public class ChatServer {
    private final ChannelGroup channelGroup=new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);
    private final EventLoopGroup group=new NioEventLoopGroup();
    private Channel channel;


    public ChannelFuture start(InetSocketAddress address) {
        ServerBootstrap bootstrap=new ServerBootstrap();
        bootstrap.group(group)
                .channel(NioServerSocketChannel.class)
                .childHandler(createInitializer(channelGroup));
        ChannelFuture future= null;
        try {
            future = bootstrap.bind(address).sync();
            future.syncUninterruptibly();
            channel=future.channel();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return future;
    }

    private ChannelHandler createInitializer(ChannelGroup channelGroup) {
        return new ChatServerInitializer(channelGroup);
    }

    public void destroy() {
        if (channel!=null){
            channel.close();
        }
        channelGroup.close();
        group.shutdownGracefully();
    }
}
