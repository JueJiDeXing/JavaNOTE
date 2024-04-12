package 课程.实验课.实验3;

import java.util.Random;
import java.util.Scanner;

public class 猜字游戏 {
    static Scanner sc = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {
        // 先生成一个随机单词
        char[] guessString = create();

        int len = guessString.length;
        boolean[] isGuess = new boolean[len];
        int need = len;
        //猜字
        while (need != 0) {
            System.out.print("(Guess) Enter a letter in word  ");
            for (int i = 0; i < len; i++) {
                if (!isGuess[i]) {
                    System.out.print("*");
                } else {
                    System.out.print(guessString[i]);
                }
            }
            System.out.print(">");
            char ch = sc.next().charAt(0);
            boolean haveEqual = false;
            boolean haveNoGuessEqual = false;
            for (int i = 0; i < len; i++) {
                if (ch == guessString[i]) {
                    if (!isGuess[i]) {
                        isGuess[i] = true;
                        haveNoGuessEqual = true;
                        need--;
                    }
                    haveEqual = true;
                }
            }
            if (need == 0) {
                System.out.println("The word is " + new String(guessString) + ". You missed 1 time ");
                System.out.print("Do you want to guess another word? Enter y or n>");
                char restart = sc.next().charAt(0);
                if (restart == 'y') {
                    guessString = create();//生成新单词,继续游戏
                    continue;
                } else {
                    break;//退出
                }
            }
            if (!haveEqual) {
                System.out.println(ch + " is not in the word");
            } else if (!haveNoGuessEqual) {
                System.out.println(ch + " is already in the word");
            }
        }
    }

    static char[] create() {
        int len = random.nextInt(5, 10);
        char[] guessString = new char[len];
        for (int i = 0; i < len; i++) {
            char ch = (char) (random.nextInt(26) + 'a');
            guessString[i] = ch;
        }
        return guessString;
    }
}
