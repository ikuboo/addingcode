package com.ikuboo.algorithm.chapter1;

/**
 *编写一个方法log(),接受一个整型参数N，返回不大于㏒₂N、的最大整数，不要使用Math库
 *
 * log(x)(y) = log y / log x;
 */
public class Exercises1_1_14 {
    public static void main(String[] args) {
        System.out.println(lg(9));
    }

    public static int lg(int n){
        int log = 2;
        int b = 0;
        while (log < n){
            log *= 2;
            b ++;
        }
        return b;
    }
}
