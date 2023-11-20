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

        //String[] tokens = new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        //String s = "";
        //int[][] matrix = new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        //Set<Integer> set = new HashSet<>();
        //StringBuilder stringBuilder = new StringBuilder(10);    }
        //Main test = new Main();
        //ListNode head1 = new ListNode(7, new ListNode(2, new ListNode(4, new ListNode(3))));
        //ListNode head2 = new ListNode(5, new ListNode(6, new ListNode(4)));
        //ListNode head3 = test.addTwoNumbers(head1, head2);
        //System.out.println(head3);
        //System.out.println(test.isAdditiveNumber("199100199"));
        //System.out.println(test.isAdditiveNumber("112358"));
    }
}
