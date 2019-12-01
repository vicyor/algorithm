package com.vicyor.algorithm;

import java.util.Random;
import java.util.Stack;

/*
    用一个栈实现另一个栈的排序
    顺序 从 大 到 小
 */
public class Question_5 {
    public static void main(String[] args) {
        Question_5_MySlove slove = new Question_5_MySlove();
        for (int i = 0; i < 10; i++) {
            slove.add(10 - i);
        }
        slove.reverse();
        slove.print();
    }

}

//我的解
class Question_5_MySlove {
    private Stack<Integer> s1 = new Stack<>();
    private Stack<Integer> s2 = new Stack<>();

    public void add(Integer item) {
        s1.add(item);
    }

    public void print() {
        while (!s1.empty()) {
            System.out.print(s1.pop());
        }
    }

    public void print2() {
        s2.forEach(System.out::print);
    }

    public void reverse() {
        while (!s1.empty()) {
            Integer s1p = s1.pop();
            Integer s2p = null;
            while (!s2.empty() && (s2p = s2.pop()) < s1p) {
                s1.push(s2p);
            }
            if (s2p!=null&&s2p>s1p)
                s2.push(s2p);
            s2.push(s1p);
        }
        while (!s2.empty()) {
            s1.push(s2.pop());
        }
    }
}
