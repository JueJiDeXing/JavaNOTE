package 数据结构与算法.算法.深搜_广搜.深度优先.排列组合问题;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class _15三数之和 {
    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> stack = new LinkedList<>();

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        dfs(nums, 3, 0, 0, nums.length - 1);
        return res;
    }

    public void dfs(int[] nums, int n, int target, int start, int end) {
        if (n == 2) {//两数之和
            twoSum(nums, start, end, target);
            return;
        }
        for (int k = start; k < end; k++) {
            if (k > start && nums[k] == nums[k - 1]) continue;
            stack.push(nums[k]);
            dfs(nums, n - 1, target - nums[k], k + 1, end);
            stack.pop();
        }
    }

    public void twoSum(int[] nums, int i, int j, int target) {
        while (i < j) {
            int sum = nums[i] + nums[j];
            if (sum < target) {
                i++;
            } else if (sum > target) {
                j--;
            } else {//找到解
                List<Integer> result = new ArrayList(stack);
                result.add(nums[i]);
                result.add(nums[j]);
                res.add(result);
                i++;//继续搜索
                j--;
                while (i < j && nums[i] == nums[i - 1]) i++;//去重
                while (i < j && nums[j] == nums[j + 1]) j--;
            }
        }
    }

}
