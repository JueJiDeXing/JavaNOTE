package 数据结构与算法.算法.二分查找;

public class _162寻找峰值 {
    /**
     <h1>「二段性」</h1>
     二分的本质是「二段性」而非「单调性」<br>
     「二段性」还能继续细分，不仅仅只有满足 01 特性（满足/不满足）的「二段性」可以使用二分，<br>
     满足 1? 特性（一定满足/不一定满足）也可以二分。<br>
     <ul>
     <li>
     <b>1. 对于任意数组一定存在峰值:</b><br>
     若 数组长度为 1，由于边界看做负无穷，此时峰值为该唯一元素的下标；:<br>
     若数组长度大于 1，从最左边的元素 nums[0] 开始出发考虑：:<br>
     如果 nums[0] > nums[1]，那么最左边元素 nums[0] 就是峰值（结合左边界为负无穷）；:<br>
     如果 nums[0] < nums[1]，将 nums[0] 看做边界， nums[1] 看做新的最左侧元素，继续往右进行分析:<br>
     </li>
     <li>
     <b>2.二分不会错过峰值:</b><br>
     nums[x]>nums[x−1]的位置，x 的右边(含x)一定存在峰值；:<br>
     或对于一个满足 nums[x]>nums[x+1] 的位置，x 的左边(含x)一定存在峰值</li>
     </ul>
     */
    public int findPeakElement(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (nums[mid] > nums[mid + 1]) r = mid;
            else l = mid + 1;
        }
        return r;
    }
}

