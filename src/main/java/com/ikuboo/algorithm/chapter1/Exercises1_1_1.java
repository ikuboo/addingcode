package com.ikuboo.algorithm.chapter1;

/**
 * 第一章练习题1.1.1
 */
public class Exercises1_1_1 {
    public static void main(String[] args) {
        int result1 = (0 + 15) / 2;
        System.out.println(result1);

        double result2 = 2.0e-6 * 100000000.1;
        //2.0 * 100000000.1 * 1e-6
        //200000000.2 * 1e-6
        //200.0000002
        System.out.println(result2);

        //true
        System.out.println(true && false || true && true);

    }
}
