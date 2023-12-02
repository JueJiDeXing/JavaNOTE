package 数据结构与算法.算法.数论;

import java.util.TreeMap;

public class 差分数组 {
    /*
    举例
    考虑数组 a=[1,3,3,5,8]a=[1,3,3,5,8]a=[1,3,3,5,8]，对其中的相邻元素两两作差（右边减左边），
    得到数组 [2,0,2,3][2,0,2,3][2,0,2,3]。然后在开头补上 a[0]a[0]a[0]，得到差分数组

    d=[1,2,0,2,3]

    这有什么用呢？
    如果从左到右累加 ddd 中的元素，我们就「还原」回了 aaa 数组 [1,3,3,5,8]。
    这类似求导与积分的概念。

    这又有什么用呢？
    现在把连续子数组 a[1],a[2],a[3] 都加上 10，得到 a′=[1,13,13,15,8]。
    再次两两作差，并在开头补上 a′[0]，得到差分数组d′=[1,12,0,2,−7]

    对比 d 和 d′，可以发现只有 d[1] 和 d[4] 变化了，
    这意味着对 aaa 中连续子数组的操作，可以转变成对差分数组 ddd 中两个数的操作。

    定义和性质
    对于数组 a，定义其差分数组（difference array）为

    d[i]={ a[0],i=0 ; a[i]−a[i−1],i≥1 }

    性质 1：从左到右累加 ddd 中的元素，可以得到数组 aaa。

    性质 2：如下两个操作是等价的。

    把 a 的子数组 a[i],a[i+1],⋯,a[j] 都加上 xxx。
    把 d[i] 增加 xxx，把 d[j+1] 减少 xxx。
    利用性质 2，我们只需要 O(1)) 的时间就可以完成对 a 的子数组的操作。最后利用性质 1 从差分数组复原出数组 a。

    注：也可以这样理解，d[i] 表示把下标 ≥i 的数都加上 d[i]。
     */

}

class _1094拼车 {
    /*
    车上最初有 capacity 个空座位。车 只能 向一个方向行驶（也就是说，不允许掉头或改变方向）

    给定整数 capacity 和一个数组 trips ,
    trip[i] = [numPassengersi, fromi, toi] 表示第 i 次旅行有 numPassengersi 乘客，接他们和放他们的位置分别是 fromi 和 toi 。
    这些位置是从汽车的初始位置向东的公里数。

    当且仅当你可以在所有给定的行程中接送所有乘客时，返回 true，否则请返回 false。

    示例 1：
        输入：trips = [[2,1,5],[3,3,7]], capacity = 4
        输出：false
     */

    /*
     本题思路
     对于本题，设 a[i] 表示车行驶到位置 i 时车上的人数。
     我们需要判断是否所有 a[i] 都不超过 capacity。

     trips[i] 相当于把 a 中下标从 fromi 到 toi 的数都增加 numPassengersi。
     这正好可以用上面讲的差分数组解决。

     例如示例 1 对应的 d 数组，d[1]=2,d[5]=−2,d[3]=3,d[7]=−3
     d=[0,2,0,3,0,−2,0,−3,⋯]
     从左到右累加，得到
     a=[0,2,2,5,5,3,3,0,⋯]
     capacity=4，由于 max(a)=5>4，所以返回 false。
     */

    /**
     <h1>实现一:数组</h1>
     创建一个长为 1001 的差分数组，这可以保证 d 数组不会下标越界。
     */
    public boolean carPooling(int[][] trips, int capacity) {
        int toMax = 0;
        for (int[] trip : trips) {
            toMax = Math.max(trip[2], toMax);
        }
        int[] path = new int[toMax + 1];
        for (int[] t : trips) {
            path[t[1]] += t[0];//fromi上n个人
            path[t[2]] -= t[0];//toi下n个人
        }
        int s = 0;//滚动变量,差分复原数组
        for (int i : path) {
            s += i;
            if (s > capacity) return false;
        }
        return true;
    }

    /**
     <h1>实现二:平衡树</h1>
     因为我们只需要考虑在 fromi 和 toi 这些位置上的乘客数，其余位置的乘客是保持不变的，无需考虑。
     平衡树可以保证我们是从小到大遍历这些位置的。
     当然，如果你不想用平衡树的话，也可以用哈希表，把哈希表的 key 取出来排序，就可以从小到大遍历这些位置了。
     */
    public boolean carPooling2(int[][] trips, int capacity) {
        TreeMap<Integer, Integer> d = new TreeMap<>();
        for (int[] t : trips) {
            int num = t[0], from = t[1], to = t[2];
            //merge(K key,V value,java.util.function.BiFunction<? super V, ? super V, ? extends V> remappingFunction)
            // key – 要与结果值关联的键
            // value – 要与键关联的现有值合并的非 null 值，或者，如果没有现有值或 null 值与键关联，则与键关联
            // remappingFunction – 重新映射函数，用于重新计算值（如果存在）
            d.merge(from, num, Integer::sum);//
            d.merge(to, -num, Integer::sum);
        }
        int s = 0;
        for (int v : d.values()) {
            s += v;
            if (s > capacity) {
                return false;
            }
        }
        return true;
    }

    /*
    练习题（右边数字为难度分）
    1109. 航班预订统计 1570
    2406. 将区间分为最少组数 1713
    2381. 字母移位 II 1793
    2772. 使数组中的所有元素都等于零 2029
    2528. 最大化城市的最小供电站数目 2236
     */
}
