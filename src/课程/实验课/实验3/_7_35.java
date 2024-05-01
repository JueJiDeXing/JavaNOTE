package 课程.实验课.实验3;

import java.util.Random;
import java.util.Scanner;

public class _7_35 {
    static Scanner sc = new Scanner(System.in);
    static Random random = new Random();
    static String[] words = {"hello", "world", "java", "mysql"};

    static char[] create() {
        return words[random.nextInt(words.length)].toCharArray();
    }

    public static void main(String[] args) {
        // 先生成一个随机单词
        char[] guessString = create();
        int len = guessString.length;
        boolean[] isGuess = new boolean[len];
        int need = len;
        //猜字
        while (need != 0) {
            //输入
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
            //检查猜测情况
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
            if (need == 0) {//全部猜完
                System.out.println("The word is " + new String(guessString) + ". You missed 1 time ");
                System.out.print("Do you want to guess another word? Enter y or n>");
                char restart = sc.next().charAt(0);
                if (restart == 'y') {
                    guessString = create();//生成新单词,继续游戏
                    len = guessString.length;
                    isGuess = new boolean[len];
                    need = len;
                    continue;
                } else {
                    break;//退出
                }
            }
            if (!haveEqual) {//没有猜中
                System.out.println(ch + " is not in the word");
            } else if (!haveNoGuessEqual) {//字母已被猜过
                System.out.println(ch + " is already in the word");
            }
        }
    }
}
