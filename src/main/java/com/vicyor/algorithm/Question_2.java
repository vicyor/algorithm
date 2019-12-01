package com.vicyor.algorithm;

import java.util.Stack;

/*
    用二个栈实现队列
    支持队列的 add,poll,peek操作
    这题没有时间复杂度的约束,所以你懂的
 */
public class Question_2 {

}

//我的解 仅逻辑
class Question_2_MySlove {
    Stack<Integer> s1 = new Stack();
    Stack<Integer> s2 = new Stack();

    void add(Integer num) {
        s1.add(num);
        s2.clear();
        synchronized (s1) {
            s1.forEach(s2::push);
        }
    }

    Integer peek() {
        return s2.peek();
    }

    Integer pop() {
        Integer ret = s2.pop();
        s1.clear();
        synchronized (s2) {
            s2.forEach(s1::push);
        }
        return ret;
    }
}
