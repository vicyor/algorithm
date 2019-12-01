package com.vicyor.algorithm;

import java.util.Stack;

/*
    如何用递归函数和栈操作来完成逆序一个栈
 */
public class Question_3 {
    public static void main(String[] args) {
    }
}

//我不会
//官方解
//将递归每一步抽象出来
class Question_3_Slove {
    Stack<Integer> stack = new Stack();

    void print() {
        while (!stack.empty()) {
            System.out.println(stack.pop());
        }
    }

    void push(Integer i) {
        stack.push(i);
    }

    //移除栈的最后一个元素并返回
    public Integer recursiveTask() {
        Integer item = stack.pop();
        if (stack.isEmpty()) {
            return item;
        } else {
            Integer last = recursiveTask();
            stack.push(item);
            return last;
        }
    }

    public void reverse() {
        Integer last = recursiveTask();
        reverse();
        stack.push(last);
    }
}
