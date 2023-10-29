package 数据结构与算法.算法.leetcode;

public class 匹配字符串 {
    public static void main(String[] args) {
        String stones = "aA";
        String jewels = "aAAbbbb";
        char[] st = stones.toCharArray();
        char[] je = jewels.toCharArray();
        int[] s = new int[52];
        int[] j = new int[52];
        //存储字母表中各字母个数
        letTableCount(st, je, s, j);
        int count = 0;
        for (int i = 0; i < 52; i++) {
            if (s[i] != 0 && j[i] != 0) {
                count += s[i];
            }
        }
        System.out.println((char)65);
    }

    private static void letTableCount(char[] st, char[] je, int[] s, int[] j) {
        for (char c : st) {
            if ((int) c >= 97) {
                s[c - 97]++;
            } else {
                s[c - 65 + 26]++;
            }
        }
        for (char c : je) {
            if ((int) c >= 97) {
                j[c - 97]++;
            } else {
                j[c - 65 + 26]++;
            }
        }
    }
}
