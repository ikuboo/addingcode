package com.ikuboo.algorithm.chapter1;

import java.util.Scanner;

/**
 * 第一章练习题1.1.3
 */
public class Exercises1_1_3 {
    public static void main(String[] args) {
       int[] in = new int[3];

        for (int i = 0; i < in.length; i++) {
            Scanner scanner = new Scanner( System.in);
            in[i] = scanner.nextInt();
        }

        int temp = in[0];
        boolean result = true;
        for (int i = 1; i < in.length; i++) {
            result = result && (temp == in[i]);
        }

        if (result)
            System.out.println("equal");
        else
            System.out.println("not equal");
    }
}
