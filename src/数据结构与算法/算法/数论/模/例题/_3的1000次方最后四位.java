package 数据结构与算法.算法.数论.模.例题;

public class _3的1000次方最后四位 {
    /*
    3^1000  % 10000
    = 9^500 % 10000
    = (1-10)^500 % 10000
    = {C(500,0) 1^500 (-10)^0 + C(500,1) 1^499 (-10)^1 + C(500,2) 1^498 (-10)^2 + ...}  % 10000
    = {1 + (-5000) + 500*499/2 * 100 } % 10000  到了后面的项,余上10000是0,对结果无影响
    = {1 - 5000 + 25000*(500-1) } % 10000
    = {1 - 5000 - 25000 } % 10000
    = -30001 % 10000
    = 0001
     */
}
