package 数据结构与算法.算法.二分查找;

public class _33搜索旋转排序数组 {
    /*
    整数数组 nums 按升序排列，数组中的值 互不相同 。

    在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，
    使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
    例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。

    给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。

    你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
     */
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            int midNum = nums[mid], leftNum = nums[left], rightNum = nums[right];
            if (midNum == target) return mid;
            if (leftNum == target) return left;
            if (rightNum == target) return right;

            if (leftNum > rightNum) {//指针落在两段
                if (midNum < rightNum) {
                    if (midNum < target && target < leftNum) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                } else {
                    if (leftNum < target && target < midNum) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }
            } else {//正常一段升序
                if (target < midNum) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }
}
