package com.vicyor.algorithm;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

/*
生成窗口的最大值数组
 */
public class Question_7 {
    public static void main(String[] args) {
        Question_7_MySlove slove = new Question_7_MySlove();
        slove.testSlove();
    }
}

/*
不容易碰到个会写的
时间复杂度 O(m*n)
 */
class Question_7_MySlove {
    private int[] arr = {4, 3, 5, 4, 3, 3, 6, 7};
    private int windowSize = 3;

    int[] generateSlove() {
        int[] maxs = new int[arr.length - windowSize + 1];
        for (int i = 0; i < maxs.length; i++) {
            int max = arr[i];
            for (int j = i + 1; j < i + windowSize; j++) {
                if (arr[j] > max) {
                    max = arr[j];
                }
            }
            maxs[i] = max;
        }
        return maxs;
    }

    public void testSlove() {
        int[] maxs = generateSlove();
        Arrays.stream(maxs).forEach(System.out::println);
    }
}

/*
  时间复杂度 o(n)
  就对arr进行一次遍历
  遍历到i的时候,嗯,就是 arr[i-w+1]~arr[i]的大的在队列首,且队列是降序的
*/
class Question_7_Slove1 {
    public int[] getMaxWindow(int[] arr, int w) {
        //存的是下标
        LinkedList<Integer> qmax = new LinkedList<>();
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            while (!qmax.isEmpty() && arr[qmax.peekLast()] < arr[i]) {
                qmax.pollLast();
            }
            qmax.addLast(i);
            if (qmax.peekFirst() == i - w) {
                qmax.pollFirst();
            }
            if (i >= w - 1) {
                res[index++] = arr[qmax.peekFirst()];
            }
        }
        return res;
    }
}