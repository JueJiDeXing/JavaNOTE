import java.util.List;

public class main2 {
    public int longestValidSubstring2(String word, List<String> forbidden) {
        int res = 0;
        int i = 0, j = 1;
        int len = word.length();
        while (j <= len) {
            boolean isValid = true;
            for (String fb : forbidden) {
                if (kmp2(word.substring(i, j), fb) != -1) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                res = Math.max(res, j - i);
            } else {
                i++;
            }
            j++;
        }
        return res;
    }

    public int kmp2(String haystack, String needle) {
        char[] origin = haystack.toCharArray();//原始
        char[] pattern = needle.toCharArray();//模式
        int[] next = getNext2(pattern);
        int i = 0, j = 0;
        while (pattern.length - j <= origin.length - i) {//优化,模式串长度不足时提前跳出
            if (pattern[j] == origin[i]) {//匹配成功,匹配下一位
                i++;
                j++;
                if (j == pattern.length) {//全部匹配
                    return i - j;
                }
            } else if (j == 0) {//第0位不匹配
                i++;
            } else {//在某处不匹配跳过最长前后缀
                j = next[j - 1];
            }
        }
        return -1;
    }

    private int[] getNext2(char[] pattern) {
        int[] next = new int[pattern.length];//最终的返回值,next[k-1]表示索引k匹配失败时,指针的跳转索引位置
        next[0] = 0;//第一位匹配失败,位置仍为0
        int i = 1;//索引i为最长相同前后缀的后缀终点,i为当前比较位置
        int j = 0;//索引0~j-1为前缀,j为当前比较的位置
        while (i < pattern.length) {
            if (pattern[i] == pattern[j]) {//该位相同
                next[i] = j + 1;//递推关系,0~j-1一直保持为相同的前缀,最长前后缀长度在上一个基础上加1
                i++;//ij后移比较下一位
                j++;
            } else if (j == 0) {//遇到不同字符,前面没有相同部分(j=0),该位为0,i后移
                i++;
            } else {//遇到不同字符,前面有相同部分,j往回找,i不动
                j = next[j - 1];//next数组中存储了跳转信息(与kmp主方法类似)
            }
        }
        return next;
    }
}
