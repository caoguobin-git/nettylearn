/***********************************************
 * File Name: HttpFileServerHandler
 * @author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 05 11 2019 下午 3:51
 ***********************************************/

package com.duochuang.chapter102.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;

public class HttpFileServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private String url;

    public HttpFileServerHandler(String url) {
        this.url = url;
    }


    @Override
    protected void messageReceived(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
        if (!msg.getDecoderResult().isSuccess()){
        }
    }
}
