package com.ikuboo.io.bio.v3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 模拟BIO的服务端
 * 接收多个客户端的请求数据
 * 一个client 对应一个处理线程
 */
public class Server {
    public static void main(String[] args) throws IOException {
        try {
            //定义一个ServerSocket对象进行服务端的端口注册
            ServerSocket serverSocket = new ServerSocket(9999);
            System.out.println("服务端启动");
            while (true) {
                //监听客户端的socket连接请求
                Socket socket = serverSocket.accept();
                ServiceSocketThread thread = new ServiceSocketThread(socket);
                thread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class ServiceSocketThread extends Thread {
        private static AtomicInteger clientCount = new AtomicInteger(0);
        private Socket socket;

        public ServiceSocketThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            System.out.println("有新的client链接,当前客户端总数:" + clientCount.incrementAndGet());
            try {
                //从socket管道中获取一个字节输入流
                InputStream is = socket.getInputStream();
                //将字节输入流转换为字符输入流
                InputStreamReader isr = new InputStreamReader(is);
                //将字符输入流包装成一个缓冲字符输入流
                BufferedReader br = new BufferedReader(isr);
                String msg;
                while ((msg = br.readLine()) != null && !socket.isClosed()) {
                    System.out.println("服务端接受到数据:" + msg);
                }

                System.out.println("Socket关闭");
                clientCount.decrementAndGet();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
