package com.vicyor.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/*
单调栈结构
    给定一个不含有重复值的数组arr,
    找到每一个i位置左边和右边离i位置最近且值比arr[i]小的位置
    若不存在则返回位置-1
*/
public class Question_8 {
    public static void main(String[] args) {
        int[] arr = {3, 4, 1, 5, 6, 2, 7};
        Question_8_MySlove slove = new Question_8_MySlove();
        slove.computeResult(arr);

    }
}

//解向量
class Question_8_SV {
    int left = -1;
    int right = -1;

    @Override
    public String toString() {
        return String.format("[left: %s ,right: %s]", left, right);
    }

    void setLeft(int left) {
        this.left = left;
    }

    void setRight(int right) {
        this.right = right;
    }
}

/*
时间复杂度 O(n)
单调栈(递减) 弹出x元素时,num为x的右最小
      最后栈顶为遍历元素的左最小
 */
class Question_8_MySlove {
    void initStack(List<Question_8_SV> svs, int length) {
        for (int i = 0; i < length; i++) {
            svs.add(new Question_8_SV());
        }
    }

    void computeResult(int[] arr) {
        List<Question_8_SV> svs = new ArrayList(arr.length);
        Arrays.stream(arr).forEach(num -> System.out.print(num + "  "));
        System.out.println();
        //存放下标
        Stack<Integer> stack = new Stack<>();
        int last_unupdated = 0;
        initStack(svs, arr.length);
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                Integer index = stack.pop();
                Question_8_SV sv = svs.get(index);
                sv.right = i;
            }
            if (!stack.isEmpty()) {
                Question_8_SV sv = svs.get(i);
                sv.left = stack.peek();
            }
            stack.push(i);
        }
        outPutResult(svs);
    }

    void outPutResult(List<Question_8_SV> svs) {
        svs.stream().forEach(System.out::println);
    }

}

/*
官方解
在元素弹出的时候再计算位置
更优雅
 */
class Question_9 {
    void init(int[][] res) {
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[i].length; j++) {
                res[i][j] = -1;
            }
        }
    }

    public int[][] getNearLessNoRepeat(int[] arr) {
        int[][] res = new int[arr.length][2];
        init(res);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                Integer idx = stack.pop();
                res[idx][1] = arr[i];
                if (!stack.isEmpty()) {
                    res[idx][0] = arr[stack.peek()];
                }
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            Integer idx = stack.pop();
            if (!stack.isEmpty()) {
                res[idx][0] = stack.peek();
            }
        }
        return res;
    }
}

/*
数组有重复值的官方解
Stack的结构是一个List
 */
class Question_8_Slove2 {
    void init(int[][] res) {
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[i].length; j++) {
                res[i][j] = -1;
            }
        }
    }

    int[][] getNearLess(int[] arr) {
        int[][] res = new int[arr.length][2];
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            int index = i;
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
                //出栈
                List<Integer> indexs = stack.pop();
                indexs.stream().forEach(idx -> res[idx][1] = index);
                if (!stack.isEmpty()) {
                    indexs.stream().forEach(idx -> {
                        List<Integer> ids = stack.peek();
                        res[idx][0] = ids.get(ids.size() - 1);
                    });
                }
            }
            //压栈
            if (!stack.isEmpty()) {
                Integer top = stack.peek().get(0);
                if (arr[top] == arr[i]) {
                    stack.peek().add(i);
                } else {
                    stack.push(new ArrayList<Integer>() {{
                        add(index);
                    }});
                }
            } else {
                stack.push(new ArrayList<Integer>() {{
                    add(index);
                }});
            }
        }
        return res;
    }
}