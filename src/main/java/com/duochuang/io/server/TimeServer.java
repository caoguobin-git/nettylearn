/***********************************************
 * File Name: TimeServer
 * @author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 05 11 2019 上午 9:25
 ***********************************************/

package com.duochuang.io.server;

import com.duochuang.io.handler.TimeServerHandler;
import com.duochuang.io.pool.TimeServerHandlerExecutePool;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeServer {
    public static void main(String[] args) throws IOException {
        int port=8080;
        ServerSocket server=null;
        try {
            server=new ServerSocket(port);
            System.out.println("TimeServer is listening on "+port);
            Socket socket=null;
            TimeServerHandlerExecutePool pool=new TimeServerHandlerExecutePool(50,10000);
            while (true){
                socket=server.accept();
                pool.execute(new TimeServerHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (server!=null){
                System.out.println("server close.");
                server.close();
                server=null;
            }
        }
    }
}
