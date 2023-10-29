package demo;

import java.io.*;

public class 用户登录 {
    public static void main(String[] args) throws IOException {
        String[] userInput = {"zhangSan", "123456"};//用户输入

        BufferedReader br = new BufferedReader(new FileReader("NOTE\\src\\demo\\ff.txt"));
        String line = br.readLine();//username=xxx&password=xxx&count=xxx
        br.close();
        String[] userinfo = line.split("&");
        String username = userinfo[0].split("=")[1];
        String userpassword = userinfo[1].split("=")[1];
        int count = Integer.parseInt(userinfo[2].split("=")[1]);

        System.out.println(username);
        if (username.equals(userInput[0]) && userpassword.equals(userInput[1])) {
            System.out.println("登录成功");
            count=0;
        }else {
            if (count >= 5) {
                System.out.println("只能输错5次");
            } else {
                System.out.println("还剩下"+(5-count)+"次机会");
                count++;
            }
        }
        BufferedWriter bw=new BufferedWriter(new FileWriter("NOTE\\src\\demo\\ff.txt"));
        String newLine=userinfo[0]+"&"+userinfo[1]+"&"+userinfo[2].split("=")[0]+"="+count;
        bw.write(newLine);
        bw.close();
    }
}
