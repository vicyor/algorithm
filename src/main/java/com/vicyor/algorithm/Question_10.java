package com.vicyor.algorithm;

import java.util.LinkedList;

/*
    一维数组
    子数组最大值减去最小值小于或等于num的数量.
    要求实现时间复杂度为O(N)的算法.
    最大值-最小值相当于物理中的带宽.
 */
public class Question_10 {
}

/*
    若 arr[i] ~ arr[j] 满足 max(arr[i]~arr[j]) -min(arr[i]-arr[j])<num,
    则 arr[k]~arr[l]亦满足. i < k < l < j.
 */
class Question_10_Slove {
    public static int getNum(int[] arr, int num) {
        int res = 0;
        int i = 0;
        int j = 0;
        //存下标
        LinkedList<Integer> qmin = new LinkedList<>();
        LinkedList<Integer> qmax = new LinkedList<>();
        while (i < arr.length) {
            while (j < arr.length) {
                //尾巴
                while (!qmin.isEmpty() && arr[qmin.peekLast()] > arr[j]) {
                    qmin.pop();
                }
                qmin.addLast(j);
                //尾巴
                while (!qmax.isEmpty() && arr[qmax.peekLast()] < arr[j]) {
                    qmax.pop();
                }
                qmax.addLast(j);
                if (arr[qmax.peekLast()] - arr[qmin.peekLast()] > num) {
                    break;
                }
                j++;
            }
            res += j - i;
            //头
            if (qmin.peekFirst() == i) {
                qmin.removeFirst();
            }
            //头
            if (qmax.peekFirst() == i) {
                qmax.removeFirst();
            }
            i++;
        }
        return res;
    }
}