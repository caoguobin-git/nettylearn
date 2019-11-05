/***********************************************
 * File Name: TimeServerHandler
 * @author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 05 11 2019 上午 9:24
 ***********************************************/

package com.duochuang.io.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class TimeServerHandler implements Runnable {
    private Socket socket;

    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in=null;
        PrintWriter out=null;
        try {
            in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out=new PrintWriter(socket.getOutputStream());
            String body=null;
            while (true){
                body=in .readLine();
                if (body==null){
                    break;
                }
                if ("Time".equals(body)){
                    out.print(new Date().toString()+"\t");
                }
                out.println("server received: "+body);
                out.flush();

            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                in.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            out.close();
            try {
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
