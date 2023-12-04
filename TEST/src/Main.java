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

    }

    static class Pair {
        String s;
        Integer freq = 0;

        public Pair(String str) {
            s = str;
        }
    }

    Map<String, Pair> map = new HashMap<>();
    Map<Pair, Integer> treeMap = new TreeMap<>(Comparator.comparingInt(e -> e.freq));

    public void inc(String key) {
        Pair pair = map.computeIfAbsent(key, Pair::new);
        pair.freq++;
        map.put(key, pair);
        treeMap.put(pair, pair.freq);
    }
}
