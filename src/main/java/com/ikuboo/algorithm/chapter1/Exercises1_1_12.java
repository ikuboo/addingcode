package com.ikuboo.algorithm.chapter1;

/**
 * 以下代码会打印出什么？
 */
public class Exercises1_1_12 {
    public static void main(String[] args) {
        int[] a = new int[10];
        //init 0
        for (int i = 0; i < 10; i++) {
            a[i] = 9 - i;
        }
        //[9,8,7,6,5,4,3,2,1,0]

        for (int i = 0; i < 10; i++) {
            a[i] = a[a[i]];
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(a[i]);
        }
    }
}
