import java.util.*;

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
    List<Integer> __;
    ArrayList<Integer> ___;
    Map<Integer, Integer> ____;
    HashMap<Integer, Integer> _____;
    TreeSet<Integer> ______;


    public static void main(String[] args) {
        Main test = new Main();
        System.out.println(4 ^ 5 ^ 13 ^ 12);
        int[] arr = {13, 2, 8, 6, 16, 4, 12, 15, 11, 14, 1, 7, 3, 9, 5, 10};
        int[][] mat = {
                {1, 3, 6, 16},
                {14, 7, 11, 10},
                {4, 5, 13, 12},
                {15, 2, 8, 9}
        };
        //System.out.println(test.firstCompleteIndex(arr, mat));
    }

    public int robotSim(int[] commands, int[][] obstacles) {
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int currX = 0, currY = 0, d = 1;
        //存储障碍物
        Set<Integer> set = new HashSet<>();
        for (int[] obstacle : obstacles) {
            set.add(obstacle[0] * 60001 + obstacle[1]);//行坐标乘六万,用一个数字存储位置信息
        }
        int res = 0;//记录最大距离
        for (int command : commands) {
            //转向指令
            if (command < 0) {
                d += command == -1 ? 1 : -1;
                d %= 4;
                if (d < 0) {
                    d += 4;
                }
                continue;
            }
            //前进指令
            int dx = dirs[d][0];//前进方向
            int dy = dirs[d][1];
            for (int i = 0; i < command; i++) {//前进command步
                //如果下一个位置遇到障碍物则停止前进
                if (set.contains((currX + dx) * 60001 + currY + dy)) {
                    break;
                }
                //前进
                currX += dx;
                currY += dy;
            }
            res = Math.max(res, currX * currX + currY * currY);
        }
        return res;
    }
}
