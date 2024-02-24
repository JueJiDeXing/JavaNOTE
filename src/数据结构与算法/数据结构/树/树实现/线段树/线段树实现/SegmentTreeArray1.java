package 数据结构与算法.数据结构.树.树实现.线段树.线段树实现;

/**
 用数组表示,带懒更新
 */
public class SegmentTreeArray1 {
    //使用数组表达的线段树
    int MAXN;
    int[] arr;
    int[] sum;
    int[] lazy;//懒增加
    boolean[] update;//懒更新
    int[] change;//懒更新的更新值

    public SegmentTreeArray1(int[] origin) {
        MAXN = origin.length + 1;//0位置是不使用的,因为想要使用位运算替换乘法
        this.arr = new int[MAXN];
        System.arraycopy(origin, 0, this.arr, 1, origin.length);
        sum = new int[MAXN << 2];//4N个节点,所以是左移2位
        lazy = new int[MAXN << 2];
        change = new int[MAXN << 2];
        update = new boolean[MAXN << 2];
    }

    /**
     构建sum(树的累加和)

     @param left  区间左端点
     @param right 区间右端点
     @param k     这个范围在sum对应的下标
     */
    public void build(int left, int right, int k) {
        if (left == right) {
            sum[k] = arr[left];
            return;
        }
        int mid = (left + right) >> 1;
        build(left, mid, k << 1);//乘2,左子树
        build(mid, right, k << 1 | 1);//乘2加1,右子树
        pushUp(k);//汇总,当前子树和=左子树和+右节点和
    }

    private void pushUp(int k) {
        sum[k] = sum[k << 1] + sum[k << 1 | 1];
    }


    /**
     在[L,R]区间上都加一个数

     @param L   区间左端点
     @param R   区间右端点
     @param val 要加的值
     @param l   当前节点表示的区间左端点
     @param r   当前节点表示的区间右端点
     @param k   当前节点
     */
    public void add(int L, int R, int val,
                    int l, int r,
                    int k) {
        //当前节点区间被任务区间完全覆盖
        if (L <= l && r <= R) {
            sum[k] += val * (r - l + 1);//l~r每个数都要加val,所有是val*范围
            lazy[k] += val;
            return;
        }
        //没有完全覆盖,将任务下发给左右孩子
        int mid = (l + r) >> 1;
        pushDown(k, mid - l + 1, r - mid);//先下发当前节点未处理的lazy任务

        if (L <= mid) {
            add(L, R, val, l, mid, k << 1);
        }
        if (mid < R) {//mid+1是右孩子的区间左端点
            add(L, R, val, mid + 1, r, k << 1 | 1);
        }
        pushUp(k);//更新当前节点sum值
    }

    /**
     区间懒更新

     @param L   区间左端点
     @param R   区间右端点
     @param val 要更新成的值
     @param l   当前节点表示的区间左端点
     @param r   当前节点表示的区间右端点
     @param k   当前节点
     */
    public void update(int L, int R, int val, int l, int r, int k) {
        //当前区间被完全覆盖
        if (L <= l && r <= R) {
            update[k] = true;//标记,表示这个节点是需要更新的(懒更新,下次收到任务再更新)
            change[k] = val;//需要更新的更新值
            sum[k] = val * (r - l + 1);//重新计算和
            lazy[k] = 0;//当前lazy取消
            return;
        }
        //没有完全覆盖,需要将lazy下发给左右孩子
        int mid = (l + r) >> 1;
        pushDown(k, mid - l + 1, r - mid);
        if (L <= mid) {
            update(L, R, val, l, mid, k << 1);
        }
        if (mid < R) {
            update(L, R, val, mid + 1, r, k << 1 | 1);
        }
        pushUp(k);
    }

    /**
     将lazy任务下发给左右孩子

     @param k        当前节点
     @param leftNum  左子树节点个数
     @param rightNum 右子树节点个数
     */
    private void pushDown(int k, int leftNum, int rightNum) {
        /*
        如果当前是遇到update任务,那么lazy取消,不需要执行
        如果当前遇到的是lazy任务,先执行之前的update任务,再下发lazy任务
         */
        if (update[k]) {//当前节点有更新任务,下发更新任务,lazy取消
            //将更新任务传给左右孩子
            update[k << 1] = true;
            update[k << 1 | 1] = true;
            change[k << 1] = change[k];
            change[k << 1 | 1] = change[k];
            //孩子的lazy任务取消
            lazy[k << 1] = 0;
            lazy[k << 1 | 1] = 0;
            //计算新的和
            sum[k << 1] = change[k] * leftNum;
            sum[k << 1 | 1] = change[k] * rightNum;
            //当前节点的更新任务完成
            update[k] = false;
        }
        if (lazy[k] != 0) {
            lazy[k << 1] += lazy[k];//下发给左孩子
            sum[k << 1] += lazy[k] * leftNum;//子树累加和
            lazy[k << 1 | 1] += lazy[k];
            sum[k << 1 | 1] += lazy[k] * rightNum;
            lazy[k] = 0;//清空自己的任务
        }
    }

    /**
     求区间的累加和

     @param L 区间左端点
     @param R 区间右端点
     @param l 当前节点表示的区间左端点
     @param r 当前节点表示的区间右端点
     @param k 当前节点
     */
    public long query(int L, int R, int l, int r, int k) {
        //当前节点区间被任务区间完全覆盖,返回当前子树的累加和
        if (L <= l && r <= R) {
            return sum[k];
        }
        //没有完全覆盖,任务下发给左右孩子
        int mid = (l + r) >> 1;
        pushDown(k, mid - l + 1, r - mid);//将当前节点未处理的lazy任务下发
        //累加左右的累加和
        long ans = 0;
        if (L <= mid) {
            ans += query(L, R, l, mid, k << 1);
        }
        if (mid < R) {
            ans += query(L, R, mid + 1, r, k << 1 | 1);
        }
        return ans;
    }
}
