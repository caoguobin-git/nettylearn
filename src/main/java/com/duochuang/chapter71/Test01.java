/***********************************************
 * File Name: Test01
 * @author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 05 11 2019 下午 3:05
 ***********************************************/

package com.duochuang.chapter71;

import org.msgpack.MessagePack;
import org.msgpack.template.Template;
import org.msgpack.template.Templates;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Test01 {
    public static void main(String[] args) throws IOException {
        List<String> src=new LinkedList<>();
        src.add("a");
        src.add("b");
        src.add("c");
        src.add("d");
        MessagePack pack=new MessagePack();
        byte[] write = pack.write(src);
        List<String> read = pack.read(write, Templates.tList(Templates.TString));
        read.forEach(k-> System.out.println(k));
    }
}
