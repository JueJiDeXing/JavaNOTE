package 数据结构与算法.算法.深搜_广搜.深度优先.排列组合问题;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class _40组数总和2 {
    // 每个数字只能使用1次
    // 给出的集合可能存在重复元素,结果不能出现重复的组合
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);//排序,使重复元素相邻
        dfs(candidates, target, 0, new LinkedList<>(), new boolean[candidates.length]);
        return res;
    }

    /**
     @param candidates 全集
     @param remainder  剩余量
     @param start      起始组合索引
     @param stack      栈
     @param isUsed     记录是否使用
     */
    public void dfs(int[] candidates, int remainder, int start, LinkedList<Integer> stack, boolean[] isUsed) {
        if (remainder < 0) {//无解
            return;
        }
        if (remainder == 0) {//找到解
            res.add(new ArrayList<>());
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            int candidate = candidates[i];
            if (remainder < candidate) {//剪枝
                continue;
            }
            if (i > 0 && candidate == candidates[i - 1] && !isUsed[i - 1]) {
                //遇到重复元素,必须先使用前面的才能使用后面重复的
                continue;
            }
            //深搜_广搜
            stack.push(candidate);
            isUsed[i] = true;
            dfs(candidates, remainder - candidate, i + 1, stack, isUsed);//start从i+1开始,一个数字不能重复使用
            isUsed[i] = false;
            stack.pop();
        }
    }
}
