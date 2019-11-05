/***********************************************
 * File Name: TimeServer
 * @author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 05 11 2019 上午 11:53
 ***********************************************/

package com.duochuang.aio.server;

import com.duochuang.aio.handler.AsyncTimeServerHandler;

public class TimeServer {
    public static void main(String[] args) {
        int port = 8080;
        AsyncTimeServerHandler timeServer = new AsyncTimeServerHandler(port);
        new Thread(timeServer,"AIO-AsyncTimeServerHandler-001").start();
    }
}
