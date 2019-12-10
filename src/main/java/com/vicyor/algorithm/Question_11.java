package com.vicyor.algorithm;

import java.util.LinkedList;

/*
    可见的山峰问题
    一个不重复的数组可以代表一座环形山.
    数组每个位置的值代表山的高度.
    1.同一座山是看不见的.
    2.相邻的山能互相看见.
    3.不相邻的山之间若有更高的山,那不相邻的山是看不见的.
 */
public class Question_11 {
}
/*
 o(n*n)
 */
class Question_11_MySlove {
    public int getCount(int[] arr) {
        int res = 0;
        //顺时针
        for (int i = 0; i < arr.length; i++) {
            int max = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] > arr[max]) {
                    max = j;
                }
                if (j - i <= 1) {
                    res++;
                    continue;
                }
                if (arr[max] > arr[i] || arr[max] > arr[j]) {
                    //几种特殊情况
                    if(arr[max]>arr[i]&&arr[max]>arr[j]){

                    }
                    break;
                }
            }
        }
        return res;
    }
}
