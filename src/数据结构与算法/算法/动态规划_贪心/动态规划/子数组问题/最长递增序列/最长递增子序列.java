package 数据结构与算法.算法.动态规划_贪心.动态规划.子数组问题.最长递增序列;

import java.util.*;

/**
 求真正序列,而非长度
 */
public class 最长递增子序列 {
    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(new 最长递增子序列().LIS(nums));//2 3 7 18
    }

    public List<Integer> LIS(int[] nums) {
        if (nums.length == 0) return new ArrayList<>();

        List<List<Integer>> results = new ArrayList<>();
        results.add(List.of(nums[0]));
        for (int i = 1; i < nums.length; i++) {
            update(results, nums[i]);
        }
        return results.get(results.size() - 1);
    }

    private void update(List<List<Integer>> results, int num) {
        //寻找最长的可以在后面拼接num的子序列
        int left = 0, right = results.size()-1;
        int pos = -1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            List<Integer> line = results.get(mid);
            int tail = line.get(line.size() - 1);//当前一行的最后一个数字(当前子序列的末位)
            if (tail < num) {
                pos = left;
                left = mid +1 ;
            } else {
                right = mid-1;
            }
        }
        if (pos == -1) {//比所有行的行末数字都小
            results.set(0, List.of(num));
            return;
        }
        //行拼接num后覆盖下一行
        List<Integer> cover = new ArrayList<>(results.get(pos));
        cover.add(num);
        if (pos == results.size() - 1) {
            results.add(cover);
        } else {
            results.set(pos + 1, cover);//相同长度下的更优解
        }
    }
}
