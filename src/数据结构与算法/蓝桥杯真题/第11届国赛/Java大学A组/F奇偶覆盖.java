package 数据结构与算法.蓝桥杯真题.第11届国赛.Java大学A组;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.math.BigInteger;
import java.util.*;

public class F奇偶覆盖 {
    /*
    给出n个矩形的对角坐标
    求叠奇数层的矩形面积覆盖 和 叠偶数层的矩形面积覆盖
     */static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    private static void solve1() {
        int n = Int();
        int[][] rect = new int[n][4];
        HashSet<Integer> setX = new HashSet<>();

        for (int i = 0; i < n; i++) {
            int x1 = Int(), y1 = Int(), x2 = Int(), y2 = Int();
            rect[i] = new int[]{x1, y1, x2, y2};
            setX.add(x1);
            setX.add(x2);
        }
        List<Integer> listX = new ArrayList<>(setX);
        Collections.sort(listX);
        long ansOdd = 0, ansEven = 0;
        for (int i = 1; i < listX.size(); i++) {
            int b = listX.get(i), a = listX.get(i - 1);
            if (a == b) continue;
            List<int[]> listY = new ArrayList<>();
            for (int[] info : rect) {
                if (info[0] <= a && info[2] >= b) {
                    listY.add(new int[]{info[1], 1});//入边
                    listY.add(new int[]{info[3], -1});//出边
                }
            }
            listY.sort(Comparator.comparingInt(a1 -> a1[0]));
            int degree = 1;
            long cntOdd = 0, cntEven = 0;//奇偶的Y覆盖
            for (int j = 1; j < listY.size(); j++) {
                int[] y1 = listY.get(j - 1), y2 = listY.get(j);
                if (degree == 0) {//y1->y2是空的
                    degree++;
                    continue;
                }
                if (degree % 2 == 0) {
                    cntEven += y2[0] - y1[0];
                } else {
                    cntOdd += y2[0] - y1[0];
                }
                degree += y2[1];
            }
            ansOdd += cntOdd * (b - a);
            ansEven += cntEven * (b - a);

        }
        System.out.println(ansOdd);
        System.out.println(ansEven);
    }

    public static void main(String[] args) {
        int n = Int();
        long[][] y = new long[n * 2][4];// n个矩形有2n个上下边
        TreeSet<Integer> x = new TreeSet<>();// 将横坐标, 排序去重
        for (int i = 0; i < n; i++) {
            int x1 = Int(), y1 = Int(), x2 = Int(), y2 = Int();
            y[i] = new long[]{y1, x1, x2, 1};// {y,x1,x2,sign} 其中sign为出入边标记( -1/1 )
            y[n + i] = new long[]{y2, x1, x2, -1};
            x.add(x1);
            x.add(x2);
        }
        Arrays.sort(y, (a1, a2) -> (a1[0] - a2[0]) >= 0 ? 1 : -1);// 按y值升序
        Integer[] x_deal = x.toArray(new Integer[0]);// 排序树变数组

        SegmentTree st = new SegmentTree(x_deal);// 实例化线段树
        BigInteger ans1 = BigInteger.ZERO, ans2 = BigInteger.ZERO;// 结果不用int,否者答案会溢出,long也不够
        for (int i = 0; i < y.length - 1; i++) {// 最后一条y边不遍历也行
            st.modify(0, y[i][1], y[i][2], (int) y[i][3]);// 遍历一条y就修改一下树
            if (y[i + 1][0] > y[i][0]) {// 如果下一条y边大于当前这条, 才开始计算本扫描区间的结果
                long high = y[i + 1][0] - y[i][0];
                ans1 = ans1.add(BigInteger.valueOf(st.query(1) * high));
                ans2 = ans2.add(BigInteger.valueOf(st.query(0) * high));
            }
        }
        System.out.println(ans1 + "\n" + ans2);// 打印缓存

    }

    static class SegmentTree {// 线段树
        Section[] tr;// (区间)存储数组
        Integer[] x;// (横坐标)去重后存储数组

