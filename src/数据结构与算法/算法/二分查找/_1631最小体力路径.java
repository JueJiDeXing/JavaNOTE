package 数据结构与算法.算法.二分查找;

import 数据结构与算法.数据结构.并查集.DisjointSet;

import java.util.*;

public class _1631最小体力路径 {
    public static int[][] change(String str) {
        String[] split = str.split("],\\[");
        int row = split.length;
        int col = 1;
        for (char ch : split[0].toCharArray()) {
            if (ch == ',') col++;
        }
        int[][] arr = new int[row][col];
        int currCol = 0, currRow = 0;
        for (char ch : str.toCharArray()) {
            if (ch == '[' || ch == ',' || ch == ']') continue;
            arr[currRow][currCol] = ch - '0';
            if (++currCol == col) {
                currCol = 0;
                currRow++;
            }

        }
        return arr;
    }

    public static void main(String[] args) {
        _1631最小体力路径 test = new _1631最小体力路径();
        int[][] heights = change("[[1,2,3],[3,8,4],[5,3,5]]");
        System.out.println(test.minimumEffortPath2(heights));
    }
    /*
    你准备参加一场远足活动。给你一个二维 rows x columns 的地图 heights ，
    其中 heights[row][col] 表示格子 (row, col) 的高度。
    一开始你在最左上角的格子 (0, 0) ，且你希望去最右下角的格子 (rows-1, columns-1) （注意下标从 0 开始编号）。
    你每次可以往 上，下，左，右 四个方向之一移动，你想要找到耗费 体力 最小的一条路径。
    一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的。
    请你返回从左上角走到右下角的最小 体力消耗值 。
     */

    /**
     <h1>二分查找+广度搜索</h>
     1 <= heights[i][j] <= 10^6<br>
     枚举高度x,判定在x高度下,能否从左上角走到右下角
     */
    public int minimumEffortPath(int[][] heights) {
        int left = 0, right = 1000000;
        int m = heights.length, n = heights[0].length;
        int ans = 0;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (can(heights, mid, m, n)) { //TODO 尝试dfs
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private boolean can(int[][] heights, int h, int m, int n) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        boolean[][] isVisited = new boolean[m][n];
        isVisited[0][0] = true;
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int x = cell[0], y = cell[1];
            for (int i = 0; i < 4; ++i) {
                int nx = x + directions[i][0];
                int ny = y + directions[i][1];
                if (!isValidIndex(heights, nx, ny) || isVisited[nx][ny]) {
                    continue;
                }
                if (Math.abs(heights[x][y] - heights[nx][ny]) <= h) {
                    queue.offer(new int[]{nx, ny});
                    isVisited[nx][ny] = true;
                }
            }
        }
        return isVisited[m - 1][n - 1];
    }

    private static boolean isValidIndex(int[][] heights, int nextX, int nextY) {
        return nextX >= 0 && nextX < heights.length && nextY >= 0 && nextY < heights[0].length;
    }


    //public boolean dfs(int[][] heights, int x, int y, boolean[][] isVisited, int h) {
    //    if (isEnd(heights, x, y)) {
    //        return true;
    //    }
    //    for (int[] direction : directions) {
    //        int nextX = x + direction[0], nextY = y + direction[1];
    //        if (isNotValidIndex(nextX, nextY, heights) || isVisited[nextX][nextY]) {
    //            continue;
    //        }
    //        if (Math.abs(heights[x][y] - heights[nextX][nextY]) >= h) continue;
    //
    //        isVisited[nextX][nextY] = true;
    //        if (dfs(heights, nextX, nextY, isVisited, h)) {
    //            return true;
    //        }
    //        isVisited[nextX][nextY] = false;
    //    }
    //    return false;
    //}

    /**
     <h1>Kruskal最小生成树</h1>
     每次选取最小边权的边加入并查集中<br>
     当加入一条边后,左上角与右下角连通,则此边权为最小消耗值
     */
    public int minimumEffortPath2(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        Queue<int[]> queue = new PriorityQueue<>((Comparator.comparingInt(o -> o[2])));
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int pos = i * n + j;
                if (j != n - 1) {
                    int pos2 = i * n + j + 1;
                    int[] edge = new int[]{pos, pos2, Math.abs(heights[i][j] - heights[i][j + 1])};
                    queue.offer(edge);
                }
                if (i != m - 1) {
                    int pos2 = (i + 1) * n + j;
                    int[] edge = new int[]{pos, pos2, Math.abs(heights[i][j] - heights[i + 1][j])};
                    queue.offer(edge);
                }
            }
        }
        int size = m * n;
        DisjointSet set = new DisjointSet(size);
        int[] edge = new int[3];
        while (!set.isConnect(0, size - 1)) {
            edge = queue.poll();
            set.union(edge[0], edge[1]);
        }
        return edge[2];
    }

    /**
     <h1>Dijkstra最短路径</h1>
     */
    public int minimumEffortPath3(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
        pq.offer(new int[]{0, 0, 0});
        //设置临时距离
        int[] distance = new int[m * n];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[0] = 0;//起点设为0
        boolean[] isVisited = new boolean[m * n];

        while (!pq.isEmpty()) {
            int[] edge = pq.poll();
            int x = edge[0], y = edge[1], d = edge[2];
            int id = x * n + y;
            if (isVisited[id]) {
                continue;
            }
            if (x == m - 1 && y == n - 1) {
                break;
            }
            isVisited[id] = true;
            for (int i = 0; i < 4; ++i) {
                int nx = x + directions[i][0];
                int ny = y + directions[i][1];
                if (!isValidIndex(heights, nx, ny)) continue;

                int h = Math.max(d, Math.abs(heights[x][y] - heights[nx][ny]));
                if (h < distance[nx * n + ny]) {
                    distance[nx * n + ny] = h;
                    pq.offer(new int[]{nx, ny, distance[nx * n + ny]});
                }
            }
        }
        return distance[m * n - 1];
    }
}
