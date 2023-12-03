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
        Main test = new Main();
        System.out.println(test.maxScore(new int[]{1, 2, 3, 4, 5, 6, 1}, 3));
    }

    //连续子数组最小和
    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length - k;
        int sum = 0;
        for (int p : cardPoints) {
            sum += p;
        }
        if (n == 0) return sum;
        return sum - minSum(cardPoints, n);
    }

    public int minSum(int[] arr, int n) {
        int min = Integer.MAX_VALUE;
        int[] prev = new int[arr.length + 1];
        prev[0] = 0;
        for (int i = 1; i <= arr.length; i++) {
            prev[i] = arr[i - 1] + prev[i - 1];
        }
        for (int i = n - 1; i < arr.length; i++) {
            int sum = prev[i] - prev[i - n];
            if (sum < min) {
                min = sum;
            }
        }
        return min;
    }
}
