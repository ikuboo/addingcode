package com.ikuboo.reflect;

import java.net.URL;

/**
 * Created by Administrator on 2018/3/3 0003.
 * BootstrapTest
 */
public class BootstrapTest {
    public static void main(String[] args) {
        //获取根类加载器所加载的全部URL数组
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();

        for (int i = 0; i < urls.length; i++) {
            System.out.println(urls[i].toExternalForm());
        }

    }
}
