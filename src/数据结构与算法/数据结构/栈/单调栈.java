package 数据结构与算法.数据结构.栈;

import java.util.Deque;
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

class _907子数组的最小值之和 {
    int MOD = 1000000007;

    public int sumSubarrayMins_(int[] arr) {
        return (int) sumMins(arr, 0, arr.length - 1);
    }

    public long sumMins(int[] arr, int left, int right) {
        if (left == right) return arr[left];
        int min = arr[left];
        int minIndex = left;
        for (int i = left + 1; i <= right; i++) {
            if (arr[i] < min) {
                min = arr[i];
                minIndex = i;
            }
        }
        long sum = (long) min * (minIndex - left + 1) % MOD * (right - minIndex + 1) % MOD;
        long n1 = 0, n2 = 0;
        if (minIndex != left) {
            n1 = sumMins(arr, left, minIndex - 1);
        }
        if (minIndex != right) {
            n2 = sumMins(arr, minIndex + 1, right);
        }
        return ((n1 + n2) % MOD + sum) % MOD;
    }

    // 重写根据下标取值方法，-1和n返回MIN_VALUE
    private int getElement(int[] arr, int n, int i) {
        if (i == -1 || i == n) {
            return Integer.MIN_VALUE;
        }
        return arr[i];
    }

    public int sumSubarrayMins(int[] arr) {
        // 处理边界情况
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        long ans = 0;
        Deque<Integer> stack = new LinkedList<>();
        // 将下标-1和n作为两个哨兵元素，它们对应的元素为MIN_VALUE
        // -1作为最左边界，n作为最右边界
        for (int i = -1; i <= n; i++) {
            // 向左寻找第一个小于等于A[i]的元素
            while (!stack.isEmpty() && getElement(arr, n, stack.peek()) > getElement(arr, n, i)) {
                // A[cur]就是之前思路中的A[i]，注意区分和上面代码的区别
                // 对于每个出栈元素来说，i就是它们的右边界，而栈顶元素就是左边界
                int cur = stack.pop();
                // 计算贡献值
                ans = (ans + (long) (cur - stack.peek()) * (i - cur) * arr[cur]) % MOD;
            }
            stack.push(i);
        }
        return (int) ans;
    }
}
