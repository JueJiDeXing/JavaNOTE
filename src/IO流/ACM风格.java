package IO流;

import java.io.*;
import java.util.Arrays;

public class ACM风格 {

    private static void example1() throws IOException {
        //接收二维数组示例
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(System.out);
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            int m = (int) in.nval;//行
            in.nextToken();
            int n = (int) in.nval;//列
            int[][] mat = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    in.nextToken();
                    mat[i][j] = (int) in.nval;
                }
            }
            //doMain
        }
        br.close();
        out.flush();
        out.close();
    }

    /*
    题目描述
    小明是一名小学老师，某次考试后，他遇到了这样的问题：读入 n 名学生的成绩，将获得某一给定分数的学生人数输出。你能帮助他解决吗？

    输入格式
    第一行包含一个整数 n(1≤n≤1000)，表示学生的人数。
    接下来一行输入 n 个整数 f(0≤f≤100)，表示各学生的成绩，整数间以空格间隔。
    最后一行输入给定的分数 g(0≤g≤100)。

    输出格式
    针对输入，打印出获得给定分数的学生人数。
     */
    private static void example2() throws IOException {
        int[] count = new int[101];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(System.out);
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            Arrays.fill(count, 0);
            int n = (int) in.nval;//列
            for (int i = 0; i < n; i++) {
                in.nextToken();
                count[(int) in.nval]++;
            }
            in.nextToken();
            out.println(count[(int) in.nval]);
        }
        br.close();
        out.flush();
        out.close();
    }

    /*
    不知晓数据规模,只能按行读的情况,无法使用StreamTokenizer
    例如,输入数据,对每一行求和
     */
    private static void example3() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        String line;
        String[] nums;
        int sum;
        while ((line = in.readLine()) != null) {
            nums = line.split(" ");
            sum = 0;
            for (String num : nums) {
                sum += Integer.parseInt(num);
            }
            out.println(sum);
        }
        out.flush();
        in.close();
        out.close();

    }
}
