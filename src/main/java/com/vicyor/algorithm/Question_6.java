package com.vicyor.algorithm;

/*
    Tower of Hanoi
    一共三个柱子,将A柱子的64个圆盘都移动到C柱子上
    规则:
        1.一次只能移动一个圆盘
        2.大盘子不能移动到小盘子上
        3.A,C上的圆盘不能相互移动
 */
/*
用递归写的
无论如何移动的都是最上面的盘子
最后移动的是唯一的盘子,即是最上,亦是最下
基于栈的看起来太难了......
 */
public class Question_6 {
    public static void main(String[] args) {
        Question_6_Slove1 slove1=new Question_6_Slove1();
        int part = slove1.process(8, "left", "mid", "right", "left", "mid");
        System.out.println(part);
    }
}

//伪代码
class Question_6_PseudoCode {

    void recursive(int n, int from, int temp, int to) {
        if (n == 1) {
            //小盘子
            System.out.println(String.format("将%d从第%d塔移动到第%d塔", n, from, to));
            return;
        }
        recursive(n - 1, from, temp, to);
        //大盘子
        System.out.println(String.format("将%d从第%d塔移动到第%d塔", n, from, temp));
        recursive(n - 1, to, from, temp);
    }
}

//书上的解法一
class Question_6_Slove1 {
    public int process(int num, String left, String mid, String right, String from, String to) {
        if (num == 1) {
            if (from.equals(mid) || to.equals(mid)) {
                System.out.println("Move 1 from " + from + " to " + to);
                return 1;
            } else {
                System.out.println("Move 1 from " + from + " to " + mid);
                System.out.println("Move 1 from " + mid + " to " + to);
                return 2;
            }
        }
        if (from.equals(mid) || to.equals(mid)) {
            String another = (from.equals(left) || to.equals(left)) ? right : left;
            int part1 = process(num - 1, left, mid, right, from, another);
            int part2 = 1;
            System.out.println("Move " + num + " from " + from + " to " + to);
            int part3 = process(num - 1, left, mid, right, another, to);
            return part1 + part2 + part3;
        } else {
            int part1 = process(num - 1, left, mid, right, from, to);
            int part2 = 1;
            System.out.println("Move " + num + " from " + from + " to " + mid);
            int part3 = process(num - 1, left, mid, right, to, from);
            int part4 = 1;
            System.out.println("Move " + num + " from " + mid + " to " + to);
            int part5 = process(num - 1, left, mid, right, from, to);
            return part1 + part2 + part3 + part4 + part5;
        }
    }
}