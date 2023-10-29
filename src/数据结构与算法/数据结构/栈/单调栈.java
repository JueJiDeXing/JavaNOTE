package 数据结构与算法.数据结构.栈;

import java.util.LinkedList;

public class 单调栈 {
}


class _42接雨水 {
    static class Data {
        int height;
        int index;

        public Data(int height, int index) {
            this.height = height;
            this.index = index;
        }

        @Override
        public String toString() {
            return "Data{" + "height=" + height + ", index=" + index + '}';
        }
    }

    /*
    给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     */
    //维护一个单调递减栈,需要弹出,说明有凹陷,需要计算水容量
    public int trap(int[] height) {
        LinkedList<Data> stack = new LinkedList<>();
        int res = 0;
        for (int i = 0; i < height.length; i++) {
            Data right = new Data(height[i], i);//当前要放入的柱子,高度为0也算
            while (!stack.isEmpty() && stack.peek().height < right.height) {
                Data pop = stack.pop();//凹陷柱
                Data left = stack.peek();//盛水柱(左)
                if (left != null) {
                    int w = right.index - left.index - 1;//宽
                    int h = Math.min(left.height, right.height) - pop.height;//高
                    res += w * h;
                }
            }
            stack.push(right);
        }
        return res;
    }
}
