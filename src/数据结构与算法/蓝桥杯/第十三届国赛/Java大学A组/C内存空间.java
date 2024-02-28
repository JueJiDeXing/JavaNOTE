package 数据结构与算法.蓝桥杯.第十三届国赛.Java大学A组;

import java.util.Scanner;

/**
 已AC
 */
public class C内存空间 {
    /*
    输入变量声明赋值语句,输出内存大小单位GB,MB,KB,B,优先用大单位表示
    //int:4 byte; long:8 byte; string:len byte
    //int[]:cnt*4 byte; long[]:cnt*8 byte
    例如:
    int a=1,b=2; 输出8B
    long[] nums=new long[131072]; 输出1MB
    String s1="hello",s2="world";输出10B
     */
    public static void main(String[] args) {
        main_enter();
    }

    private static void main_enter() {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        sc.nextLine();
        long bt = 0;
        for (int i = 0; i < T; i++) {
            bt += extracted(sc.nextLine());
        }
        System.out.println(change(bt));
    }

    private static String change(long bt) {
        StringBuilder res = new StringBuilder();
        int GB = (int) (bt / 1024 / 1024 / 1024);
        if (GB > 0) {
            bt -= (long) GB * 1024 * 1024 * 1024;
            res.append(GB).append("GB");
        }
        int MB = (int) (bt / 1024 / 1024);
        if (MB > 0) {
            bt -= (long) MB * 1024 * 1024;
            res.append(MB).append("MB");
        }
        int KB = (int) (bt / 1024);
        if (KB > 0) {
            bt -= (long) KB * 1024;
            res.append(KB).append("KB");
        }
        if (bt > 0) {
            res.append(bt).append("B");
        }
        return res.toString();
    }

    private static long extracted(String str) {
        int count = 0;//赋值语句个数
        for (char ch : str.toCharArray()) {
            if (ch == '=') count++;
        }
        //数据类型
        String type = str.split(" ")[0];
        if(type.equals("int")){
            return count * 4L;
        }
        if(type.equals("long")){
            return count * 8L;
        }
        if(type.equals("String")){
            String[] res = str.split("\"");
            long len = 0;//统计长度
            for (int j = 1; j < res.length; j += 2) {
                len += res[j].length();
            }
            return len;
        }
        //数组
        int cnt = 0;
        String[] res = str.split("=|,");
        for (int j = 1; j < res.length; j += 2) {
            int idx1 = res[j].lastIndexOf("["), idx2 = res[j].lastIndexOf("]");
            int c = Integer.parseInt(res[j].substring(idx1 + 1, idx2));
            cnt += c;
        }
        return type.equals("int[]") ? cnt * 4L : cnt * 8L;
    }
}
