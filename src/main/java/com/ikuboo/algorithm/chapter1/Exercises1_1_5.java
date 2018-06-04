package com.ikuboo.algorithm.chapter1;

/**
 * 第一章练习题1.1.5
 */
public class Exercises1_1_5 {
    public static void main(String[] args) {
      double x,y;
      x = 0.2d;
      y = 0.4d;

      boolean xb = x > 0.0 && x < 1.0;
      boolean yb = y > 0.0 && y < 1.0;

      if(xb && yb)
          System.out.println("true");
      else
          System.out.println("false");
    }
}
