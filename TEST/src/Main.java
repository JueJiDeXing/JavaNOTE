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
    void printTreeNode(TreeNode root) {
        System.out.print(root.val + ", ");
        if (root.left != null) {
            printTreeNode(root.left);
        }
        if (root.right != null) {
            printTreeNode(root.right);
        }
    }

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

    /**
     传入由"[]"包裹的二维数组字符串转换后返回
     int[][]array=change("[[1,2],[3,4]]") 与 int[][]array = new int[][]{{1,2},{3,4}}等效
     */
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

    public static void main(String[] args) {
         System.out.printf("%6b\n",(1>2));
    }
}
