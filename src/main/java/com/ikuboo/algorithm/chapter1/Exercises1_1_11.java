package com.ikuboo.algorithm.chapter1;

/**
 * 编写一段代码，打印出一个二位布尔数组的内容，其中*表示真，空格表示假，打印出行号和列号
 *
 */
public class Exercises1_1_11 {
    public static void main(String[] args) {
        boolean[][] data ={{true,false,true},{false,true,true},{false,false,true,true}};

        for (int i = 0; i < data.length; i++){
            for (int j = 0; j < data[i].length; j++){
                boolean ele = data[i][j];
                System.out.print(i + "_" + j + ":" + (ele ? "*" : " ") + "\t");
            }
            System.out.println("");
        }

    }
}
