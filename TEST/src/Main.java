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

    public static int findKOr(int[] nums, int k) {
        int[] bit = new int[32];
        for (int n : nums) {
            int idx = 0;
            while (n > 0) {
                if ((n & 1) == 1) {
                    bit[idx]++;
                }
                n >>= 1;
                idx++;
            }
        }
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if (bit[i] >= k) {
                ans += 1 << i;
            }
        }
        return ans;

    }


    public static boolean consecutiveSqrt(double x, int n, double eps) {
        double cvx1 = x;
        for (int i = 0; i < n; i++) {
            cvx1 = Math.sqrt(cvx1);
        }
        for (int i = 0; i < n; i++) {
            cvx1 = Math.pow(cvx1, 2);
        }
        return Math.abs(cvx1 - x) >= eps;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        double eps = scanner.nextDouble();
        for (int i = 2; i <= N; i++) {
            int k = 1;
            while (true) {
                if (consecutiveSqrt(i, k, eps)) {
                    System.out.println(i + "   " + k);
                    break;
                }
                ++k;
            }
        }
        scanner.close();
    }
    /*
    用例1：
输入
20 2.45e-10

输出
2 22 3 20 4 20 5 19 6 20 7 21 8 20 9 19 10 19 11 20 12 19 13 19 14 18 15 18 16 18 17 19 18 17 19 19 20 18

用例2：
输入
20 7.45e-12

输出
2 16 3 15 4 14 5 14 6 14 7 16 8 15 9 13 10 19 11 13 12 12 13 14 14 15 15 14 16 14 17 15 18 12 19 12 20 12

用例3：
输入
40 4.5e-15

输出
2 7 3 4 4 5 5 5 6 4 7 3 8 4 9 3 10 4 11 3 12 3 13 3 14 4 15 2 16 3 17 2 18 6 19 4 20 2 21 2 22 3 23 6 24 2 25 2 26 3 27 2 28 2 29 2 30 2 31 2 32 1 33 2 34 4 35 2 36 2 37 1 38 1 39 3 40 1

用例4：
输入
80 4.5e-7

输出
2 32 3 30 4 33 5 29 6 35 7 29 8 32 9 29 10 28 11 29 12 32 13 30 14 29 15 28 16 34 17 32 18 29 19 29 20 28 21 29 22 29 23 35 24 28 25 29 26 28 27 27 28 29 29 28 30 28 31 28 32 32 33 29 34 28 35 28 36 28 37 30 38 29 39 28 40 27 41 29 42 29 43 29 44 26 45 27 46 32 47 27 48 32 49 26 50 26 51 26 52 27 53 28 54 28 55 29 56 27 57 29 58 30 59 29 60 27 61 26 62 26 63 27 64 28 65 28 66 27 67 29 68 29 69 26 70 26 71 28 72 27 73 27 74 27 75 27 76 27 77 28 78 25 79 26 80 36

用例5：
输入
87 1.5e-4

输出
2 41 3 43 4 39 5 39 6 41 7 40 8 38 9 37 10 38 11 39 12 37 13 42 14 37 15 37 16 40 17 38 18 36 19 37 20 40 21 38 22 36 23 41 24 37 25 40 26 36 27 39 28 35 29 37 30 37 31 38 32 36 33 37 34 36 35 35 36 36 37 37 38 37 39 37 40 36 41 36 42 40 43 38 44 36 45 36 46 38 47 35 48 37 49 35 50 35 51 39 52 34 53 37 54 38 55 36 56 36 57 36 58 34 59 36 60 37 61 34 62 39 63 36 64 36 65 36 66 34 67 34 68 34 69 36 70 36 71 37 72 36 73 36 74 34 75 37 76 37 77 34 78 35 79 34 80 36 81 33 82 35 83 34 84 38 85 36 86 41 87 35
     */
}


