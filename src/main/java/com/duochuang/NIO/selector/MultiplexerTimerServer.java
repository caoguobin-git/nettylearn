/***********************************************
 * File Name: MultiplexerTimerServer
 * @author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 05 11 2019 上午 10:26
 ***********************************************/

package com.duochuang.nio.selector;

import com.google.common.base.Strings;
import io.netty.buffer.ByteBuf;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class MultiplexerTimerServer implements Runnable {

    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private volatile boolean stop;

    /**初始化多路复用器*/
    public MultiplexerTimerServer(int port){
        /**
         * 1. 创建selector
         * 2. 打开channel
         * 3. 设置非阻塞
         * 4. 绑定端口
         * 5. 注册到selector
         */
        try {
            selector=Selector.open();
            serverSocketChannel=ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(port),1024);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("The time server is start in port: "+port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop(){
        this.stop=true;
    }

    @Override
    public void run() {
        /**
         * 1. 执行选择操作
         * 2. 获取所有key
         * 3. 遍历keys并处理
         */
        while (!stop){
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                SelectionKey key=null;
                while (iterator.hasNext()){
                    key=iterator.next();
                    iterator.remove();
                    try{
                        handleInput(key);
                    }catch (Exception e){
                        e.printStackTrace();
                        if (key!=null){
                            key.cancel();
                            if (key.channel()!=null){
                                key.channel().close();
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (selector!=null){
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleInput(SelectionKey key) throws IOException {
        if (key.isValid()){
            //处理新接入请求
            if (key.isAcceptable()){
                ServerSocketChannel ssc= (ServerSocketChannel) key.channel();

                //todo 为什么不用这个？
                SocketChannel sc = serverSocketChannel.accept();

//                SocketChannel sc = ssc.accept();
                sc.configureBlocking(false);
                //添加新请求到selector
                sc.register(selector, SelectionKey.OP_READ);
            }
            if (key.isReadable()){
                SocketChannel sc= (SocketChannel) key.channel();
                ByteBuffer readBuffer= ByteBuffer.allocate(1024);
                int readBytes = sc.read(readBuffer);
                if (readBytes>0){
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String body=new String(bytes,"UTF-8");
                    System.out.println("The time server receive order: "+body);
                    doWrite(sc,new Date().toString());
                }else if (readBytes<0){
                    key.cancel();
                    sc.close();
                }else {
                }
            }
        }
    }

    private void doWrite(SocketChannel sc, String response) throws IOException {
        System.out.println(response);
        if (!Strings.isNullOrEmpty(response)){
            byte[] bytes = response.getBytes();
            ByteBuffer writeBuffer=ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            sc.write(writeBuffer);
        }
    }
}
