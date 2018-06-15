package com.ikuboo.algorithm.chapter1;

/**
 * 第一章练习题1.1.6
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

        System.out.println("--------------------");

        print();

    }




    public static void print(){
        int n1 = 0;
        int n2 = 1;

        for (int i = 0; i < 10; i++) {
            int temp = n2;
            n2 = n2 + n1;
            n1 = temp;
            System.out.println(n2);

        }
    }

}
