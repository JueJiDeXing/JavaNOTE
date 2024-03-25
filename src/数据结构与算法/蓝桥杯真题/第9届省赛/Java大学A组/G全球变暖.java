package 数据结构与算法.蓝桥杯真题.第9届省赛.Java大学A组;

import java.util.*;

/**
 已AC
 */
public class G全球变暖 {
    /*
    N*N的地图
    .为海水  #为陆地
    与海水直接相邻的陆地会被淹没
    求最后剩余的岛屿数量
      */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        map = new char[n][n];
        for (int i = 0; i < n; i++) {
            map[i] = sc.next().toCharArray();
        }
        isVisited = new boolean[n][n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] != '#' || isVisited[i][j]) continue;
                isVisited[i][j] = true;
                if (!dfs(i, j)) {//将连通的部分都置为true,并判断其中是否有块不与海水相邻
                    ans++;//都是与海水相邻的,岛屿被淹没数+1
                }
            }
        }
        System.out.println(ans);
    }

    static char[][] map;
    static boolean[][] isVisited;

    /**
     将与(x,y)连通的陆地置为visited
     返回连通块中,是否存在陆地不与海水相邻
     */
    static boolean dfs(int x, int y) {
        boolean ans = false;
        for (int[] d : direction) {
            int nx = x + d[0], ny = y + d[1];
            if (!isValid(nx, ny) || map[nx][ny] == '.' || isVisited[nx][ny]) continue;
            isVisited[nx][ny] = true;
            if (!isConnect(nx, ny)) {
                ans = true;
            }
            boolean contain = dfs(nx, ny);
            if (contain) ans = true;
        }
        return ans;
    }


    static int n;
    static int[][] direction = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    /**
     陆地(x,y)是否与海水相邻
     */
    static boolean isConnect(int x, int y) {
        for (int[] d : direction) {
            int nx = x + d[0], ny = y + d[1];
            if (!isValid(nx, ny)) continue;
            if (map[nx][ny] == '.') return true;
        }
        return false;
    }

    static boolean isValid(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

}
