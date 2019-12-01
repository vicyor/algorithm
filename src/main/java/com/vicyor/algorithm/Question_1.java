package com.vicyor.algorithm;

import java.util.Stack;

/**
 * 题目: 实现一个特殊的栈,在栈的基本功能的基础上,实现返回最小的元素
 * getMin的时间复杂度为O(1)
 */
public class Question_1 {

}

/*
    官方解 1：
      用2个栈,
      getMin 原理
      a ------> b ------>c
      即使b到c的元素弹出了,但是a-b的最小值依然保存在栈中
 */
class Question_1_S1 {
    //数据栈
    Stack<Integer> data = new Stack();
    //最小栈
    Stack<Integer> min = new Stack();

    void push(Integer num) {
        data.push(num);
        if (min.empty() || min.peek() >= num) {
            min.push(num);
        }
    }

    Integer pop() {
        Integer pop = null;
        if (!data.empty()) {
            pop = data.pop();
            if (min.peek().equals(pop)) {
                min.pop();
            }
        }
        return pop;
    }

    Integer getMin() {
        return min.empty() ? null : min.peek();
    }
}