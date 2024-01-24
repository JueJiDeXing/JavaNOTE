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
        System.out.println(test.countSymmetricIntegers(1200, 1230));
    }

    public int countSymmetricIntegers(int low, int high) {
        int ans=0;
        for(int i=low;i<=high;i++){
            int a=-1,b=-2;
            if(i<=99){
                a=i/10;
                b=i%10;
            } else if(i>=1000&&i<=9999){
                a=i/100;
                b=i%100;
            }
            if(a==b){
                ans++;
            }
        }
        return ans;

    }
}



