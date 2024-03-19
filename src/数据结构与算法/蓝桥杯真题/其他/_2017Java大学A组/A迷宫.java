package 数据结构与算法.蓝桥杯真题.其他._2017Java大学A组;

public class A迷宫 {
    /*
    10*10的迷宫,每个位置都有标记字母,(D,R,L,U),表示在该格往哪个方向走
    问有多少个位置,可以走出迷宫
    迷宫地图如下:
    UDDLUULRUL
    UURLLLRRRU
    RRUURLDLRD
    RUDDDDUUUU
    URUDLLRRUU
    DURLRLDLRL
    ULLURLLRDU
    RDLULLRDDD
    UUDDUDUDLL
    ULRDLUURRR
     */
    public static void main(String[] args) {
        String[] map = "UDDLUULRUL UURLLLRRRU RRUURLDLRD RUDDDDUUUU URUDLLRRUU DURLRLDLRL ULLURLLRDU RDLULLRDDD UUDDUDUDLL ULRDLUURRR".split(" ");
        int res = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (solve(map, i, j, new boolean[10][10])) {
                    res++;
                }
            }
        }
        System.out.println(res);
    }

    static boolean solve(String[] map, int x, int y, boolean[][] visited) {
        if (x < 0 || x >= 10 || y < 0 || y >= 10) return true;//走出去了
        if (visited[x][y]) return false;//转圈
        visited[x][y] = true;
        char direction = map[x].charAt(y);
        if (direction == 'U') {//上
            return solve(map, x, y + 1, visited);
        }
        if (direction == 'D') {
            return solve(map, x + 1, y, visited);
        }
        if (direction == 'L') {
            return solve(map, x - 1, y, visited);
        }
        return solve(map, x, y - 1, visited);
    }

}
