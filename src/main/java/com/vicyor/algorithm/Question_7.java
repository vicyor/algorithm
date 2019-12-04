package com.vicyor.algorithm;

import org.junit.Test;

import java.util.Arrays;

/*
生成窗口的最大值数组
 */
public class Question_7 {
    public static void main(String[] args) {
        Question_7_MySlove slove=new Question_7_MySlove();
        slove.testSlove();
    }
}

/*
不容易碰到个会写的
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
