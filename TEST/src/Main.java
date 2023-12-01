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
        System.out.println(test.cal(new int[][]{
                {1, 2, 3, 4},
                {2, 3, 4, 6},
                {6, 5, 4, 3}
        }));
    }

    /**
     <h1>暴力枚举</h1>
     对于一支股票,要画在一幅图上,它需要与这幅图上的所有股票都保持严格大于或严格小于的关系
     遍历所有图,判断当前股票是否能插入到图中,如果不能插入则新建一幅图,图数+1

     @param mat n支股票
     @return 股票最少画多少幅图
     */
    public int cal(int[][] mat) {
        int n = mat.length;//n支股票
        List<Integer>[] map = new ArrayList[n];//map[i]表示第i幅图画了哪些股票
        List<Integer> first = new ArrayList();//第一幅图
        first.add(0);//画第1支(索引0)股票
        map[0] = first;//第一幅图
        int count = 1;//图的数量
        for (int i = 1; i < n; i++) {
            int[] s = mat[i];
            boolean canInsert = false;
            //将股票mat[i]插在哪幅图上
            outer:
            for (List<Integer> m : map) {
                if (m == null) continue;
                for (Integer index : m) {//图m是否与第i支股票没有交叉
                    if (!isStrictly(s, mat[index])) {
                        //如果某支股票不是严格大于或严格小于的,则判断下一幅图
                        continue outer;
                    }
                }
                //没有交叉,图m可以画第i支股票
                m.add(i);
                canInsert = true;
                break;
            }
            if (!canInsert) {//不能画
                //新建一幅图
                List<Integer> newMap = new ArrayList<>();
                newMap.add(i);
                map[count++] = newMap;
            }
        }
        return count;//返回图的数量
    }

    /**
     是否严格大于或严格小于
     */
    private boolean isStrictly(int[] s1, int[] s2) {
        if (s1[0] > s2[0]) {
            for (int i = 0; i < s1.length; i++) {
                if (s1[i] < s2[i]) {
                    return false;
                }
            }
        } else {
            for (int i = 0; i < s1.length; i++) {
                if (s1[i] > s2[i]) {
                    return false;
                }
            }
        }
        return true;
    }
}
