package com.ikuboo.algorithm.chapter1.part1_4.sub1_4_2;

import com.ikuboo.algorithm.tools.StopWatch;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class ThreeSum {

    public static int count(int[] param){
        int len = param.length;
        int count = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + i; j < len; j++) {
                for (int k = j + 1; k < len; k++) {
                    if(param[i] + param[j] + param[k] == 0){
                        count ++;
                    }
                }
            }
        }
        return count;
    }

    public static double timeTrial(int len){
        int max = 1000000;
        int[] a = new int[len];

        for (int i = 0; i < a.length; i++) {
            a[i] = StdRandom.uniform(-max, max);
        }
        StopWatch stopWatch = new StopWatch();
        count(a);
        return stopWatch.elapsedTime();
    }

    public static void main(String[] args) {
        for (int len = 250; len < 100000; len += len){
            double v = timeTrial(len);
            StdOut.println(v);
        }
    }

}
