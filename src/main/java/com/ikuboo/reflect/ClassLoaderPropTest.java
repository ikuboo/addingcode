package com.ikuboo.reflect;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.Enumeration;

/**
 * Created by Administrator on 2018/3/3 0003.
 * 类加载器
 */
public class ClassLoaderPropTest {
    public static void main(String[] args) throws IOException {
        //获取系统类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println("系统类加载器:" + systemClassLoader);

        //获取系统类加载器的加载class的路径
        Enumeration<URL> resources = systemClassLoader.getResources("");
        while (resources.hasMoreElements()){
            System.out.println(resources.nextElement());
        }

        //获取扩展类加载器
        ClassLoader extClassLoader = systemClassLoader.getParent();
        String property = System.getProperty("java.ext.dirs");
        System.out.println("扩展类加载器:" + extClassLoader);
        System.out.println("扩展类加载器加载路径:" + property);
    }
}
