/***********************************************
 * File Name: EchoClientHandler
 * @author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 05 11 2019 下午 3:23
 ***********************************************/

package com.duochuang.chapter72.handler;

import io.netty.channel.ChannelHandlerAdapter;

public class EchoClientHandler  extends ChannelHandlerAdapter {
    private final int sendNumber;

    public EchoClientHandler(int sendNumber) {
        this.sendNumber = sendNumber;
    }
}
