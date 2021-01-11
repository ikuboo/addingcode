package com.ikuboo.io.bio.v1;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

/**
 * 客户端
 */
public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        //创建socket对象请求服务端
        Socket socket = new Socket("127.0.0.1", 9999);
        //从socket中获取一个字节输入流
        OutputStream os = socket.getOutputStream();
        //将字节输出流包装成一个打印流
        PrintStream printStream = new PrintStream(os);
        printStream.println("hello, 我是个消息体");
    }
}
