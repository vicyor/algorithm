package com.vicyor.algorithm;

import org.junit.Test;

import java.util.*;

//给定一个整形矩阵map,其中值为0和1两种,求其中全是1的所有区域中,最大的矩形区域为1的数量.
/*
        map= 1 0 1 1
             1 1 1 1
             1 1 1 0
 */
public class Question_9 {
    private static int[][] map = {{1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 0}};

    public static void main(String[] args) {
        Question_9_MySlove slove = new Question_9_MySlove();
        int res = slove.getSlove(map);
        System.out.println(res);
    }
}

/*
先计算出来行,根据行去算列
 */
class Question_9_MySlove {
    public int getSlove(int[][] map) {
        //int [] 自动赋值为0
        int[] height = new int[map[0].length];
        //从上到下遍历
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                height[j] = map[i][j] == 0 ? 0 : height[j] + 1;
            }
        }
        //接下就是对height[j]遍历,在height数组中找比height[j]小且位置最近的两个
        //单调栈结构
        int[][] res = monotonicStack(height);
        int[] area = new int[height.length];
        //计算结果 比如  leftMin-i-rightMin  --->are= (leftMin,i] U[i,rightMin) *height[i]
        for (int i = 0; i < height.length; i++) {
            int left = res[i][0];
            int right = res[i][1];
            int length = 0;
            if (left != -1) {
                //不带上比height[i]小的呢个
                length += i - left;
            }else{
                length+=i+1;
            }
            if (right != -1) {
                //不带上比height[i]小的呢个，i在左边已算过 -1
                length += right - i-1;
            }else{
                length+=height.length-1-i+1-1;
            }
            area[i] = height[i] * length;
        }
        OptionalInt max = Arrays.stream(area).max();
        return max.getAsInt();
    }

    public void init(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = -1;
            }
        }
    }

    //可重复的单调栈问题
    public int[][] monotonicStack(int[] height) {
        int[][] res = new int[height.length][2];
        init(res);
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < res.length; i++) {
            int index = i;
            //单调递增栈，确保遍历的元素必须压栈.在元素弹出时更新左右
            while (!stack.isEmpty() && height[stack.peek().get(0)] > height[i]) {
                List<Integer> ids = stack.pop();
                ids.stream().forEach(id -> {
                    res[id][1] = index;
                    if (!stack.isEmpty()) {
                        //更新左边
                        res[id][0] = stack.peek().get(stack.peek().size()-1);
                    }
                });
            }
            //压栈
            if (stack.isEmpty()) {
                stack.push(new ArrayList<Integer>() {{
                    add(index);
                }});
            } else {
                //重复值压栈
                List<Integer> ids = stack.peek();
                Integer id = ids.get(0);
                if (height[id] == height[i]) {
                    ids.add(i);
                } else {
                    stack.push(new ArrayList<Integer>() {{
                        add(index);
                    }});
                }
            }
        }
        while(!stack.isEmpty()){
            List<Integer> ids = stack.pop();
            ids.forEach(id->{
                if(!stack.isEmpty()){
                    res[id][0]=stack.peek().get(stack.peek().size()-1);
                }
            });
        }
        return res;
    }
}