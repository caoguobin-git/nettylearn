package com.duochuang;

import com.duochuang.websocket.server.ChatServer;
import io.netty.channel.ChannelFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetSocketAddress;

@SpringBootApplication
public class NettylearnApplication implements CommandLineRunner {

    @Value("${ChatServer.port}")
    private int port;

    @Autowired
    private ChatServer chatServer;

    public static void main(String[] args) {
        SpringApplication.run(NettylearnApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        ChannelFuture future = chatServer.start(new InetSocketAddress(port));
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run(){
                chatServer.destroy();
            }
        });
        future.channel().closeFuture().syncUninterruptibly();
    }
}
