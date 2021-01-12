package com.ikuboo.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousByteChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {

        AsynchronousSocketChannel a = AsynchronousSocketChannel.open();

        SocketChannel sc = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9999));
        sc.configureBlocking(false);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.print("请输入要发送的数据:");
            String msg = scanner.nextLine();
            buffer.put(msg.getBytes());
            buffer.flip();
            sc.write(buffer);
            buffer.clear();
        }
    }
}
