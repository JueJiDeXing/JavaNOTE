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


    public static void main(String[] args) throws Exception {
        Main test = new Main();
        System.out.println(test.queensAttacktheKing(new int[][]{{0, 1}, {1, 0}, {4, 0}, {0, 4}, {3, 3}, {2, 4}}, new int[]{0, 0}));
    }

    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        int[][] rowQueens = new int[7][2], colQueens = new int[7][2];
        int[][] mainDiagonalQueens = new int[7][2], subDiagonalQueens = new int[7][2];
        int i1 = 0, i2 = 0, i3 = 0, i4 = 0;
        for (int[] pos : queens) {
            if (pos[1] == king[1]) {
                rowQueens[i1++] = pos;
            } else if (pos[0] == king[0]) {
                colQueens[i2++] = pos;
            } else if (pos[0] - pos[1] == king[0] - king[1]) {
                mainDiagonalQueens[i3++] = pos;
            } else if (pos[0] + pos[1] == king[0] + king[1]) {
                subDiagonalQueens[i4++] = pos;
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        //固定列
        outer1:
        for (int i = king[0] - 1; i >= 0; i--) {//向上
            for (int k = 0; k < i1; k++) {
                if (i == rowQueens[k][0]) {
                    List<Integer> r = new ArrayList<>();
                    r.add(i);
                    r.add(king[1]);
                    ans.add(r);
                    break outer1;
                }
            }
        }
        outer2:
        for (int i = king[0] + 1; i < 8; i++) {//向下
            for (int k = 0; k < i1; k++) {
                if (i == rowQueens[k][0]) {
                    List<Integer> r = new ArrayList<>();
                    r.add(i);
                    r.add(king[1]);
                    ans.add(r);
                    break outer2;
                }
            }
        }
        //固定行
        outer3:
        for (int i = king[1] - 1; i >= 0; i--) {//向左
            for (int k = 0; k < i2; k++) {
                if (i == colQueens[k][1]) {
                    List<Integer> r = new ArrayList<>();
                    r.add(king[1]);
                    r.add(i);
                    ans.add(r);
                    break outer3;
                }
            }
        }
        outer4:
        for (int i = king[1] + 1; i < 8; i++) {//向右
            for (int k = 0; k < i2; k++) {
                if (i == colQueens[k][1]) {
                    List<Integer> r = new ArrayList<>();
                    r.add(king[1]);
                    r.add(i);
                    ans.add(r);
                    break outer4;
                }
            }
        }
        //主对角线
        outer5:
        for (int i = king[0] - 1, j = king[1] - 1; i >= 0 && j >= 0; i--, j--) {//向左上
            for (int k = 0; k < i3; k++) {
                if (i == mainDiagonalQueens[k][0] && j == mainDiagonalQueens[k][1]) {
                    List<Integer> r = new ArrayList<>();
                    r.add(i);
                    r.add(j);
                    ans.add(r);
                    break outer5;
                }
            }
        }
        outer6:
        for (int i = king[0] + 1, j = king[1] + 1; i < 8 && j < 8; i++, j++) {//向右下
            for (int k = 0; k < i3; k++) {
                if (i == mainDiagonalQueens[k][0] && j == mainDiagonalQueens[k][1]) {
                    List<Integer> r = new ArrayList<>();
                    r.add(i);
                    r.add(j);
                    ans.add(r);
                    break outer6;
                }
            }
        }
        //副对角线
        outer7:
        for (int i = king[0] + 1, j = king[1] - 1; i < 8 && j >= 0; i++, j--) {//向右上
            for (int k = 0; k < i4; k++) {
                if (i == subDiagonalQueens[k][0] && j == subDiagonalQueens[k][1]) {
                    List<Integer> r = new ArrayList<>();
                    r.add(i);
                    r.add(j);
                    ans.add(r);
                    break outer7;
                }
            }
        }
        outer8:
        for (int i = king[0] - 1, j = king[1] + 1; i >= 0 && j < 8; i--, j++) {//向左下
            for (int k = 0; k < i4; k++) {
                if (i == subDiagonalQueens[k][0] && j == subDiagonalQueens[k][1]) {
                    List<Integer> r = new ArrayList<>();
                    r.add(i);
                    r.add(j);
                    ans.add(r);
                    break outer8;
                }
            }
        }
        return ans;
    }


}