        class Section {// 最小不可分的“区间类”
            int l, r, count;// 左边界、右边界、完全覆盖计数
            long w1, w2, len;// 奇覆盖宽、偶覆盖宽、区间总长

            public Section() {
            }

            public Section(int left, int right) {
                this.l = left;
                this.r = right;
                this.len = x[right + 1] - x[left];// 偏移映射的“计算r+1”, 结果直接存起来
            }
        }

        public SegmentTree(Integer[] nums) {// 初始化
            this.x = nums;
            int n = nums.length - 1;// nums.length 即x点的个数, n即这些点间的区间数，所以减1
            this.tr = new Section[n * 4];// 线段树的空间要开4倍，怕方法查询时空指针
            for (int i = 0; i < tr.length; i++) {
                tr[i] = new Section();
            }
            build(0, 0, n - 1);// n相当于数组的长度，这里“n-1”是数组的最后一个索引
        }

        private void build(int i, int left, int right) {// 建树
            tr[i] = new Section(left, right);
            if (left != right) {// 没有走到树叶
                build(toLeft(i), left, toMid(i));
                build(toRight(i), toMid(i) + 1, right);
            }
        }

        /**
        从节点i开始, 将x区间[L,R]添加覆盖sign, sign=1为入边y1, sign=-1为出边y2
         */
        private void modify(int i, Long L, Long R, int sign) {
            if (i >= tr.length || R <= x[tr[i].l] || x[tr[i].r + 1] <= L) {// 越界
                return;
            }
            if (L <= x[tr[i].l] && x[tr[i].r + 1] <= R) {// 全部在区间内(偏移映射的“计算r+1”)
                tr[i].count += sign;
            } else {
                if (L <= x[toMid(i)]) {
                    modify(toLeft(i), L, R, sign);
                }
                if (R > x[toMid(i)]) {
                    modify(toRight(i), L, R, sign);
                }
            }
            pushUp(i);// 前面修改了该区间的覆盖标记，这里顺便更新该区间的覆盖数据
        }

        private void pushUp(int i) {// 递推向上更新数据
            if (tr[i].l == tr[i].r) {
                // 走到【树叶】
                if (tr[i].count == 0) {// 没有覆盖标记时
                    tr[i].w1 = 0;
                    tr[i].w2 = 0;
                } else if (tr[i].count % 2 == 1) {// 奇数个覆盖时
                    tr[i].w1 = tr[i].len;
                    tr[i].w2 = 0;
                } else {// 偶数个覆盖时
                    tr[i].w1 = 0;
                    tr[i].w2 = tr[i].len;
                }
            } else {
                // 走到【树干】
                long ww1 = tr[toLeft(i)].w1 + tr[toRight(i)].w1;// 子区间奇宽之和
                long ww2 = tr[toLeft(i)].w2 + tr[toRight(i)].w2;// 子区间偶宽之和
                if (tr[i].count == 0) {// 没有覆盖标记时, 但子区间可能有覆盖
                    tr[i].w1 = ww1;
                    tr[i].w2 = ww2;
                } else if (tr[i].count % 2 == 1) {// 奇数个覆盖时
                    tr[i].w1 = tr[i].len - ww1;// 因为完全覆盖才有覆盖标记,所以用大减小,且奇数会使子区间奇偶变换
                    tr[i].w2 = tr[i].len - tr[i].w1;
                } else {// 偶数个覆盖时
                    tr[i].w1 = ww1;
                    tr[i].w2 = tr[i].len - tr[i].w1;
                }
            }
        }

        private long query(int model) {
            long ans = 0;
            if (model == 1) {// 查询 奇 宽度
                ans = tr[0].w1;
            }
            if (model == 0) {// 查询 偶 宽度
                ans = tr[0].w2;
            }
            return ans;
        }

        private int toMid(int i) {// 求二分索引
            return (tr[i].l + tr[i].r) / 2;
        }

        private int toLeft(int i) {// 左子树索引
            return i * 2 + 1;
        }

        private int toRight(int i) {// 右子树索引
            return i * 2 + 2;
        }
    }
}
