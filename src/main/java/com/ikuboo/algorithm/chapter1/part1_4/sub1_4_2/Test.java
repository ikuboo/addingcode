package com.ikuboo.algorithm.chapter1.part1_4.sub1_4_2;

public class Test {

    public static void main(String[] args) {
        System.out.println(jiechen(1000));
    }

    public static double jiechen(int n){
        if(n == 1){
            return 1;
        }

         return Math.pow(n,2) + jiechen(n - 1);
    }
}
