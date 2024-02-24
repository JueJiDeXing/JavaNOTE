package 基础.集合.单列集合Collection.Set.TreeSet;

import java.util.TreeSet;

public class TreeSet_220存在重复元素III {
    /*
    给你一个整数数组 nums 和两个整数 indexDiff 和 valueDiff 。

    找出满足下述条件的下标对 (i, j)：

    i != j,
    abs(i - j) <= indexDiff
    abs(nums[i] - nums[j]) <= valueDiff
    如果存在，返回 true ；否则，返回 false 。
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        // 滑动窗口,如果使用队列维护,每次都要检查队列里的元素是否满足,时间是O(kn)
        // 所以需要一种数据结构,支持添加和删除元素的操作,并且需要是有序的,支持二分查找降低时间复杂度
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            Integer ceiling = set.ceiling(nums[i] - valueDiff);//找一个大于底线的最小值
            if (ceiling != null && ceiling - valueDiff <= nums[i]) {//有这个值,并且这个值小于顶线
                return true;
            }
            //添加到集合
            set.add(nums[i]);
            if (i >= indexDiff) {//移除前面越界的(不用考虑元素的重复,因为在for循环里如果是重复的会返回true)
                set.remove(nums[i - indexDiff]);
            }
        }

        return false;
    }
}
