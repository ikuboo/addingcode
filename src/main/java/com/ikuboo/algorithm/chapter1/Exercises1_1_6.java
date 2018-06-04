package com.ikuboo.algorithm.chapter1;

/**
 * 第一章练习题1.1.5
 */
public class Exercises1_1_6 {
    public static void main(String[] args) {
        int f = 0;
        int g = 1;
        for (int i = 0; i < 15; i++) {
            System.out.println(f);
            f = f + g;
            g = f - g;
        }
    }
}
