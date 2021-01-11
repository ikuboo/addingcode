package com.ikuboo.algorithm.jike;

/**
 * 归并排序
 * 时间复杂度 log n
 * 空间复杂度 n
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] b = new int[]{1,6,4,2,3};
        int[] c = mergeSort(b, 0, b.length-1);
        for (int i : c) {
            System.out.println(i);
        }
    }

    public static int[] mergeSort(int[] a, int l, int r) {
        if (l >= r) {
            return new int[]{a[l]};
        }

        int q =  (l + r) / 2;

        int[] c_1 = mergeSort(a, l, q);
        int[] c_2 = mergeSort(a,q + 1, r);

        int i = 0;
        int j = 0;
        int k = 0;

        int[] temp = new int[c_1.length + c_2.length];
        while (i < c_1.length && j < c_2.length) {
            if (c_1[i] > c_2[j]) {
                temp[k++] = c_2[j++];
            } else {
                temp[k++] = c_1[i++];
            }
        }
        for (int m = i; m < c_1.length; m++) {
            temp[k++] = c_1[m];
        }

        for (int n = j; n < c_2.length; n++) {
            temp[k++] = c_2[n];
        }

        return temp;
    }
}
