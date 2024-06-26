package 数据结构与算法.算法.数学.数论.模.例题;

public class 和不能整除差 {
    /*
    [1,2012]选择n个数,任意两个数的和不能被他们的差整除,求n的最大值
     */
    /*
    如果选择了a:
    a+1不能选,他们的差为1,一定可以整除他们的和
    a+2不能选,他们的差为2,他们的和为偶数,可以被整除

    所以间距最小为2,也就是3k+t的形式

    对于3k+1:
    (3m+1 + 3n+1) / (3m+1 - 3n+1)
    = (3(m+n)+2) / 3(m-n)
    分子模3余2,分母模3余0,不能整除
    所以3k+1的数都能取
    1,4,7,...,2011共 (2011-1)/3+1 = 671个数

    对于3k+2:
    (3m+2 + 3n+2) / (3m+2 - 3n+2)
    = (3(m+n)+4) / 3(m-n)
    分子模3余1,分母模3余0,不能整除
    所以3k+2的数都能取
    2,5,8,...,2012共 (2012-2)/3+1 = 671个数

    对于3k:
    (3m+3n) / (3m-3n) = (m+n) / (m-n)
    并不是所有3k的数都能取,个数一定小于671

    所以最大取671个数
     */
}
