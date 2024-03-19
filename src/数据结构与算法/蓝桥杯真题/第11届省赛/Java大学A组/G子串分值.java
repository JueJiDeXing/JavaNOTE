package 数据结构与算法.蓝桥杯真题.第11届省赛.Java大学A组;

import java.util.*;

/**
 已AC
 */
public class G子串分值 {
    /*
    f(str)表示str中只出现一次的字母的个数,str由小写字母组成
    给定字符串S,求S的所有子串s的f(s)之和
     */

    /**
     对每个字母求贡献<br>
     字符S[i],假设左边离它left距离没有S[i],右边离它right距离没有S[i]<br>
     对于仅包含一个S[i]的子串,S[i]左边可选择长度为[0,left],有left+1个选择,右侧同理有right+1个选择<br>
     根据组合计数,ans+=(left+1)*(right+1)<br>
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] s = sc.next().toCharArray();
        int ans = 0;
        for (int i = 0; i < s.length; i++) {
            int left = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (s[j] == s[i]) break;
                left++;
            }
            int right = 0;
            for (int j = i + 1; j < s.length; j++) {
                if (s[j] == s[i]) break;
                right++;
            }
            ans += (left + 1) * (right + 1);
        }
        System.out.println(ans);

    }
}
