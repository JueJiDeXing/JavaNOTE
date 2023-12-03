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

    static class Pair {
        String s;
        Integer p = 1;

        public Pair(String s, Integer p) {
            this.s = s;
            this.p = p;
        }

        public Pair(Integer p) {
            this.p = p;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "s='" + s + '\'' +
                    ", p=" + p +
                    '}';
        }
    }

    public static void main(String[] args) {

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
