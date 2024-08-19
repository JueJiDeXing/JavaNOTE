package 课程.实验课.课堂练习;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class 文件异常处理 {
    static class MyException extends Exception {
        public MyException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {
        createFile();
        Scanner sc = new Scanner(System.in);
        try {
            Map<String, Integer> scoreMap = readScore("score.txt");
            System.out.print("请输入要查找的学生姓名:");
            String name = sc.next();
            searchScore(name, scoreMap);
        } catch (IOException e) {
            System.out.println("读取文件出错:" + e.getMessage());
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("score.txt"))) {
            bw.write("tom 89\n");
            bw.write("mike 90\n");
            bw.write("mary 86");
        } catch (IOException e) {
            System.out.println("写入文件出错:" + e.getMessage());
            System.exit(1); // 异常退出
        }
    }

    public static Map<String, Integer> readScore(String fileName) throws IOException {
        Map<String, Integer> scoreMap = new HashMap<>();// 人名 -> 分数
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) break;
                String[] parts = line.split(" ");
                scoreMap.put(parts[0], Integer.parseInt(parts[1]));
            }
        }
        return scoreMap;
    }

    public static void searchScore(String name, Map<String, Integer> scoreMap) throws MyException {
        if (scoreMap.containsKey(name)) {
            System.out.println(name + "的分数是:" + scoreMap.get(name));
        } else {
            throw new MyException("检索失败，未找到" + name + "的分数");
        }
    }
}
