import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        ListNode p = this;
        StringBuilder sb = new StringBuilder();
        while (p != null) {
            sb.append(p.val).append("->");
            p = p.next;
        }
        sb.append("null");
        return sb.toString();
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class Main {
    List<Integer> LLLLLLLLLLL;
    ArrayList<Integer> ALALALALALAL;
    Map<Integer, Integer> MMMMMMMMM;
    HashMap<Integer, Integer> HHHHHHHH;
    Hashtable<Integer, Integer> HTHTHTHTHTHT;
    TreeMap<Integer, Integer> TMTMTMTMTMT;
    TreeSet<Integer> TSTSTSTSTS;
    LinkedList<Integer> LILILILILILI;
    StringBuilder SBSBSBSBSB;
    HashSet<Integer> HSHSHSHSHSHSHS;
    static Main test = new Main();

    public static int[][] change(String str) {
        String[] split = str.split("],\\[");
        int row = split.length;
        int col = 1;
        for (char ch : split[0].toCharArray()) {
            if (ch == ',') col++;
        }
        int[][] arr = new int[row][col];
        int currCol = 0, currRow = 0;
        for (char ch : str.toCharArray()) {
            if (ch == '[' || ch == ',' || ch == ']') continue;
            arr[currRow][currCol] = ch - '0';
            if (++currCol == col) {
                currCol = 0;
                currRow++;
            }

        }
        return arr;
    }

    public static void main(String[] args) {
        ConcurrentSkipListMap<Integer, Integer> map = new ConcurrentSkipListMap<>();

    }



}


class Solution {
    public static void main(String[] args) {
        Solution solution=new Solution();
        System.out.println(solution.minimumMountainRemovals(new int[]{100, 92, 89, 77, 74, 66, 64, 66, 64}));
    }
    public int minimumMountainRemovals(int[] nums) {
        int n = nums.length;

        int[] prev = new int[n];//以i结尾的最长递增子序列长度
        Arrays.fill(prev, 1);
        for (int i = 1; i < n; i++) {//第一个数字不需要再处理
            for (int j = 0; j < i; j++) {//第i个数字与0~i进行组合
                if (nums[i] > nums[j]) {//满足升序
                    prev[i] = Math.max(prev[j] + 1, prev[i]);
                }
            }
        }
        int[] suff = new int[n];//以i开头的最长递减子序列长度
        Arrays.fill(suff, 1);
        for (int i = n - 2; i >= 0; i--) {//第一个数字不需要再处理
            for (int j = n - 1; j > i; j--) {//第i个数字与i~n-1进行组合
                if (nums[i] > nums[j]) {//满足降序
                    suff[i] = Math.max(suff[j] + 1, suff[i]);
                }
            }
        }
        System.out.println(Arrays.toString(prev));
        System.out.println(Arrays.toString(suff));
        int ans = 0;
        for (int i = 1; i < n - 1; i++) {
            if (prev[i] == 1 || suff[i] == 1) continue;
            ans = Math.max(ans, prev[i] + suff[i] - 1);
        }

        return n-ans;
    }
}

