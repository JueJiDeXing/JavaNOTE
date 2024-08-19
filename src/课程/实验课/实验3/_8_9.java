package 课程.实验课.实验3;

import java.util.Scanner;

public class _8_9 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] map = new int[3][3];
        print(map);
        int r, c;
        int player = 1;
        int count = 0;
        while (count < 9) {
            char symbol = (player == 1 ? 'X' : 'O');
            System.out.print("Enter a row (0, 1, or 2) for player " + symbol + " : ");
            r = sc.nextInt();
            System.out.print("Enter a column (0, 1, or 2) for player " + symbol + " : ");
            c = sc.nextInt();
            if (r < 0 || r > 2 || c < 0 || c > 2 || map[r][c] != 0) {
                System.out.println("Invalid move! Try again.");
                continue;
            }
            map[r][c] = player;
            print(map);
            if (check(map, player)) {
                System.out.println("Player " + symbol + " wins!");
                break;
            }
            player ^= 3;// 1与2切换: 1^3=0b01^0b11=0b10=2  2^3=0b10^0b11=0b01=1
            count++;
        }
        if (count == 9) {
            System.out.println("It's a draw!");
        }
    }

    static boolean check(int[][] map, int player) {
        for (int i = 0; i < 3; i++) {
            int cnt = 0;
            for (int j = 0; j < 3; j++) {
                if (map[i][j] == player) cnt++;
                else break;
            }
            if (cnt == 3) return true;
        }
        for (int j = 0; j < 3; j++) {
            int cnt = 0;
            for (int i = 0; i < 3; i++) {
                if (map[i][j] == player) cnt++;
                else break;
            }
            if (cnt == 3) return true;
        }
        if (map[1][1] != player) return false;
        return (map[0][0] == player && map[2][2] == player) || (map[0][2] == player && map[2][0] == player);
    }

    static void print(int[][] map) {
        System.out.println("-------------");
        for (int[] rows : map) {
            System.out.print("| ");
            for (int col : rows) {
                System.out.print((col == 0 ? " " : (col == 1 ? "X" : "O")) + " | ");
            }
            System.out.println("\n-------------");
        }
    }
}
