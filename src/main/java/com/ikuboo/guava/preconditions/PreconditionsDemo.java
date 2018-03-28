package com.ikuboo.guava.preconditions;

import com.google.common.base.Preconditions;

/**
 * @author yuanchunsen@jd.com
 *         2018/1/21.
 */
public class PreconditionsDemo {


    public static void main(String[] args) {
       sqrt(1f);
       sum(1, 1);
       getValue(10);

    }

    private static double sqrt(double input){
        Preconditions.checkArgument(input>0.0,
                "Illegal Argument passed: Negative value %s.",input);
        return Math.sqrt(input);
    }

    private static int sum(Integer a,Integer b){
        a=Preconditions.checkNotNull(a,
                "Illegal Argument passed: First parameter is Null.");
        b=Preconditions.checkNotNull(b,
                "Illegal Argument passed: First parameter is Null.");
        return a+b;
    }

    private static int getValue(int input){
        int[] data={1,2,3,4,5};
        int index= Preconditions.checkElementIndex(input,data.length,
                "Illegal Argument passed: Invalid index.");
        return data[index];
    }
}
