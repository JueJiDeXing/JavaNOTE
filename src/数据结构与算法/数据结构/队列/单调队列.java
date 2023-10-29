package 数据结构与算法.数据结构.队列;

import java.util.LinkedList;

public class 单调队列 {
}

class MonotonicQueue {
    LinkedList<Integer> dequeue = new LinkedList<>();

    public MonotonicQueue() {

    }

    public Integer peek() {
        return dequeue.peekFirst();
    }

    public Integer poll() {
        return dequeue.pollFirst();
    }

    public void offer(Integer t) {
        while (!dequeue.isEmpty() && dequeue.peekLast() < t) {
            dequeue.pollLast();
        }
        dequeue.offerLast(t);
    }

    @Override
    public String toString() {
        return dequeue.toString();
    }
}

class _239滑动窗口最大值 {
    /*
    给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
    你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
    返回 滑动窗口中的最大值 。
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        int[] res = new int[len - k + 1];
        MonotonicQueue queue = new MonotonicQueue();//单调队列
        for (int i = 0; i < len; i++) {
            if (i >= k && queue.peek() == nums[i - k]) {
                //检查队头元素是否超过滑动窗口范围
                queue.poll();
            }
            queue.offer(nums[i]);
            if (i >= k - 1) {
                res[i - k + 1] = queue.peek();
            }
        }
        return res;

    }
}
