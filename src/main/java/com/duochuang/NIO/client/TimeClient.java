/***********************************************
 * File Name: TimeClient
 * @author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 05 11 2019 上午 11:16
 ***********************************************/

package com.duochuang.nio.client;

import com.duochuang.nio.hanlder.TimeClientHandler;

import java.io.IOException;

public class TimeClient {
    public static void main(String[] args) throws IOException {
        int port=8080;
        new Thread(new TimeClientHandler("127.0.0.1",port),"NIO-TimeClient-001").start();
    }
}
