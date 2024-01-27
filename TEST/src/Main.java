import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        //统计行列数
        String[] split = str.split("],\\[");
        int row = split.length;
        int col = 1;
        for (char ch : split[0].toCharArray()) {
            if (ch == ',') col++;
        }

        //取出所有数字
        List<Integer> numbers = new ArrayList<>();
        Pattern pattern = Pattern.compile("-?\\d+"); // 匹配整数数字的正则表达式
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            String match = matcher.group();
            int number = Integer.parseInt(match);
            numbers.add(number);
        }
        //放入数组中
        int[][] arr = new int[row][col];
        int currCol = 0, currRow = 0;
        for (int num : numbers) {
            arr[currRow][currCol] = num;
            if (++currCol == col) {
                currCol = 0;
                currRow++;
            }
        }
        return arr;
    }


    public static void main(String[] args) throws IOException {
        int[][] edges = change("[[0,1],[1,2],[2,3],[3,4],[4,5]]");
        int[] coins = new int[]{1, 0, 0, 0, 0, 1};
        System.out.println(test.collectTheCoins(coins, edges));
    }

    public int collectTheCoins(int[] coins, int[][] edges) {
        int n = coins.length;//节点个数
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();//邻接矩阵
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        int[] degree = new int[n];//度表
        //建图
        for (int[] edge : edges) {
            degree[edge[0]]++;
            degree[edge[1]]++;
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        int[] temp_degree = degree.clone();
        //清除无硬币的叶子节点
        boolean f = true;
        while (f) {
            f = false;
            for (int i = 0; i < n; i++) {
                if (degree[i] == 1 && coins[i] == 0) {
                    f = true;
                    clearLeaf(n, graph, temp_degree, i);
                }
            }
            System.arraycopy(temp_degree, 0, degree, 0, n);
        }
        //把叶子节点再嘎两遍
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) {
                clearLeaf(n, graph, temp_degree, i);
            }
        }
        System.arraycopy(temp_degree, 0, degree, 0, n);
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) {
                clearLeaf(n, graph, temp_degree, i);
            }
        }
        System.arraycopy(temp_degree, 0, degree, 0, n);
        //剩余的节点都是必须走的,每条边经过两次,ans=(node-1)*2;
        int node = 0;
        for (int d : degree) {
            if (d != 0) node++;
        }
        return node == 0 ? 0 : (node - 1) * 2;
    }

    private void clearLeaf(int n,  ArrayList<ArrayList<Integer>>  graph, int[] degree, int i) {
        degree[i] = 0;
        ArrayList<Integer>edges= graph.get(i);
        for (int j = 0; j < edges.size(); j++) {
            if (edges.get(j) == 1) {
        }

    graph.get(i).set(j, 0);
                graph.get(j).set(i, 0);
                degree[j]--;
            }       for (int r = 0; r < n; r++) {
            if (graph.get(r).get(i) == 1) {
                graph.get(r).set(i, 0);
                graph.get(i).set(r, 0);
                degree[r]--;
            }
        }
    }

}



