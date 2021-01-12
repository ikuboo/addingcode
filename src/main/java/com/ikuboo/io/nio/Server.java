package com.ikuboo.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class Server {
    public static void main(String[] args) throws IOException {
        //打开通道
        ServerSocketChannel ssc = ServerSocketChannel.open();
        //设置为非阻塞模式
        ssc.configureBlocking(false);
        ssc.bind(new InetSocketAddress(9999));
        //打开多路复用器
        Selector selector = Selector.open();
        //注册多路复用器到通道上
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        //多路复用器轮训
        while (selector.select() > 0){
            //监听事件
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            //遍历事件
            while (it.hasNext()){
                SelectionKey sk = it.next();
                //注册事件
                if(sk.isAcceptable()){
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);
                    sc.register(selector, SelectionKey.OP_READ);
                //读事件
                }else if(sk.isReadable()){
                    SocketChannel channel = (SocketChannel) sk.channel();
                    StringBuffer stringBuffer = new StringBuffer();
                    //申请缓冲区
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int len = 0;
                    while ((len = channel.read(buffer)) > 0){
                        buffer.flip();
                        stringBuffer.append(new String(buffer.array(), 0, len));
                        buffer.clear();
                    }
                    System.out.println("服务端接受到消息:" + stringBuffer.toString());
                }
                it.remove();
            }
        }
    }
}
