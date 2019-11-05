/***********************************************
 * File Name: TimeServer
 * @author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 05 11 2019 上午 10:24
 ***********************************************/

package com.duochuang.nio.server;

import com.duochuang.nio.selector.MultiplexerTimerServer;

public class TimeServer {
    public static void main(String[] args) {
        int port=8080;
        MultiplexerTimerServer timerServer=new MultiplexerTimerServer(port);
        new Thread(timerServer,"NIO-MultiplexerTimeServer-001").start();
    }
}
