package com.ikuboo.algorithm.chapter1;

/**
 * 第一章练习题1.1.8
 *
 */
public class Exercises1_1_9 {
    public static void main(String[] args) {

        //10进制转2进制,转string格式输入
        Exercises1_1_9.impl1(4);
        Exercises1_1_9.impl2(4);


        byte b = 'a';//一个字节可以表示 -128 到 127 的数字
        String s = Integer.toBinaryString(b);//十进制转二进制转string
        int i = Integer.valueOf(s,2);//二进制转十进制

        System.out.println(b);
        System.out.println(s);
        System.out.println(i);
    }
    public static  void impl1(int n){
        String str = Integer.toBinaryString(n);
        System.out.println(str);
    }

    public static  void impl2(int n){
        String str = "";

        for(int i = n; i > 0; i /=2){
            str  = (i % 2 ) + str;
        }

        System.out.println(str);
    }


}
