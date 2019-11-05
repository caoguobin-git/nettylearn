/***********************************************
 * File Name: TimeClient
 * @author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 05 11 2019 上午 9:25
 ***********************************************/

package com.duochuang.io.client;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TimeClient {
    public static void main(String[] args) throws IOException {
        int port=8080;
        BufferedReader in=null;
        PrintWriter out=null;
        Socket socket=new Socket("127.0.0.1",port);
        Scanner scanner=new Scanner(System.in);
        try {
            in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out=new PrintWriter(socket.getOutputStream(),true);
            while (true){
                String comm=scanner.nextLine();
                out.println(comm);
                String s = in.readLine();
                System.out.println("client received: "+s);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            in.close();
            out.close();
            socket.close();
        }
    }
}
