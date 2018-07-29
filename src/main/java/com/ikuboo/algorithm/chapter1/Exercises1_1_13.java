package com.ikuboo.algorithm.chapter1;

/**
 * 编写一段代码，打印出一个M行N猎的二维数组转置（交换行和列）
 *
 * before
 * a,b,c,d
 * e,f,g,h
 * i,j,k,l
 *
 * after
 *
 * a,e,i
 * b,f,j
 * c,g,k
 * d,h,l
 *
 */
public class Exercises1_1_13 {
    public static void main(String[] args) {
       char[][] data = {{'a','b','c','d'},{'e','f','g','h'},{'i','j','k','l'}};

       char[][] datato = new char[4][3];

       for(int i = 0; i < data.length; i++){
           for (int j = 0; j < data[i].length; j++) {
              datato[j][i] =  data[i][j];
           }
       }

        for (int i = 0; i < datato.length; i++) {
            for (int j = 0; j < datato[i].length; j++) {
                System.out.print(datato[i][j] + "\t");
            }
            System.out.println("");
        }
    }
}
