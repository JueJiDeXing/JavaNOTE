package 数据结构与算法.算法.数论.差分数组;

public class 差分数组 {
    /*
    举例
    考虑数组 a=[1,3,3,5,8]，对其中的相邻元素两两作差（右边减左边），
    得到数组 [2,0,2,3]。然后在开头补上 a[0]，得到差分数组
    d=[1,2,0,2,3]

    这有什么用呢？
    如果从左到右累加 d 中的元素，我们就「还原」回了 a 数组 [1,3,3,5,8]。
    这类似求导与积分的概念。

    这又有什么用呢？
    现在把连续子数组 a[1],a[2],a[3] 都加上 10，得到 a′=[1,13,13,15,8]。
    再次两两作差，并在开头补上 a′[0]，得到差分数组d′=[1,12,0,2,−7]

    对比 d = [1,2,0,2,3] 和 d′ = [1,12,0,2,−7] ，
    可以发现只有 d[1] 和 d[4] 变化了 ，
    这意味着对 a 中连续子数组的操作，可以转变成对差分数组 d 中两个数的操作 。

    定义和性质
    对于数组 a，定义其差分数组（difference array）为

    d[i]={ a[0],i=0 ; a[i]−a[i−1],i≥1 }

    性质 1：从左到右累加 d 中的元素，可以得到数组 a。

    性质 2：如下两个操作是等价的。

    操作1: 把 a 的子数组 a[i],a[i+1],⋯,a[j] 都加上 x。
    操作2: 把 d[i] 增加 x，把 d[j+1] 减少 x。
    利用性质 2，我们只需要 O(1)) 的时间就可以完成对 a 的子数组的操作。最后利用性质 1 从差分数组复原出数组 a。

    !注：也可以这样理解，d[i] 表示把下标 ≥i 的数都加上 d[i]。
     */

}

