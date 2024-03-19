package 数据结构与算法.蓝桥杯真题.第11届省赛.Java大学A组;
/**
 已AC
 */
public class C蛇形填数 {
    /*
    1 2 6 7
    3 5 8
    4 9
    10...
    求第20行第20列的数字
     */
    public static void main(String[] args) {
        int x = 0, y = 0;
        boolean up = true;//是否是斜向上走
        int len = 1;//需要斜向走的长度
        int curr = 0;//当前步数
        int currNum = 1;//当前需要填充的数字
        while (true) {
            if (x == 19 && y == 19) {
                System.out.println(currNum);//761
                return;
            }
            currNum++;
            curr++;
            if (curr == len) {//当前斜向走完了
                len++;//下一斜向长度为len+1
                curr = 0;//置为0
                if (up) {//如果是斜向上走,走到顶后需要向右移一位
                    y++;
                } else {//同理,向下移一位
                    x++;
                }
                up = !up;//换向
            } else {//走斜向
                if (up) {
                    x--;
                    y++;
                } else {
                    x++;
                    y--;
                }
            }
        }
    }
}
