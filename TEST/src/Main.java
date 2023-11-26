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

        TreeSet<Integer>set=new TreeSet<>();
        set.add(4);
        set.add(1);
        set.add(18);
        set.add(3);
        set.add(13);
        System.out.println(set);
        System.out.println(set.ceiling(3));
        System.out.println(set.ceiling(4));
        System.out.println(set.ceiling(5));
        System.out.println(set.higher(4));
        set.pollLast();
    }
}
