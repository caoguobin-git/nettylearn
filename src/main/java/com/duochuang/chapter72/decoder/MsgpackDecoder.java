/***********************************************
 * File Name: MsgpackDecoder
 * @author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 05 11 2019 下午 3:12
 ***********************************************/

package com.duochuang.chapter72.decoder;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.msgpack.MessagePack;

import java.util.List;

public class MsgpackDecoder extends MessageToMessageDecoder<ByteBuf> {


    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        final byte[] arr;
        final int length = msg.readableBytes();
        arr=new byte[length];
        msg.getBytes(msg.readerIndex(),arr,0,length);
        MessagePack messagePack=new MessagePack();
        out.add(messagePack.read(arr));
    }
}
