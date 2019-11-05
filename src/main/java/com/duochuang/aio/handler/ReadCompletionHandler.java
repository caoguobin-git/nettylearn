/***********************************************
 * File Name: ReadCompletionHandler
 * @author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 05 11 2019 下午 12:11
 ***********************************************/

package com.duochuang.aio.handler;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class ReadCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {

    private AsynchronousSocketChannel channel;

    public ReadCompletionHandler(AsynchronousSocketChannel channel) {
        if (this.channel == null) {
            this.channel = channel;
        }
    }

    @Override
    public void completed(Integer result, ByteBuffer attachment) {

    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {

    }
}
