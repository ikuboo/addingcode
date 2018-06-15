package com.ikuboo.algorithm.chapter1;

/**
 * 第一章练习题1.1.7
 */
public class Exercises1_1_7 {
    public static void main(String[] args) {
        //a();
        b();
    }


    public static void a(){
        double t = 9.0;
        while(Math.abs(t - 9.0/t) > .001){

            t = (9.0 / t + t) / 2.0;
        }
        System.out.println(String.format("%.5f",t));
    }

    public static void b(){
        int sum = 0;
        for (int i = 1; i < 1000; i++) {
            for(int j = 0; j < i; j++){
                sum ++;
            }
        }
        System.out.println(sum);
        //等差数列 1 + 2 + 3 + 4 + 5 + ... + n
        // n * (n + 1) / 2
        System.out.println(999 * (999 + 1) / 2);
    }

    public static void c(){
        int sum = 0;
        for (int i = 1; i < 1000; i *= 2) {
            for (int j = 0; j < 1000; j++) {
                sum ++;
            }
        }

        // 等比数列
        // (1 + 2 + 4 + 8 + 16 + 32  + ...  ) * 1000

    }
}
