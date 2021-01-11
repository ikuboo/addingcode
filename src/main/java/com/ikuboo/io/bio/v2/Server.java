package com.ikuboo.io.bio.v2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 模拟BIO的服务端
 * 接收客户端的请求数据和向客户端发送数据
 */
public class Server {
    public static void main(String[] args) throws IOException {
        try{
            //定义一个ServerSocket对象进行服务端的端口注册
            ServerSocket serverSocket = new ServerSocket(9999);
            //监听客户端的socket连接请求
            System.out.println("服务端启动");
            Socket socket = serverSocket.accept();
            System.out.println("服务端socket打开");
            //从socket管道中获取一个字节输入流
            InputStream is = socket.getInputStream();
            //将字节输入流转换为字符输入流
            InputStreamReader isr = new InputStreamReader(is);
            //将字符输入流包装成一个缓冲字符输入流
            BufferedReader br = new BufferedReader(isr);

            String msg;
            while ((msg = br.readLine()) != null){
                System.out.println("服务端接受到数据:" + msg);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
