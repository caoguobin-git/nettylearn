/***********************************************
 * File Name: TimeServerHandler
 * @author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 05 11 2019 下午 12:25
 ***********************************************/

package com.duochuang.chapter43.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

public class TimeServerHandler extends ChannelHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte[] req=new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body=new String(req,"UTF-8");
        System.out.println("The time server receive order: "+body);
        String currentTime="Time".equalsIgnoreCase(body)?new Date(System.currentTimeMillis()).toString():"BAD ORDER";
        ByteBuf resp= Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.write(resp);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("读取完成，发送 ~ ~ ~");
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
